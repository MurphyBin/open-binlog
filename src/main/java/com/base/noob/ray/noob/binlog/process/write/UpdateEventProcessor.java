package com.base.noob.ray.noob.binlog.process.write;

import com.alibaba.fastjson.JSONObject;
import com.base.noob.ray.noob.binlog.meta.ColumnMeta;
import com.base.noob.ray.noob.binlog.meta.TableMeta;
import com.base.noob.ray.noob.binlog.meta.TableMetaCache;
import com.base.noob.ray.noob.binlog.model.RowDiffModel;
import com.google.code.or.binlog.impl.event.UpdateRowsEventV2;
import com.google.code.or.common.glossary.Row;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UpdateEventProcessor extends AbstractWriteEventProcessor<UpdateRowsEventV2> {
    @Override
    List<RowDiffModel> buildRowDiffModel(String tableId, UpdateRowsEventV2 event) {

        log.error("**************start to 更新事件 table:{}, UpdateRowsEventV2: {}", tableId, JSONObject.toJSONString(event));
        List<RowDiffModel> rowDiffModels = new ArrayList<>();
        TableMeta tableMeta = getTableMeta(tableId, event.getColumnCount().intValue());

        event.getRows().forEach(rowPair -> {
            RowDiffModel rowDiffModel = new RowDiffModel();

            rowDiffModel.setTableName(tableMeta.getTableName());
            rowDiffModel.setTimestamp(event.getHeader().getTimestamp());
            rowDiffModel.setType(2);

            Row preRow = rowPair.getBefore();
            Row newRow = rowPair.getAfter();

            for (int i = 0; i < tableMeta.getColumnMetas().size(); i++) {
                ColumnMeta columnMeta = tableMeta.getColumnMetas().get(i);
                Object preValue = getValue(preRow.getColumns().get(i));
                Object newValue = getValue(newRow.getColumns().get(i));

                if (columnMeta.isPk()) {
                    rowDiffModel.getPkColumnName().add(columnMeta.getColumnName());
                    rowDiffModel.getPk().add(newValue);
                }

                rowDiffModel.getPreValue().put(columnMeta.getColumnName(), preValue);
                rowDiffModel.getNewValue().put(columnMeta.getColumnName(), newValue);

                if (!compare(preValue, newValue)) {
                    rowDiffModel.getDiffColumns().add(columnMeta.getColumnName());
                }
            }

            rowDiffModels.add(rowDiffModel);
        });

        return rowDiffModels;
    }


    boolean compare(Object left, Object right) {
        if (left == null) {
            return right == null;
        } else {
            return left.equals(right);
        }
    }


    @Override
    public Class<?> getEventClass() {
        return UpdateRowsEventV2.class;
    }
}
