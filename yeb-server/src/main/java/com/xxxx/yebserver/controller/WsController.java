package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author Aquarius
 * @description websocket在线聊天
 * @create 2022-11-24 20:33
 */
@Controller
//@SecurityRequirement(name = "bearerAuth")
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {
        Admin admin = (Admin) authentication.getPrincipal();//获取当前用户
        chatMsg.setFrom(admin.getUsername());// 登录用户
        chatMsg.setFormNickName(admin.getName());//显示用户名
        chatMsg.setData(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);

    }
}
