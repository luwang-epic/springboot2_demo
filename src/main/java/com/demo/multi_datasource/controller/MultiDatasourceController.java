package com.demo.multi_datasource.controller;

import com.demo.multi_datasource.student.mapper.StudentMapper;
import com.demo.multi_datasource.student.service.StudentService;
import com.demo.multi_datasource.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示多数据源下对数据库的操作api
 * @author wanglu
 * @date 2019/11/14
 */
@RestController
public class MultiDatasourceController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentService studentService;

    /**
     * 会使用springboot数据库的数据源来操作
     */
    @RequestMapping("/addStudent")
    public String addStudent(String name, Integer age) {
        return studentMapper.insert(name, age) > 0 ? "success" : "fail";
    }

    /**
     * 会使用springboot_multi_datasource数据库的数据源来操作
     */
    @RequestMapping("/addUser")
    public String addUser(String name) {
        return userMapper.insert(name) > 0 ? "success" : "fail";
    }

    /**
     * 会使用springboot数据库的数据源来操作
     */
    @RequestMapping("/addStudentWithTranslation")
    public String addStudentWithTranslation(String name, Integer age) {
        return studentService.addStudentWithTranslation(name, age) > 0 ? "success" : "fail";
    }
}
