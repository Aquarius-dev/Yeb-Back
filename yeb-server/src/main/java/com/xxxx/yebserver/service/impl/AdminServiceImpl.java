
package com.xxxx.yebserver.service.impl;

import com.xxxx.yebserver.entity.Admin;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.mapper.AdminMapper;
import com.xxxx.yebserver.security.jwt.JwtUtils;
import com.xxxx.yebserver.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登陆之后返回Token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

        if(null != userDetails || !passwordEncoder.matches(password, userDetails.getPassword())){
            return RespBean.error("用户名密码不正确");
        }
        
        if(userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtUtils.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>(6);
        tokenMap.put("token",token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登陆成功",tokenMap);
    }

     /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

}
