package com.xxxx.yebserver.mapper;

import com.xxxx.yebserver.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户adminId查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
