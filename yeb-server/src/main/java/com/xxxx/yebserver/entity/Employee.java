package com.xxxx.yebserver.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DecimalStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */


@Data
@Accessors(chain = true)
@TableName("t_employee")
@Tag(name = "Employee对象", description = "员工表")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "员工姓名")
    @Excel(name = "员工姓名")
    private String name;

    @Schema(description = "性别")
    @Excel(name = "性别")
    private String gender;

    @Schema(description = "出生日期")
    @Excel(name = "出生日期",width = 20,format = "yyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDate birthday;

    @Schema(description = "身份证号")
    @Excel(name = "身份证",width = 30)
    private String idCard;

    @Schema(description = "婚姻状况")
    @Excel(name = "婚姻状况")
    private String wedlock;

    @Schema(description = "民族")
    private Integer nationId;

    @Schema(description = "籍贯")
    @Excel(name = "籍贯")
    private String nativePlace;

    @Schema(description = "政治面貌")
    private Integer politicId;

    @Schema(description = "邮箱")
    @Excel(name = "邮箱",width = 30)
    private String email;

    @Schema(description = "电话号码")
    @Excel(name = "电话号码",width = 15)
    private String phone;

    @Schema(description = "联系地址")
    @Excel(name = "联系地址",width = 40)
    private String address;

    @Schema(description = "所属部门")
    private Integer departmentId;

    @Schema(description = "职称ID")
    private Integer jobLevelId;

    @Schema(description = "职位ID")
    private Integer posId;

    @Schema(description = "聘用形式：劳动合同，劳务合同")
    @Excel(name = "聘用形式")
    private String engageForm;

    @Schema(description = "最高学历")
    @Excel(name = "最高学历")
    private String tiptopDegree;

    @Schema(description = "所属专业")
    @Excel(name = "所属专业",width = 20)
    private String specialty;

    @Schema(description = "毕业院校")
    @Excel(name = "毕业院校",width = 20)
    private String school;

    @Schema(description = "入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "入职日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate beginDate;

    @Schema(description = "在职状态")
    @Excel(name = "在职状态")
    private String workState;

    @Schema(description = "工号")
    @Excel(name = "工号")
    private String workId;

    @Schema(description = "合同期限")
    @Excel(name = "合同期限",suffix = "年")
    private Double contractTerm;

    @Schema(description = "转正日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "转正日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate conversionTime;

    @Schema(description = "离职日期")
    @TableField("notWork_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate notworkDate;

    @Schema(description = "合同起始日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "合同起始日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate beginContract;

    @Schema(description = "合同终止日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "合同终止日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate endContract;

    @Schema(description = "工龄")
    @Excel(name = "工龄")
    private Integer workAge;

    @Schema(description = "工资账套ID")
    private Integer salaryId;

    @Schema(description = "民族")
    @TableField(exist = false)
    @ExcelEntity(name = "民族")
    private Nation nation;

    @Schema(description = "政治面貌")
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;


    @Schema(description = "部门")
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private Department department;

    @Schema(description = "职称")
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;

    @Schema(description = "职位")
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private Position position;

    @Schema(description = "工资套帐")
    @TableField(exist = false)
    private Salary salary;

}
