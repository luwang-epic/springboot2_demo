package com.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 演示spring boot JdbcTemplate数据源的使用
 * @author wanglu
 * @date 2019/11/11
 */
@Service
public class StudentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addStudent(String name, Integer age) {
        int update = jdbcTemplate.update("insert into student(name, age) values(?, ?)", name, age);
        return update > 0;
    }

}
