package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Menu;
import com.xxxx.yebserver.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@RestController
@RequestMapping("/system/cfg")
@SecurityRequirement(name = "bearerAuth")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Operation(summary = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}
