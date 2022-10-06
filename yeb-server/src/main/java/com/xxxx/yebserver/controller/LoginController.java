package com.xxxx.yebserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "登陆",description = "登陆之后返回Token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam,HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request); 
    }

    @Operation(summary = "当前用户信息",description = "获取当前用户信息")
    @PostMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }
    
    @Operation(summary = "退出登陆",description = "退出登陆")
    @PostMapping( "/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
