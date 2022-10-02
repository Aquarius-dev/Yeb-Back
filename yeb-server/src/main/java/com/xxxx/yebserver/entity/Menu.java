package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("path")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("图标")
    private String iconCls;

    @ApiModelProperty("是否保持激活")
    private Boolean keepAlive;

    @ApiModelProperty("是否要求权限")
    private Boolean requireAuth;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("是否启用")
    private Boolean enabled;
}
