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
 * 培训表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */


@Data
@Accessors(chain = true)
@TableName("t_employee_train")
@Tag(name = "EmployeeTrain对象", description = "培训表")
public class EmployeeTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "员工编号")
    private Integer eid;

    @Schema(description = "培训日期")
    private LocalDateTime trainDate;

    @Schema(description = "培训内容")
    private String trainContent;

    @Schema(description = "备注")
    private String remark;
}
