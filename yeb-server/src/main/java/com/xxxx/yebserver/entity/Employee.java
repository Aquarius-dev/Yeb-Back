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
@Tag(name = "Employee对象", description = "员工表")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="员工姓名")
    private String name;

    @Schema(description="性别")
    private String gender;

    @Schema(description="出生日期")
    private LocalDateTime birthday;

    @Schema(description="身份证号")
    private String idCard;

    @Schema(description="婚姻状况")
    private String wedlock;

    @Schema(description="民族")
    private Integer nationId;

    @Schema(description="籍贯")
    private String nativePlace;

    @Schema(description="政治面貌")
    private Integer politicId;

    @Schema(description="邮箱")
    private String email;

    @Schema(description="电话号码")
    private String phone;

    @Schema(description="联系地址")
    private String address;

    @Schema(description="所属部门")
    private Integer departmentId;

    @Schema(description="职称ID")
    private Integer jobLevelId;

    @Schema(description="职位ID")
    private Integer posId;

    @Schema(description="聘用形式：劳动合同，劳务合同")
    private String engageForm;

    @Schema(description="最高学历")
    private String tiptopDegree;

    @Schema(description="所属专业")
    private String specialty;

    @Schema(description="毕业院校")
    private String school;

    @Schema(description="入职日期")
    private LocalDateTime beginDate;

    @Schema(description="在职状态")
    private String workState;

    @Schema(description="工号")
    private String workId;

    @Schema(description="合同期限")
    private Object contractTerm;

    @Schema(description="转正日期")
    private LocalDateTime conversionTime;

    @Schema(description="离职日期")
    private LocalDateTime notworkDate;

    @Schema(description="合同起始日期")
    private LocalDateTime beginContract;

    @Schema(description="合同终止日期")
    private LocalDateTime endContract;

    @Schema(description="工龄")
    private Integer workAge;

    @Schema(description="工资账套ID")
    private Integer salaryId;
}
