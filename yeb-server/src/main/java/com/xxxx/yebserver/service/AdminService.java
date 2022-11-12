package com.xxxx.yebserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.AdminLoginParam;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登陆之后返回Token
     *
     * @param adminLoginParam
     * @param request
     * @return
     */
    RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户ID查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取所有操作员
     *
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
