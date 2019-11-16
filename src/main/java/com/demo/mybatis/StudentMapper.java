package com.demo.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * spring boot 整合 mybatis 的 student mapper
 *      有xml和注解两种方式，本类是注解的方式
 * @author wanglu
 * @date 2019/11/12
 */
public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<StudentDo> findStudents();

    @Insert("insert into student(name, age) values(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
