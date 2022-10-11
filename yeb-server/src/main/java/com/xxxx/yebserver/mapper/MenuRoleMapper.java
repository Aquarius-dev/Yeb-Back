package com.xxxx.yebserver.mapper;

import com.xxxx.yebserver.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单角色中间表 Mapper 接口
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
