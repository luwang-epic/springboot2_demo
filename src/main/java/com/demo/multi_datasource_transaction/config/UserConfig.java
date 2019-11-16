package com.demo.multi_datasource_transaction.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 该类用于加载配置文件中的Student数据源配置
 * @author wanglu
 * @date 2019/11/14
 */
@ConfigurationProperties(prefix = "spring.datasource.user")
@Data
public class UserConfig {
    /*
     *  ConfigurationProperties注解用于将配置文件中的属性读取到该类中
     *          使用该注解需要在启动类上面加上@EnableConfigurationProperties(UserConfig.class)注解指明该类
     */

    private String url;
    private String username;
    private String password;
    private int maxPoolSize;
    private int minPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String uniqueResourceName;
}
