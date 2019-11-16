package com.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. 演示spring boot的第一个接口使用
 * @author wanglu
 * @date 2019/11/10
 */

@RestController
@EnableAutoConfiguration
@ComponentScan("com.demo.service")
public class HelloService {
    /**
     *
        RestController注解 表示当前类所有定义好的沙发理个发，统一都返回json
                     相当于spring mvc的@Controller和@ResponseBody同时使用
        EnableAutoConfiguration注解 作用实际上是启动springMVC,但是启动是扫包范围只在HelloService类，因此需要加上ComponentScan注解
        ComponentScan注解 增加扫包范围，使得com.demo.service包下的api也可以正确加载
     */

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloService.class, args);
    }
}
