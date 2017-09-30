package com.base.noob.ray.noob.binlog.process.write;

import com.alibaba.fastjson.JSONObject;
import com.base.noob.ray.noob.binlog.meta.ColumnMeta;
import com.base.noob.ray.noob.binlog.meta.TableMeta;
import com.base.noob.ray.noob.binlog.model.RowDiffModel;
import com.google.code.or.binlog.impl.event.WriteRowsEventV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InsertEventProcessor extends AbstractWriteEventProcessor<WriteRowsEventV2> {

    @Override
    List<RowDiffModel> buildRowDiffModel(String tableId, WriteRowsEventV2 event) {

        log.error("**************start to 插入事件 table:{}, UpdateRowsEventV2: {}", tableId, JSONObject.toJSONString(event));
        List<RowDiffModel> rowDiffModels = new ArrayList<>();
        TableMeta tableMeta = getTableMeta(tableId, event.getColumnCount().intValue());

        event.getRows().forEach(row -> {
            RowDiffModel rowDiffModel = new RowDiffModel();

            rowDiffModel.setTableName(tableMeta.getTableName());
            rowDiffModel.setTimestamp(event.getHeader().getTimestamp());
            rowDiffModel.setType(1);

            for (int i = 0; i < tableMeta.getColumnMetas().size(); i++) {
                ColumnMeta columnMeta = tableMeta.getColumnMetas().get(i);
                Object value = getValue(row.getColumns().get(i));

                if (columnMeta.isPk()) {
                    rowDiffModel.getPkColumnName().add(columnMeta.getColumnName());
                    rowDiffModel.getPk().add(value);
                }

                rowDiffModel.getDiffColumns().add(columnMeta.getColumnName());
                rowDiffModel.getNewValue().put(columnMeta.getColumnName(), value);
            }

            rowDiffModels.add(rowDiffModel);
        });

        return rowDiffModels;
    }


    @Override
    public Class<?> getEventClass() {
        return WriteRowsEventV2.class;
    }
}
