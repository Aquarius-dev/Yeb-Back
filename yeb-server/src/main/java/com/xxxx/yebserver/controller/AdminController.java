package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.entity.Role;
import com.xxxx.yebserver.service.AdminService;
import com.xxxx.yebserver.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Tag(name = "AdminController", description = "操作员")
@RestController
@RequestMapping("/system/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Operation(summary = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords) {
        return adminService.getAllAdmins(keywords);
    }

    @Operation(summary = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @Operation(summary = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @Operation(summary = "获取操作员所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @Operation(summary = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }
}
