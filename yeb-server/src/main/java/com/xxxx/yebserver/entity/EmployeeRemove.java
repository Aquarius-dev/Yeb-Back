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
 * 员工调动表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_employee_remove")
@ApiModel(value = "EmployeeRemove对象", description = "员工调动表")
public class EmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工id")
    private Integer eid;

    @ApiModelProperty("调动后部门id")
    private Integer afterDepid;

    @ApiModelProperty("调动后职位")
    private String afterJobid;

    @ApiModelProperty("调动日期")
    private LocalDateTime removeDate;

    @ApiModelProperty("调动原因")
    private String reason;

    @ApiModelProperty("备注")
    private String remark;
}
