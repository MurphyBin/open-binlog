package com.base.noob.ray.noob.app;

import com.base.noob.ray.noob.NoobThread.NoobUpdate;
import com.base.noob.ray.noob.binlog.BingLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //start update mysql
        NoobUpdate noobUpdate =  contextRefreshedEvent.getApplicationContext().getBean(NoobUpdate.class);
        noobUpdate.init();


        //start binglog
        BingLog bingLog = contextRefreshedEvent.getApplicationContext().getBean(BingLog.class);
        bingLog.init();
    }
}
