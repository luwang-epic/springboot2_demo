package com.demo.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 3. 演示如何连接数据库
 * @author wanglu
 * @date 2019/11/10
 */
@SpringBootApplication
public class JdbcSpringBoot {
    /**
        SpringBootApplication注解 等同于如下三个注解：EnableAutoConfiguration + ComponentScan + Configuration
            ComponentScan的扫包范围是启动类（AppSpringBoot）所在包以及其子包
            Configuration是一个配置注解，该注解相当于将类变成xml的配置文件，类似替换spring的bean.xml
     */
    public static void main(String[] args) {
        SpringApplication.run(JdbcSpringBoot.class, args);
    }
}
