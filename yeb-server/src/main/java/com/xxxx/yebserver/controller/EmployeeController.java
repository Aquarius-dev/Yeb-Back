package com.xxxx.yebserver.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxxx.yebserver.entity.*;
import com.xxxx.yebserver.service.*;
import com.xxxx.yebserver.utils.RespPageBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Tag(name = "EmployeeController", description = "员工")
@RestController
@RequestMapping("/employee/basic")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PoliticsStatusService politicsStatusService;

    @Autowired
    private JoblevelService joblevelService;

    @Autowired
    private NationService nationService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Operation(summary = "获取所有员工(分页)")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }


    @Operation(summary = "获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @Operation(summary = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @Operation(summary = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @Operation(summary = "获取所有职位")
    @GetMapping("/Positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @Operation(summary = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @Operation(summary = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.maxWorkID();
    }

    @Operation(summary = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @Operation(summary = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @Operation(summary = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @Operation(summary = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        // 导出所有
        List<Employee> list = employeeService.getEmployee(null);
        // HSSF 03版兼容性强 XSSF 07版
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out = null;
        try {
            response.setHeader("content-type", "application/octet-stream");
            // 防止文中乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Operation(summary = "导入员工")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file) {
        // 导入参数
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);

        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusesList = politicsStatusService.list();
        List<Department> departmentsList = departmentService.list();
        List<Joblevel> joblevelsList = joblevelService.list();
        List<Position> positionsList = positionService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            list.forEach(employee -> {
                // indexOf 在字符串中寻找参数字符串第一次出现的位置并返回该位置。
                // 民族 id
                // 获取 Nation 的 name, 通过 name 获取对应的下标，通过下标获取整完的对象，通过对象获取 id,
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                // 政治 id
                employee.setPoliticId(politicsStatusesList.get(politicsStatusesList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                // 部门 id
                employee.setDepartmentId(departmentsList.get(departmentsList.indexOf(new Department(employee.getDepartment().getName()))).getId());
                // 职称 id
                employee.setJobLevelId(joblevelsList.get(joblevelsList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                // 职位 id
                employee.setPosId(positionsList.get(positionsList.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(list)) {
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }
}

