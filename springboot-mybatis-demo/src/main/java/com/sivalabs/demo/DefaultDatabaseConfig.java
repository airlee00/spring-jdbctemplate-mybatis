package com.sivalabs.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan("${scan.mappers}") // -Dscan.mappers=com.sivalabs.demo.mappers
//@MapperScan("com.sivalabs.demo.mappers")
class DefaultDatabaseConfig  {
 
    @Autowired
    private ApplicationContext applicationContext;
 
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
    	HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://114.31.122.81:13306/cm");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername("cm");
        config.setPassword("123qwe");
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(20);
        config.addDataSourceProperty("cachePrepStmts","true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        return  new HikariDataSource(config);
    }
    @Bean(destroyMethod = "close")
    public DataSource dataSource2() {
    	PoolProperties poolProperties = new PoolProperties();
    	poolProperties.setUrl("jdbc:mysql://114.31.122.81:13306/cm");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setUsername("cm");
        poolProperties.setPassword("123qwe");
        poolProperties.setInitialSize(10);
        /* 기본값 100 - 동시에 사용할 수 있는 최대 커넥션 개수 */
        poolProperties.setMaxActive(10);
        /* 기본값 100 - 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수 */
        poolProperties.setMaxIdle(10);
        /* 기본값 10 - 최소한으로 유지할 커넥션 개수*/
        poolProperties.setMinIdle(10);
        /* 기본값 30000 - 커넥션 풀 안의 커넥션이 고갈됐을 때 커넥션 반납을 대기하는 시간(밀리초)*/
        return new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource2());
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }
 
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource2());
       // sessionFactoryBean.setTypeAliasesPackage("com.stunstun.spring.repository.entity");
       // sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:**/mappers/*.xml"));
        return sessionFactoryBean.getObject();
    }
}
