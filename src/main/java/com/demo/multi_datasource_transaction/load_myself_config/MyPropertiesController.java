package com.demo.multi_datasource_transaction.load_myself_config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取自己的配置
 * @author wanglu
 * @date 2019/11/16
 */
@RestController
public class MyPropertiesController {

    @Value("${test.name}")
    private String value;

    @RequestMapping("/myProperties")
    public String myProperties() {
        return String.format("我自己的配置是：%s", value);
    }
}
