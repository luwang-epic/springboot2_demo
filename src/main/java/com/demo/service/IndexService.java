package com.demo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于演示spring boot的第二个接口
 * @author wanglu
 * @date 2019/11/10
 */
@RestController
public class IndexService {
    /*
        RestController注解是对@Controller和@ResponseBody两个注解的封装，就是返回json格式的数据，为写微服务接口作铺垫
     */

    @RequestMapping("/")
    public Map<String, String> index() {
        Map<String, String> data = new HashMap<>();
        data.put("code", "200");
        data.put("msg", "success");
        return data;
    }



}
