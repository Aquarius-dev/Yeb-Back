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
 * 员工表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_employee")
@ApiModel(value = "Employee对象", description = "员工表")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("出生日期")
    private LocalDateTime birthday;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("婚姻状况")
    private String wedlock;

    @ApiModelProperty("民族")
    private Integer nationId;

    @ApiModelProperty("籍贯")
    private String nativePlace;

    @ApiModelProperty("政治面貌")
    private Integer politicId;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("联系地址")
    private String address;

    @ApiModelProperty("所属部门")
    private Integer departmentId;

    @ApiModelProperty("职称ID")
    private Integer jobLevelId;

    @ApiModelProperty("职位ID")
    private Integer posId;

    @ApiModelProperty("聘用形式：劳动合同，劳务合同")
    private String engageForm;

    @ApiModelProperty("最高学历")
    private String tiptopDegree;

    @ApiModelProperty("所属专业")
    private String specialty;

    @ApiModelProperty("毕业院校")
    private String school;

    @ApiModelProperty("入职日期")
    private LocalDateTime beginDate;

    @ApiModelProperty("在职状态")
    private String workState;

    @ApiModelProperty("工号")
    private String workId;

    @ApiModelProperty("合同期限")
    private Object contractTerm;

    @ApiModelProperty("转正日期")
    private LocalDateTime conversionTime;

    @ApiModelProperty("离职日期")
    private LocalDateTime notworkDate;

    @ApiModelProperty("合同起始日期")
    private LocalDateTime beginContract;

    @ApiModelProperty("合同终止日期")
    private LocalDateTime endContract;

    @ApiModelProperty("工龄")
    private Integer workAge;

    @ApiModelProperty("工资账套ID")
    private Integer salaryId;
}
