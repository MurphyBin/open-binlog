package com.base.noob.ray.noob.config.dataconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Created by wanglei on 2017/6/8.
 */
@Configuration
public class DataSourceBaseProperties {

    @Value("${datasource.base.driverClassName}")
    private String driverClassName;

    @Value("${datasource.base.connectionTestQuery}")
    private String connectionTestQuery;

    @Value("${datasource.base.connectionTimeoutMs}")
    private Long connectionTimeoutMs;

    @Value("${datasource.base.idleTimeoutMs}")
    private Long idleTimeoutMs;

    @Value("${datasource.base.maxLifetimeMs}")
    private Long maxLifetimeMs;

    @Value("${datasource.base.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${datasource.base.minIdle}")
    private Integer minIdle;


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }

    public Long getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(Long connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public Long getIdleTimeoutMs() {
        return idleTimeoutMs;
    }

    public void setIdleTimeoutMs(Long idleTimeoutMs) {
        this.idleTimeoutMs = idleTimeoutMs;
    }

    public Long getMaxLifetimeMs() {
        return maxLifetimeMs;
    }

    public void setMaxLifetimeMs(Long maxLifetimeMs) {
        this.maxLifetimeMs = maxLifetimeMs;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }
}
