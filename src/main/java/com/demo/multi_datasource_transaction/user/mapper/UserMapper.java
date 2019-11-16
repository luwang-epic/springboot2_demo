package com.demo.multi_datasource_transaction.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 操作springboot_multi_datasource库下的user表
 * @author wanglu
 * @date 2019/11/14
 */
public interface UserMapper {
    @Insert("insert into user(name) values(#{name})")
    int insert(@Param("name") String name);
}
