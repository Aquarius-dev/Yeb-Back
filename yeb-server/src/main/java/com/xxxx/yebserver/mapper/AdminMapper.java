package com.xxxx.yebserver.mapper;

import com.xxxx.yebserver.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有操作
     * @param id
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
