package com.demo.multi_datasource_transaction.controller;

import com.demo.multi_datasource_transaction.student.mapper.StudentMapper;
import com.demo.multi_datasource_transaction.student.service.StudentService;
import com.demo.multi_datasource_transaction.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示多数据源下对数据库的操作api
 * @author wanglu
 * @date 2019/11/14
 */
@RestController
public class MultiDatasourceTransactionController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentService studentService;

    /** 使用value注解读取自定义的配置 **/
    @Value("${self_configuration.name}")
    private String name;

    @Value("${multi_environment.name}")
    private String environmentName;

    /**
     * 会使用springboot数据库的数据源来操作
     */
    @RequestMapping("/addStudentWithTranslation")
    public String addStudentWithTranslation(String name, Integer age) {
        return studentService.addStudentWithTranslation(name, age) > 0 ? "success" : "fail";
    }

    /**
     * 测试多环境配置文件
     */
    @RequestMapping("/exception")
    public int exception() {
        return 1/0;
    }

    /**
     * 测试读取自定义的一些配置
     */
    @RequestMapping("self_configuration")
    public String getSelfConfiguration() {
        return name;
    }

    /**
     * 测试多环境下读取不同的配置文件
     */
    @RequestMapping("readConfiguration")
    public String readConfiguration() {
        return environmentName;
    }

}
