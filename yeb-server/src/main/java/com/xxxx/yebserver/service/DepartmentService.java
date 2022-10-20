package com.xxxx.yebserver.service;

import com.xxxx.yebserver.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yebserver.entity.RespBean;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return
     */
    RespBean addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
