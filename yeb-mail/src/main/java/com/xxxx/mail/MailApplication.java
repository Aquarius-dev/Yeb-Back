package com.xxxx.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Aquarius
 * @description Mail启动类
 * @create 2022/11/13
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
}