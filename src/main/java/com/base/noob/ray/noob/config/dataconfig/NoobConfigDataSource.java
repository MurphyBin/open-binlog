package com.base.noob.ray.noob.config.dataconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.base.noob.ray.noob.database.mapper",sqlSessionFactoryRef = "sqlSessionFactoryNoob")
@Import(AbstractDataSource.class)
public class NoobConfigDataSource extends AbstractDataSource {

    @Value("${datasource.noob.jdbcUrl}")
    private String jdbcUrl;
    @Value("${datasource.noob.userName}")
    private String userName;
    @Value("${datasource.noob.password}")
    private String password;


    @Bean(name = "dataSource")
    public DataSource getLoanProDataSource() {
        HikariDataSource dataSource = this.getDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryNoob")
    public SqlSessionFactory getLoanProSqlSessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        //扫描mapper.xml
        String sqlMapperLocation = "classpath*:sqlMap/loanPro/*.xml";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(sqlMapperLocation));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("dataSource") DataSource ds) throws Exception {
        return new JdbcTemplate(ds);
    }
}
