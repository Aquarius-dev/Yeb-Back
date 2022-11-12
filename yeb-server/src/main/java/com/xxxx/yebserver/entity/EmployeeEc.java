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
 * 员工奖罚表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_employee_ec")
@Tag(name = "EmployeeEc对象", description = "员工奖罚表")
public class EmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="员工编号")
    private Integer eid;

    @Schema(description="奖罚日期")
    private LocalDateTime ecDate;

    @Schema(description="奖罚原因")
    private String ecReason;

    @Schema(description="奖罚分")
    private Integer ecPoint;

    @Schema(description="奖罚类别，0：奖，1：罚")
    private Byte ecType;

    @Schema(description="备注")
    private String remark;
}
