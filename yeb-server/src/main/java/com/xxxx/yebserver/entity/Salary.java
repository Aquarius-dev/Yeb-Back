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
 * 工资表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_salary")
@Tag(name = "Salary对象", description = "工资表")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="基本工资")
    private Integer basicSalary;

    @Schema(description="奖金")
    private Integer bonus;

    @Schema(description="午餐补助")
    private Integer lunchSalary;

    @Schema(description="交通补助")
    private Integer trafficSalary;

    @Schema(description="应发工资")
    private Integer allSalary;

    @Schema(description="养老金基数")
    private Integer pensionBase;

    @Schema(description="养老金比率")
    private Double pensionPer;

    @Schema(description="启用时间")
    private LocalDateTime createDate;

    @Schema(description="医疗基数")
    private Integer medicalBase;

    @Schema(description="医疗保险比率")
    private Double medicalPer;

    @Schema(description="公积金基数")
    private Integer accumulationFundBase;

    @Schema(description="公积金比率")
    private Double accumulationFundPer;

    @Schema(description="名称")
    private String name;
}
