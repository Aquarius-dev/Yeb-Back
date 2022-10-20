package com.xxxx.yebserver.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 分页返回的公共对象
 * @Author aquarius
 * @Date 2022/10/14 11:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据 list
     */
    private List<?> data;
}
