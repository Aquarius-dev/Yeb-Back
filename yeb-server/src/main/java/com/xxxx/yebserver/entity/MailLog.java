package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 邮件日志
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_mail_log")
@Tag(name = "MailLog对象", description = "邮件日志")
public class MailLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="消息id")
    private String msgId;

    @Schema(description="接收员工id")
    private Integer eid;

    @Schema(description="状态（0:消息投递中 1:投递成功 2:投递失败）")
    private Integer status;

    @Schema(description="路由键")
    private String routeKey;

    @Schema(description="交换机")
    private String exchange;

    @Schema(description="重试次数")
    private Integer count;

    @Schema(description="重试时间")
    private LocalDateTime tryTime;

    @Schema(description="创建时间")
    private LocalDateTime createTime;

    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
