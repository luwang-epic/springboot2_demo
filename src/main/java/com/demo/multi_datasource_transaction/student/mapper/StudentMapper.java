package com.demo.multi_datasource_transaction.student.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 操作springboot库下的student表
 * @author wanglu
 * @date 2019/11/14
 */
public interface StudentMapper {
    @Insert("insert into student(name, age) values(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
