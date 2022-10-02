package com.xxxx.yebserver.service.impl;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.mapper.AdminMapper;
import com.xxxx.yebserver.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
