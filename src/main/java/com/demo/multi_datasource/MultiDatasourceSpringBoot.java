package com.demo.multi_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 5. 演示如何连接多数据源
 * @author wanglu
 * @date 2019/11/14
 */
@SpringBootApplication
public class MultiDatasourceSpringBoot {

    /*
    多数据源如何定位自己数据源：
        1. 分包名，指定数据源适用的包名 推荐采用
        2. 注解形式，在对应的方法上面加上类似@Datasource(数据源名称)的注解，指定需要使用的数据源， 不推荐

    多数据源分布式事务问题和正真正领域中产生的分布式事务问题不一样
        多数据源分布式事务的问题产生在同一个项目中，有多个不同的数据库连接
        分布式领域中事务因为系统拆分，每个服务都有自己的独立数据库

        多数据源项目中如何解决分布式事务问题：jta + atomikos，基于mysql的两阶段提交协议，生成一个全局的事务管理器
            需要加入jta-atomikos的jar包，springboot自身支持这个
        分布式领域中如何解决分布式事务问题：LCN, tcc， 基于mq解决分布式事务
     */


    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceSpringBoot.class, args);
    }
}
