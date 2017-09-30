package com.base.noob.ray.noob.binlog.process.write;

import com.alibaba.fastjson.JSONArray;
import com.base.noob.ray.noob.binlog.meta.TableMeta;
import com.base.noob.ray.noob.binlog.meta.TableMetaCache;
import com.base.noob.ray.noob.binlog.model.RowDiffModel;
import com.base.noob.ray.noob.binlog.process.EventProcess;
import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.impl.event.AbstractRowEvent;
import com.google.code.or.common.glossary.Column;
import com.google.code.or.common.glossary.column.BlobColumn;
import com.google.code.or.common.glossary.column.StringColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public abstract class AbstractWriteEventProcessor<T extends AbstractRowEvent> implements EventProcess {

    @Autowired
    private TableMetaCache tableMetaCache;


    @Override
    public void process(BinlogEventV4 event) {
        AbstractRowEvent abstractRowEvent = (AbstractRowEvent) event;
        String tableId = "" + abstractRowEvent.getTableId();
        @SuppressWarnings("unchecked")
        List<RowDiffModel> rowDiffModels = buildRowDiffModel(tableId, (T) event);

        /**
         * do what you want,,, sender or output
         */
        log.error("****RowDiffModel**** : {}" , JSONArray.toJSONString(rowDiffModels));
    }



    abstract List<RowDiffModel> buildRowDiffModel(String tableId, T event);


    protected TableMeta getTableMeta(String tableId, int realColumnCount) {
        TableMeta tableMeta = tableMetaCache.getTableMeta(tableId);

        if (tableMeta.getColumnMetas().size() != realColumnCount) {
            tableMeta = tableMetaCache.rebuild(tableMeta.getTableName());
        }

        return tableMeta;
    }

    protected Object getValue(Column column) {
        if (column instanceof StringColumn) {
            return column.toString();
        } else if (column instanceof BlobColumn) { //text类型特殊处理
            return new String((byte[]) column.getValue());
        }
        else {
            return column.getValue();
        }
    }

}
