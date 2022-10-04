/*
 * @Author: Aquarius
 * @Date: 2022-10-03 08:45:49
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-03 21:39:36
 * @Description: comment
 */
package com.xxxx.yebserver.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象",description = "")
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;

}
