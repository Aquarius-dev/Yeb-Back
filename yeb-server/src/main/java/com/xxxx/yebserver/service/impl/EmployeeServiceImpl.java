package com.xxxx.yebserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.yebserver.entity.Employee;
import com.xxxx.yebserver.entity.MailLog;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.mapper.EmployeeMapper;
import com.xxxx.yebserver.mapper.MailLogMapper;
import com.xxxx.yebserver.service.EmployeeService;
import com.xxxx.yebserver.utils.MailConstants;
import com.xxxx.yebserver.utils.RespPageBean;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private MailLogMapper mailLogMapper;

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page = new Page<>(currentPage, size);
        Page<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        return new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
    }

    /**
     * 获取工号:添加员工工号是当前最大工号+1
     *
     * @return
     */
    @Override
    public RespBean maxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(work_id)"));
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(work_id)").toString()) + 1));
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();// 合同开始时间
        LocalDate endContract = employee.getEndContract();// 合同结束时间
        // 计算 两个日期相差多少天
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            //  获取当前行添加员工记录
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            // 数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTTING_KEY_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MEG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
            // 把设置的数据插入表中
            mailLogMapper.insert(mailLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTTING_KEY_NAME, emp,
                    new CorrelationData(msgId));
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败");
    }

    /**
     * 查询员工
     *
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }
}