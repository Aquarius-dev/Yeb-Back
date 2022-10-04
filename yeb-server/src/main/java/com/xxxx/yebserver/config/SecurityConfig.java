/*
 * @Author: Aquarius
 * @Date: 2022-10-04 10:52:42
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-04 10:55:17
 * @Description: comment
 */
package com.xxxx.yebserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


