package com.ksl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * SpringBoot程序
 * @RestController 等同于 （@Controller 与 @ResponseBody）
 *
 * @author fudayu
 */
//@RestController
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 很关键：默认情况下 TaskScheduler 的 poolSize = 1
     *
     * @return 线程池
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

//    @GetMapping("/demo1")
//    public String demo1() {
//        return "Hello xiaojiangtun";
//    }
//
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("SpringBoot 默认提供的 Bean：");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            Arrays.stream(beanNames).forEach(System.out::println);
//        };
//    }
}
