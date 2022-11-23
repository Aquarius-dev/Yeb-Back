package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.entity.Salary;
import com.xxxx.yebserver.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 工资表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Tag(name = "SalaryController", description = "工资表控制器")
@RestController
@RequestMapping("/salary/sob")
@SecurityRequirement(name = "bearerAuth")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @Operation(summary = "获取员工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalarys() {
        return salaryService.list();
    }

    @Operation(summary = "添加工资账套")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Operation(summary = "删除工资账套")
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable Integer id) {
        if (salaryService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


    @Operation(summary = "更新工资账套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
