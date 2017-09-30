package com.base.noob.ray.noob.NoobThread;

import com.base.noob.ray.noob.database.dao.NoobConfigDAO;
import com.base.noob.ray.noob.database.dto.NoobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class NoobUpdate {
    private static final String NAME = "NAME-NOOBCONFIG-UPDATE-THREAD";
    private final AtomicBoolean running = new AtomicBoolean(false);

    @Autowired
    private NoobConfigDAO noobConfigDAO;

    private UpdateImpl update;



    public void init() {

        update = new UpdateImpl();
        update.start();
    }


    class UpdateImpl implements update, Runnable {
        @Override
        public void start() {
            if (!running.compareAndSet(false, true)) {
                return;
            }
            Thread thread = new Thread(this);
            thread.setName(NAME);
            thread.start();
        }

        @Override
        public void end() {
            if (!running.compareAndSet(true, false)) {
                return;
            }
        }

        @Override
        public void run() {
            while (running.get()) {
                try {
                    NoobConfig noobConfig = noobConfigDAO.query(2L);
                    noobConfig.setContent(String.valueOf(UUID.randomUUID()));
                    noobConfig.setModTime(new Date());
                    noobConfigDAO.updateNoobConfig(noobConfig);

                    log.error("Thread update table ---------------  name: {}", NAME);
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        }
    }


    interface update {
        public void start();
        public void end();
    }
}
