package com.xiaojiangtun.task.xxljob;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 基于 xxl 定时任务 ，xxl-admin需要在github上下载官方的xxl部署到服务器上
 */
@Component
public class XxlTaskDemo {

    private static final Logger log = LoggerFactory.getLogger(XxlTaskDemo.class);

//    @Async
//    @Scheduled(cron = "0/1 * * * * *")
//    public void scheduled1() throws InterruptedException {
//        Thread.sleep(3000);
//        log.info("scheduled1 每1秒执行一次：{}", LocalDateTime.now());
//    }

//    @Scheduled(fixedRate = 1000)
//    public void scheduled2() throws InterruptedException {
//        Thread.sleep(3000);
//        log.info("scheduled2 每1秒执行一次：{}", LocalDateTime.now());
//    }

    @XxlJob("myXxlJob")
    public void scheduled3() throws InterruptedException {
        Thread.sleep(5000);
        log.info("myXxlJob 上次执行完毕后隔3秒继续执行：{}", LocalDateTime.now());
    }

}
