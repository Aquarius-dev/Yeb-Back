
package com.xxxx.yebserver.service;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.RespBean;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登陆之后返回Token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

}
