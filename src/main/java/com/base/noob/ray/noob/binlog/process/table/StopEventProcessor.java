package com.base.noob.ray.noob.binlog.process.table;

import com.base.noob.ray.noob.binlog.process.EventProcess;
import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.impl.event.StopEvent;

public class StopEventProcessor implements EventProcess {
    @Override
    public void process(BinlogEventV4 event) {
        StopEvent stopEvent = (StopEvent)event;

    }

    @Override
    public Class<?> getEventClass() {
        return StopEvent.class;
    }
}
