package com.xxxx.yebserver.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职称等级表
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
@TableName("t_joblevel")
@Tag(name = "Joblevel对象", description = "职称等级表")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "职称名称")
    @Excel(name = "职称名称")
    @NonNull
    private String name;

    @Schema(description = "职称等级")
    private String titleLevel;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asis/Shanghai")
    private LocalDateTime createDate;

    @Schema(description = "是否启用")
    private Boolean enabled;
}
