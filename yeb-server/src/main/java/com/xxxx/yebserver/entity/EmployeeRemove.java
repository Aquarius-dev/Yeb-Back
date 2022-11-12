package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工调动表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */


@Data
@Accessors(chain = true)
@TableName("t_employee_remove")
@Tag(name = "EmployeeRemove对象", description = "员工调动表")
public class EmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "员工id")
    private Integer eid;

    @Schema(description = "调动后部门id")
    private Integer afterDepid;

    @Schema(description = "调动后职位")
    private String afterJobid;

    @Schema(description = "调动日期")
    private LocalDateTime removeDate;

    @Schema(description = "调动原因")
    private String reason;

    @Schema(description = "备注")
    private String remark;
}
