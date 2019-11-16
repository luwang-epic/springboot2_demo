package com.demo.multi_datasource_transaction.student.service;

import com.demo.multi_datasource_transaction.student.mapper.StudentMapper;
import com.demo.multi_datasource_transaction.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 提供对外服务
 * @author wanglu
 * @date 2019/11/14
 */
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * Transactional注解代表该方法启用了事务
     *      采用了xa事务管理器来统一管理所有的事务，因此不需要指明具体的事务管理器
     */
    @Transactional
    public int addStudentWithTranslation(String name, Integer age) {
        int i = studentMapper.insert(name, age);
        userMapper.insert(name);
        int j = 1/ age;
        return i;
    }


}
