package com.xxxx.yebserver.controller;

/**
 * @Description: TODD
 * @Author aquarius
 * @Date 2022/10/11 15:47
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.yebserver.entity.Menu;
import com.xxxx.yebserver.entity.MenuRole;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.entity.Role;
import com.xxxx.yebserver.service.MenuRoleService;
import com.xxxx.yebserver.service.MenuService;
import com.xxxx.yebserver.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组 - 角色
 *
 * @author bing  @create 2021/1/16-下午4:25
 */
@RestController
@RequestMapping("/system/basic/permission")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;

    @Operation(summary = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @Operation(summary = "添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @Operation(summary = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @Operation(summary = "根据角色 id 查询菜单 id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>()
                        .eq("rid", rid))
                .stream().map(MenuRole::getMid)
                .collect(Collectors.toList());
    }

    @Operation(summary = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }

}

