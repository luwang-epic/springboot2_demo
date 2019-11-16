package com.demo.multi_datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wanglu
 * @date 2019/11/14
 */
@Configuration
@MapperScan(basePackages = "com.demo.multi_datasource.student.mapper", sqlSessionTemplateRef = "studentSqlSessionTemplate")
public class StudentDataSourceConfig {
    /**
     * Configuration注解表示将StudentDataSourceConfig这个类变成StudentDataSourceConfig.xml文件
     * MapperScan注解表示扫包范围，该范围下的包都使用student数据源
     */

    @Bean("studentDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.student")
    public DataSource studentDataSource() {
        /*
         * ConfigurationProperties注解让spring知道创建数据源时使用配置中的哪个数据源
         * bean注解是将该数据源注册到spring中
         */
        return DataSourceBuilder.create().build();
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

    /**
     * 创建事务管理器
     */
    @Bean("studentTransactionManager")
    public DataSourceTransactionManager studentTransactionManager(@Qualifier("studentDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建会话模板SqlSession
     */
    @Bean("studentSqlSessionTemplate")
    public SqlSessionTemplate studentSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
