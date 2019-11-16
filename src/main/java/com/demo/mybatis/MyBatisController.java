package com.demo.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 演示使用mybatis操作数据库的api
 * @author wanglu
 * @date 2019/11/12
 */
@RestController
public class MyBatisController {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/findStudents")
    public List<StudentDo> findStudents() {
        return studentMapper.findStudents();
    }
}
