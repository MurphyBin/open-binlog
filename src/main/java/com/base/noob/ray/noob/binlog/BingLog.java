package com.base.noob.ray.noob.binlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BingLog {
    @Autowired
    private NotificationListener notificationListener;


    public void init() {
        log.error("start to -------------------BingLog");
        final AutoOpenReplicator aor = new AutoOpenReplicator();
        aor.setServerId(1);
        aor.setHost("127.0.0.1");
        aor.setUser("root");
        aor.setPassword("000123");
        aor.setAutoReconnect(true);
        aor.setDelayReconnect(5);
        aor.setBinlogFileName("mysql-bin.000007");
        aor.setBinlogPosition(4L);
        aor.setBinlogEventListener(notificationListener);
        aor.start();
    }
}
