/*
 * @Author: Aquarius
 * @Date: 2022-10-06 20:40:07
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-07 09:44:14
 * @Description: comment
 */

package com.xxxx.yebserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.utils.CaptchaUtil;
import com.xxxx.yebserver.entity.*;
import com.xxxx.yebserver.mapper.AdminMapper;
import com.xxxx.yebserver.mapper.AdminRoleMapper;
import com.xxxx.yebserver.mapper.RoleMapper;
import com.xxxx.yebserver.security.jwt.JwtUtils;
import com.xxxx.yebserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Author: Aquarius
 * @Date: 2022-10-02 16:24:49
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-06 20:36:53
 * @Description: 管理员服务类
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登陆之后返回Token
     *
     * @param adminLoginParam
     * @param request
     * @return
     */
    @Override
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request) {
        if (!CaptchaUtil.ver(adminLoginParam.getCode(), request)) {
            // 清除ession中的验证码
            CaptchaUtil.clear(request);
            return RespBean.error("验证码不正确");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminLoginParam.getUsername());

        if (null == userDetails || !passwordEncoder.matches(adminLoginParam.getPassword(), userDetails.getPassword())) {
            return RespBean.error("用户名密码不正确");
        }

        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>(6);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登陆成功", tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
    }

    /**
     * 根据用户ID查询角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取所有操作员
     *
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return adminMapper.getAllAdmins(id, keywords);
    }

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("admin_Id", adminId));
        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = baseMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 比对密码，判断旧密码是否正确
        if (encoder.matches(oldPass, admin.getPassword())) {
            // 设置密码，并加密
            admin.setPassword(encoder.encode(pass));
            int result = baseMapper.updateById(admin);
            if (1 == result) {
                return RespBean.success("更新成功！");
            }
        }
        return RespBean.error("更新失败！");
    }
}
