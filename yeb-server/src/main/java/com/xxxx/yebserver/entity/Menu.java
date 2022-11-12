package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */


@Data
@Accessors(chain = true)
@TableName("t_menu")
@Tag(name = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "url")
    private String url;

    @Schema(description = "path")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "菜单名")
    private String name;

    @Schema(description = "图标")
    private String iconCls;

    @Schema(description = "是否保持激活")
    private Boolean keepAlive;

    @Schema(description = "是否要求权限")
    private Boolean requireAuth;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "是否启用")
    private Boolean enabled;

    @Schema(description = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @Schema(description = "角色列表")
    @TableField(exist = false)
    private List<Role> roles;
}
