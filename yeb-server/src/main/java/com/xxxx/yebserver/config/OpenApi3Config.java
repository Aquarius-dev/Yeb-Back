
package com.xxxx.yebserver.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @Description: OpenApi 配置
 * @Author aquarius
 * @Date 2022/10/14 11:38
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
