package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 职称等级表
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_joblevel")
@Tag(name = "Joblevel对象", description = "职称等级表")
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="职称名称")
    private String name;

    @Schema(description="职称等级")
    private String titleLevel;

    @Schema(description="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asis/Shanghai")
    private LocalDateTime createDate;

    @Schema(description="是否启用")
    private Boolean enabled;
}
