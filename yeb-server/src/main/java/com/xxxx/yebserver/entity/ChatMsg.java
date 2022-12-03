package com.xxxx.yebserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Aquarius
 * @description 在线聊天消息
 * @create 2022-11-24 20:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {
    private String from;
    private String to;
    private String content;
    private LocalDateTime data;
    private String formNickName;
}
