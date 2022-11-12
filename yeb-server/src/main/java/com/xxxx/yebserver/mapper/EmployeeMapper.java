package com.xxxx.yebserver.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.yebserver.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 获取所有员工(分页)
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    Page<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);
}
