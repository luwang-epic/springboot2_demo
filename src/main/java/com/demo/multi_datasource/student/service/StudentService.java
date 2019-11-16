package com.demo.multi_datasource.student.service;

import com.demo.multi_datasource.student.mapper.StudentMapper;
import com.demo.multi_datasource.user.mapper.UserMapper;
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
     *      如果只有一个数据源不需要指定事务管理器，如果有多个数据源，需要告诉springboot使用哪个事务管理器
     * 事务的底层原理使用了aop技术 增强
     */
    @Transactional(transactionManager = "studentTransactionManager")
    public int addStudentWithTranslation(String name, Integer age) {
        int i = studentMapper.insert(name, age);
        int j = 1/ age;
        return i;
    }


}
