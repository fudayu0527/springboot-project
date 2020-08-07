package com.xiaojiangtun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 默认配置文件读取，用来映射我们在 application.properties 中的内容
 */
@Component
@ConfigurationProperties(prefix = "default")
public class DefaultProperties {

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DefaultProperties{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}