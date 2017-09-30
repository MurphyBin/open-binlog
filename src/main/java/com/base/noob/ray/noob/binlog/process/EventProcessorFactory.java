package com.base.noob.ray.noob.binlog.process;

import com.base.noob.ray.noob.binlog.meta.TableMetaCache;
import com.base.noob.ray.noob.binlog.process.table.TableMapEventProcessor;
import com.base.noob.ray.noob.binlog.process.write.InsertEventProcessor;
import com.base.noob.ray.noob.binlog.process.write.UpdateEventProcessor;
import com.google.code.or.binlog.BinlogEventV4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class EventProcessorFactory {

    @Autowired
    private InsertEventProcessor insertEventProcessor;
    @Autowired
    private UpdateEventProcessor updateEventProcessor;
    @Autowired
    private TableMapEventProcessor tableMapEventProcessor;
    private static Map<Class, EventProcess> eventProcessMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void post() {
        init(insertEventProcessor);
        init(updateEventProcessor);
        init(tableMapEventProcessor);
    }

    public void process(BinlogEventV4 event) {
        EventProcess eventProcess = eventProcessMap.get(event.getClass());

        if (null != eventProcess) {
            eventProcess.process(event);
        } else {
            log.debug("**********************no process event: " + event);
        }
    }


    private void init(EventProcess eventProcess) {
        eventProcessMap.put(eventProcess.getEventClass(), eventProcess);
    }
}
