package com.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
   SpringBoot 非常简单， 简化传统方式使用xml配置形式

   SpringBoot核心理念：
    1. 能够实现帮助开发者快速的整合第三方框架（Spring，Mybatis，hibernate等）
        原理：maven依赖封装整合和自定义的starter
    2. 完全去除xml配置，采用注解形式
        原理：SpringBoot其实根据Spring体系原生的注解实现包装
    3. 不需要外部容器，内嵌入服务器（Tomcat）
        原理：Java语言创建tomcat服务器，让其将本地class文件交给tomcat加载

 */

/**
 * 2. 第二种启动方式
 * @author wanglu
 * @date 2019/11/10
 */
@SpringBootApplication
public class HelloSpringBoot {
    /**
        SpringBootApplication注解 等同于如下三个注解：EnableAutoConfiguration + ComponentScan + Configuration
            ComponentScan的扫包范围是启动类（AppSpringBoot）所在包以及其子包
            Configuration是一个配置注解，该注解相当于将类变成xml的配置文件，类似替换spring的bean.xml

        使用SpringBootApplication注解来代替第一种启动方式中的EnableAutoConfiguration和ComponentScan注解
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBoot.class, args);
    }
}
