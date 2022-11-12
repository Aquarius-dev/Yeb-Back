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
 * 管理员角色中间表
 * </p>
 *
 * @author Aquarius
 * @date 2022/10/02
 */


@Data
@Accessors(chain = true)
@TableName("t_admin_role")
@Tag(name = "AdminRole对象", description = "管理员角色中间表")
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    private Integer adminId;

    @Schema(description = "权限id")
    private Integer rid;
}
