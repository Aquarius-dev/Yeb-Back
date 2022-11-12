package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统消息
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */


@Data
@Accessors(chain = true)
@TableName("t_sys_msg")
@Tag(name = "SysMsg对象", description = "系统消息")
public class SysMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "消息id")
    private Integer mid;

    @Schema(description = "0表示群发消息")
    private Integer type;

    @Schema(description = "这条消息是给谁的")
    private Integer adminid;

    @Schema(description = "0 未读 1 已读")
    private Integer state;
}
