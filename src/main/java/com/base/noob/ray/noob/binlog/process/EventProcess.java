package com.base.noob.ray.noob.binlog.process;

import com.google.code.or.binlog.BinlogEventV4;

public interface EventProcess {

    void process(BinlogEventV4 event);

    Class<?> getEventClass();
}
