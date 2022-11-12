package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Department;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@RestController
@RequestMapping("/system/basic/department")
@SecurityRequirement(name = "bearerAuth")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @Operation(summary = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        return departmentService.addDep(dep);
    }

    @Operation(summary = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id) {
        return departmentService.deleteDep(id);
    }

}
