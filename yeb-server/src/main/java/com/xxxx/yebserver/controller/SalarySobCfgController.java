package com.xxxx.yebserver.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxxx.yebserver.entity.Employee;
import com.xxxx.yebserver.entity.RespBean;

import com.xxxx.yebserver.entity.Salary;
import com.xxxx.yebserver.service.EmployeeService;
import com.xxxx.yebserver.service.SalaryService;
import com.xxxx.yebserver.utils.RespPageBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author Aquarius
 * @description 员工套账
 * @create 2022-11-23 19:36
 */
@Tag(name = "SalarySobCfgController", description = "员工套账")
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {
    @Autowired
    private SalaryService salaryService;

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @Operation(summary = "获取所有员工账套(分页)")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeWithSalary(currentPage, size);
    }

    @Operation(summary = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salary_id", sid).eq("id", eid))) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

}
