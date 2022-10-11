
package com.xxxx.yebserver.security.conmponent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/*
 * @Author: Aquarius
 * @Date: 2022-10-05 16:45:06
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-05 17:14:46
 * @Description: SpringSecurity白名单资源路径配置
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
