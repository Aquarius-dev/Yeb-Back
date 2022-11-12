package com.xxxx.yebserver.service;

import com.xxxx.yebserver.entity.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yebserver.entity.RespBean;

/**
 * <p>
 * 菜单角色中间表 服务类
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface MenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
