package com.xxxx.yebserver.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "name")
@Accessors(chain = true)
@TableName("t_department")
@Tag(name = "Department对象", description = "部门表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "部门名称")
    @Excel(name = "部门名称")
    @NonNull
    private String name;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "路径")
    private String depPath;

    @Schema(description = "是否启用")
    private Boolean enabled;

    @Schema(description = "是否上级")
    private Boolean isParent;

    @Schema(description = "子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @Schema(description = "返回结果")
    @TableField(exist = false)
    private Integer result;
}
