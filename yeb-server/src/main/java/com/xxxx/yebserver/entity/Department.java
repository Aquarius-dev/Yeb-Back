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
 * 部门表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_department")
@Tag(name = "Department对象", description = "部门表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="部门名称")
    private String name;

    @Schema(description="父id")
    private Integer parentId;

    @Schema(description="路径")
    private String depPath;

    @Schema(description="是否启用")
    private Boolean enabled;

    @Schema(description="是否上级")
    private Boolean isParent;
}
