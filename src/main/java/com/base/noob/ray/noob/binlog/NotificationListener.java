package com.base.noob.ray.noob.binlog;

import com.alibaba.fastjson.JSON;
import com.base.noob.ray.noob.binlog.process.EventProcessorFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.code.or.binlog.BinlogEventListener;
import com.google.code.or.binlog.BinlogEventV4;
import com.google.code.or.binlog.impl.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener implements BinlogEventListener {

    @Autowired
    private EventProcessorFactory eventProcessorFactory;

    private static Logger logger = LoggerFactory.getLogger(NotificationListener.class);
    private String eventDatabase;

    /**
     * 先搞一个简单的例子 试试水
     * @param event
     */
    @Override
    public void onEvents(BinlogEventV4 event) {


        logger.error("****************************EVENT IN *************************************");
        logger.error("eventType: {} , event: {}", event.getClass(), JSON.toJSON(event));
        eventProcessorFactory.process(event);
    }
}
