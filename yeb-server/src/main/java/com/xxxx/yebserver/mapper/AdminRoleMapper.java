package com.xxxx.yebserver.mapper;

import com.xxxx.yebserver.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.yebserver.entity.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 管理员角色中间表 Mapper 接口
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
