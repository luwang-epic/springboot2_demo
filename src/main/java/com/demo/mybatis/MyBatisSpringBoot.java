package com.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 4. 演示如何使用mybatis连接数据库
 * @author wanglu
 * @date 2019/11/10
 */
@SpringBootApplication
@MapperScan("com.demo.mybatis")
public class MyBatisSpringBoot {
    /**
        SpringBootApplication注解 等同于如下三个注解：EnableAutoConfiguration + ComponentScan + Configuration
            ComponentScan的扫包范围是启动类（AppSpringBoot）所在包以及其子包
            Configuration是一个配置注解，该注解相当于将类变成xml的配置文件，类似替换spring的bean.xml

        MapperScan注解 用于spring boot和mybatis整合时扫描mapper类， 如果没有该注解，会报找不到mapper错误
     */
    public static void main(String[] args) {
        SpringApplication.run(MyBatisSpringBoot.class, args);
    }
}
