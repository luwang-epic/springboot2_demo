package com.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api层
 * @author wanglu
 * @date 2019/11/12
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 默认为get请求，该方法的访问路径为：http://locahost:8080/addStudent?userName=&age=
     */
    @RequestMapping("/addStudent")
    public String addStudent(String userName, Integer age) {
        return studentService.addStudent(userName, age) ? "success" : "fail";
    }
}
