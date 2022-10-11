
package com.xxxx.yebserver.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

/*
 * @Author: Aquarius
 * @Date: 2022-10-07 09:55:23
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-07 10:09:08
 * @Description: comment
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Yeb API",version = "0.1"))
@SecurityScheme(name = "bearerAuth",type = SecuritySchemeType.HTTP,scheme = "Bearer",bearerFormat = "JWT")
public class OpenApi3Config {
//    @Bean
//    public OpenAPI customOpenAPI(){
//        return new OpenAPI().components(new Components()
//                .addSecuritySchemes("bearer-Jwt",
//                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
//    }
}
