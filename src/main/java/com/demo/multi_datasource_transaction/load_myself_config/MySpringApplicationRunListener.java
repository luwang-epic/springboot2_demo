package com.demo.multi_datasource_transaction.load_myself_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Properties;

/**
 * 通过编写自己加载自己的配置文件，了解springboot如何加载配置文件的
 *          springboot通过实现SpringApplicationRunListener接口的EventPublishingRunListener类来读取配置文件
 *          springboot会将EventPublishingRunListener类放在resource/META-INF/spring.factories文件中，
 *              通过org.springframework.boot.SpringApplicationRunListener=\ org.springframework.boot.context.event.EventPublishingRunListener
 *                      配置让springboot可以找到该类，然后加载读取
 *          我们也可以在自己的项目resource文件夹下加入META-INF/spring.factories文件（springboot会扫描所有jar包中的这个命名的文件，执行到不同步骤时加载相应的类）
 *              然后将我们自己的MySpringApplicationRunListener配置到这个文件中，在我们自己实现的类中读取配置，然后放入到springboot中，这样就可以模拟springboot加载过程了
 *              编写自己的MySpringApplicationRunListener时，可以参考springboot自己的EventPublishingRunListener实现类
 *          Ordered为加载的顺序，EventPublishingRunListener类中的order为0，正序排序，越小越先加载，如果想要我们的类先加载，那么需要将返回值设置为<0
 * @author wanglu
 * @date 2019/11/16
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;
    private final String[] args;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        System.out.println(">>>>>> MySpringApplicationRunListener starting <<<<<<");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println(">>>>>> MySpringApplicationRunListener environmentPrepared <<<<<<");

        //配置文件读取到程序中 思路需要自己将本地文件读取到程序中，然后放入到springboot容器
        Properties properties = new Properties();
        try {
            // 1. 读取我们的my.properties文件
            properties.load(this.getClass().getClassLoader().getResourceAsStream("my.properties"));
            // 2. 设置读取名称为my
            PropertySource propertySource = new PropertiesPropertySource("my", properties);
            // 3. 将资源添加到springboot项目中
            MutablePropertySources propertySources = environment.getPropertySources();
            // 4. 通过该api接口可以将配置文件读取到springboot项目中
            propertySources.addLast(propertySource);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(">>>>>> MySpringApplicationRunListener contextPrepared <<<<<<");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(">>>>>> MySpringApplicationRunListener contextLoaded <<<<<<");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(">>>>>> MySpringApplicationRunListener started <<<<<<");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println(">>>>>> MySpringApplicationRunListener running <<<<<<");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(">>>>>> MySpringApplicationRunListener failed <<<<<<");
    }

    @Override
    public int getOrder() {
        //让我们的类先加载
        return -1;
    }
}
