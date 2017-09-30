package com.base.noob.ray.noob.config.dataconfig;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wanglei on 2017/6/8.
 */
public class AbstractDataSource {

    @Autowired
    protected DataSourceBaseProperties dataSourceBaseProperties;

    public HikariDataSource getDataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dataSourceBaseProperties.getDriverClassName());
        ds.setConnectionTestQuery(dataSourceBaseProperties.getConnectionTestQuery());
        ds.setConnectionTimeout(dataSourceBaseProperties.getConnectionTimeoutMs());
        ds.setIdleTimeout(dataSourceBaseProperties.getIdleTimeoutMs());
        ds.setMaxLifetime(dataSourceBaseProperties.getMaxLifetimeMs());
        ds.setMaximumPoolSize(dataSourceBaseProperties.getMaxPoolSize());
        ds.setMinimumIdle(dataSourceBaseProperties.getMinIdle());
        return ds;
    }
}
