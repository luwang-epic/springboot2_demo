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
@MapperScan(basePackages = "com.demo.multi_datasource_transaction.student.mapper", sqlSessionTemplateRef = "studentSqlSessionTemplate")
public class StudentDataSourceConfig {
    /**
     * 需要将我们的数据源统一交给我们全局xa事务管理
     */

    @Bean("studentDataSource")
    public DataSource studentDataSource(StudentConfig studentConfig) {
        // 1. 创建我们的xaDataSource
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(studentConfig.getUrl());
        mysqlXADataSource.setPassword(studentConfig.getPassword());
        mysqlXADataSource.setUser(studentConfig.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        // 2. 注册到我们全局事务上
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName(studentConfig.getUniqueResourceName());
        atomikosDataSourceBean.setMinPoolSize(studentConfig.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(studentConfig.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(studentConfig.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(studentConfig.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(studentConfig.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(studentConfig.getMaxIdleTime());

        return atomikosDataSourceBean;
    }

    /**
     * 创建我们的会话工厂SqlSessionFactory
     */
    @Bean(name = "studentSqlSessionFactory")
    public SqlSessionFactory studentSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

//因为有了全局的xa事务管理器，需要删除这个事务管理器，否则会报错
//    /**
//     * 创建事务管理器
//     */
//    @Bean("studentTransactionManager")
//    public DataSourceTransactionManager studentTransactionManager(@Qualifier("studentDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 创建会话模板SqlSession
     */
    @Bean("studentSqlSessionTemplate")
    public SqlSessionTemplate studentSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
