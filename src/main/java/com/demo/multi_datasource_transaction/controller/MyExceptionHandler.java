package com.demo.multi_datasource_transaction.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一的异常处理页面
 * @author wanglu
 * @date 2019/11/15
 */
@ControllerAdvice
public class MyExceptionHandler {
    /*
     * ControllerAdvice注解为异常切面类
     */

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public Map<String, String> exceptionHandler() {
        /*
         * ExceptionHandler注解表示该方法为异常时返回的内容，
         * ResponseBody该注解表示返回的为body体
         */
        Map<String, String> error = new HashMap<>();
        error.put("code", "500");
        error.put("msg", "运行时异常");
        return error;
    }

}
