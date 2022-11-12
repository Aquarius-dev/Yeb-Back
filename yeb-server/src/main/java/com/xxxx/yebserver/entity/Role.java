package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_role")
@Tag(name = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="名称")
    private String name;

    @Schema(description="角色名称")
    private String nameZh;
}
