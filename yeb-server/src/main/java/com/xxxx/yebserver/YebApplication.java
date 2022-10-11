package com.xxxx.yebserver;

import com.xxxx.yebserver.security.conmponent.IgnoreUrlsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/*
 * @Author: Aquarius
 * @Date: 2022-10-02 16:24:49
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-06 20:57:48
 * @Description:  启动类
 */

@SpringBootApplication
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
@MapperScan("com.xxxx.yebserver.mapper")
public class YebApplication{
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }

}