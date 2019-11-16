package com.demo.mybatis;

import lombok.Data;

import java.util.Date;

/**
 * @author wanglu
 * @date 2019/11/12
 */
@Data
public class StudentDo {
    private int id;
    private String name;
    private String age;
    private Date birthday;
}
