package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工奖罚表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_employee_ec")
@ApiModel(value = "EmployeeEc对象", description = "员工奖罚表")
public class EmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工编号")
    private Integer eid;

    @ApiModelProperty("奖罚日期")
    private LocalDateTime ecDate;

    @ApiModelProperty("奖罚原因")
    private String ecReason;

    @ApiModelProperty("奖罚分")
    private Integer ecPoint;

    @ApiModelProperty("奖罚类别，0：奖，1：罚")
    private Byte ecType;

    @ApiModelProperty("备注")
    private String remark;
}
