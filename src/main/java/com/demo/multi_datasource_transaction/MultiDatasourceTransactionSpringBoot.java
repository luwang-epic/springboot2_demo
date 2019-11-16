package com.demo.multi_datasource_transaction;

import com.demo.multi_datasource_transaction.config.StudentConfig;
import com.demo.multi_datasource_transaction.config.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;


/*

springboot启动流程：
    核心分为两个步骤：
        1. 创建SpringApplication对象
        2. 调用SpringApplication run实现启动同时返回当前的容器上下文
    分析流程：
        1. 创建SpringApplication对象Springboot容器初始化操作
        2. 获取当前应用启动类型  原理：判断当前classpath是否有加载我们的servlet类，返回servlet web启动方式
                WebApplicationType分为三种类型：
                    1. REACTIVE：响应式启动（Spring5新特性）
                    2. none：不会嵌入web容器启动（将SpringBoot项目放入外部服务器运行）
                    3. SERVLET：基于Web容器启动
        3. setInitializers 读取SpringBoot包下面的META-INF/spring.factories，获取到对应ApplicationContextInitializer装配到集合中
        4. setListeners 读取SpringBoot包下面的META-INF/spring.factories，获取到对应ApplicationListener装配到集合中
        5. deduceMainApplicationClass 获取当前运行的主函数
        6. 调用SpringApplication的run方法进行启动
        7. StopWatch stopWatch = new StopWatch(); 记录我们SpringBoot项目启动时间
        8. SpringApplicationRunListeners listeners = getRunListeners(args); 读取META-INF/spring.factories文件中的SpringApplicationRunListeners类型存入到集合中
        9. listeners.starting(); 循环调用监听starting方法
        10. ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
                listeners.environmentPrepared(environment); 读取配置文件到springboot容器中
        11. Banner printedBanner = printBanner(environment); 打印我们springboot banner
        12. context = createApplicationContext(); 创建springboot上下文
        13. refreshContext(context); 刷新上下文
        14. 开始创建tomcat容器
        15. 开始加载springMVC
        16. afterRefresh(context, applicationArguments); 定义一个空的模板给其他之类实现重写
        17. listeners.started(context); 使用广播和回调机制通知监听器springboot容器启动成功
        18. listeners.running(context); 使用广播和回调机制通知监听器springboot容器正在运行了
        19. return context; 最后返回当前的上下文

 */

/**
 * 6. 演示如何解决多数据源事务问题
 * @author wanglu
 * @date 2019/11/14
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({StudentConfig.class, UserConfig.class})
public class MultiDatasourceTransactionSpringBoot {
    /*
        EnableScheduling注解表示开启springboot的自动调度功能
     */

    /*
    多数据源分布式事务问题和正真正领域中产生的分布式事务问题不一样
        多数据源分布式事务的问题产生在同一个项目中，有多个不同的数据库连接
        分布式领域中事务因为系统拆分，每个服务都有自己的独立数据库

        多数据源项目中如何解决分布式事务问题：jta + atomikos，基于mysql的两阶段提交协议，生成一个全局的事务管理器
            需要加入jta-atomikos的jar包，springboot自身支持这个
        分布式领域中如何解决分布式事务问题：LCN, tcc， 基于mq解决分布式事务
     */


    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceTransactionSpringBoot.class, args);
    }
}
