package com.demo.multi_datasource_transaction.config;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wanglu
 * @date 2019/11/14
 */
@Configuration
@MapperScan(basePackages = "com.demo.multi_datasource_transaction.user.mapper", sqlSessionTemplateRef = "userSqlSessionTemplate")
public class UserDataSourceConfig {
    /**
     * Configuration注解表示将UserDataSourceConfig这个类变成UserDataSourceConfig.xml文件
     * MapperScan注解表示扫包范围，该范围下的包都使用user数据源
     */

    @Bean("userDataSource")
    public DataSource userDataSource(UserConfig userConfig) {
        // 1. 创建我们的xaDataSource
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(userConfig.getUrl());
        mysqlXADataSource.setPassword(userConfig.getPassword());
        mysqlXADataSource.setUser(userConfig.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        // 2. 注册到我们全局事务上
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName(userConfig.getUniqueResourceName());
        atomikosDataSourceBean.setMinPoolSize(userConfig.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(userConfig.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(userConfig.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(userConfig.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(userConfig.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(userConfig.getMaxIdleTime());

        return atomikosDataSourceBean;
    }

    /**
     * 创建我们的会话工厂SqlSessionFactory
     */
    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

//因为有了全局的xa事务管理器，需要删除这个事务管理器，否则会报错
//    /**
//     * 创建事务管理器
//     */
//    @Bean("userTransactionManager")
//    public DataSourceTransactionManager userTransactionManager(@Qualifier("userDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 创建会话模板SqlSession
     */
    @Bean("userSqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
