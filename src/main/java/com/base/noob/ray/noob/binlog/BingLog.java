package com.base.noob.ray.noob.binlog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BingLog {
    @Autowired
    private NotificationListener notificationListener;

    @Value("${binlog.datasource.noob.userName}")
    private String user;
    @Value("${binlog.datasource.noob.password}")
    private String password;
    @Value("${binlog.datasource.noob.host}")
    private String host;
    @Value("${binlog.datasource.noob.serviceId}")
    private Integer serviceId;
    @Value("${binlog.datasource.noob.delay.reconnect}")
    private Integer delayReconnect;


    public void init() {
        final AutoOpenReplicator aor = new AutoOpenReplicator();
        aor.setServerId(serviceId);
        aor.setHost(host);
        aor.setUser(user);
        aor.setPassword(password);
        aor.setAutoReconnect(true);
        aor.setDelayReconnect(delayReconnect);
        aor.setBinlogFileName("mysql-bin.000007");
        aor.setBinlogPosition(4L);
        aor.setBinlogEventListener(notificationListener);
        aor.start();
    }
}
