/*
 * @Author: Aquarius
 * @Date: 2022-10-06 20:40:07
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-07 10:27:37
 * @Description: comment
 */
package com.xxxx.yebserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.AdminLoginParam;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.service.AdminService;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
/*
 * @Author: Aquarius
 * @Date: 2022-10-03 08:49:59
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-06 11:05:19
 * @Description: 登陆处理类
 */
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "登陆",description = "登陆之后返回Token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam,HttpServletRequest request) {
        return adminService.login(adminLoginParam,request);
    }

    @Operation(summary = "当前用户信息",description = "获取当前用户信息")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setRoles(adminService.getRoles(admin.getId()));
        admin.setPassword(null);
        return admin;
    }
    
    @Operation(summary = "退出登陆",description = "退出登陆")
    @PostMapping( "/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
