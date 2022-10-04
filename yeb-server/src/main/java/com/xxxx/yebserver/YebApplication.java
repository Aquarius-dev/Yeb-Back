/*
 * @Author: Aquarius
 * @Date: 2022-10-02 16:24:49
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-03 21:31:43
 * @Description: comment
 */
package com.xxxx.yebserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxxx.yebserver.mapper")
public class YebApplication{
    
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }

}