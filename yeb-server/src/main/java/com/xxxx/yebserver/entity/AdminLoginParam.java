package com.xxxx.yebserver.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 管理员登录对象
 * @author Aquarius
 * @data 2022/11/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Accessors(chain = true)
@Tag(name = "AdminLogin对象",description = "")
public class AdminLoginParam {
    @Schema(description= "用户名",required = true)
    private String username;
    @Schema(description= "密码",required = true)
    private String password;
    @Schema(description = "验证码",required = true)
    private String code;
}
