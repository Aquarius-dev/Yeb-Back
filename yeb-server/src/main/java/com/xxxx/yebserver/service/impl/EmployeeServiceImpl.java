package com.xxxx.yebserver.service.impl;

import com.xxxx.yebserver.entity.Employee;
import com.xxxx.yebserver.mapper.EmployeeMapper;
import com.xxxx.yebserver.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
