package com.base.noob.ray.noob.binlog.process.table;

import com.alibaba.fastjson.JSONObject;
import com.base.noob.ray.noob.binlog.meta.TableMetaCache;
import com.base.noob.ray.noob.binlog.process.EventProcess;
import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.impl.event.TableMapEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TableMapEventProcessor implements EventProcess {

    @Autowired
    private TableMetaCache tableMetaCache;

    @Override
    public void process(BinlogEventV4 event) {

        log.error("**************start to Table事件, UpdateRowsEventV2: {}", JSONObject.toJSONString(event));
        TableMapEvent tableMapEvent = (TableMapEvent) event;
        String tableName = tableMapEvent.getDatabaseName() + "." + tableMapEvent.getTableName();

        tableMetaCache.put("" + tableMapEvent.getTableId(), tableName);
    }

    @Override
    public Class<?> getEventClass() {
        return TableMapEvent.class;
    }
}
