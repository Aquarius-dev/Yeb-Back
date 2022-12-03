package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 在线聊天
 * @Author Aquarius
 * @Date 2022/11/30 20:07
 */
@Tag(name = "ChatController")
@RestController
@RequestMapping("/chat")
//@SecurityRequirement(name = "bearerAuth")
public class ChatController {
    @Autowired
    private AdminService adminService;

    @Operation(summary = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }
}
