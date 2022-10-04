/*
 * @Author: Aquarius
 * @Date: 2022-10-03 08:49:59
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-03 09:37:55
 * @Description: comment
 */
package com.xxxx.yebserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.AdminLoginParam;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "登陆之后返回Token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam,HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request); 
    }

    @ApiOperation(value = "获取当前用户信息")
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
    
    @ApiOperation(value = "退出登陆") 
    @PostMapping( "/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
