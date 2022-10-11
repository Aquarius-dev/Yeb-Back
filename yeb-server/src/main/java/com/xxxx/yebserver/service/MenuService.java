package com.xxxx.yebserver.service;

import com.xxxx.yebserver.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
public interface MenuService extends IService<Menu> {

    /**
     * 通过用户ID查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
