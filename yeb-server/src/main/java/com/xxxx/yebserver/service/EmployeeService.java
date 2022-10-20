package com.xxxx.yebserver.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxxx.yebserver.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.utils.RespPageBean;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 获取所有员工(分页)
      * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage,Integer size,Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     * @return
     */
    RespBean maxWorkID();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);
}
