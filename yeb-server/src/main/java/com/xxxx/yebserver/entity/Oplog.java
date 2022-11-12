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
 * 
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_oplog")
@Tag(name = "Oplog对象", description = "")
public class Oplog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description="添加日期")
    private LocalDateTime addDate;

    @Schema(description="操作内容")
    private String operate;

    @Schema(description="操作员ID")
    private Integer adminid;
}
