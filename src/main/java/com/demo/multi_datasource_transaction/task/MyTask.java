package com.demo.multi_datasource_transaction.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 演示定时调度功能
 * @author wanglu
 * @date 2019/11/15
 */
@Component
public class MyTask {
    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        /*
            Scheduled注解为开启定时调度，fixedRate的单位为毫秒，表示每个多少毫秒运行一次该方法
         */
        System.out.println("现在时间： " + dataFormat.format(new Date()));
    }
}
