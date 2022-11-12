/*
 * @Author: Aquarius
 * @Date: 2022-10-02 16:24:49
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-03 08:45:21
 * @Description: comment
 */
package com.xxxx.yebserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xxxx.yebserver.config.CustomAuthorityDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理员表
 * @author Aquarius
 * @date 2022/10/02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_admin")
@Tag(name = "Admin对象", description = "管理员表")
public class Admin implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "住宅电话")
    private String telephone;

    @Schema(description = "联系地址")
    private String address;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户头像")
    private String userFace;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "是否启用1 0")
    @Getter(AccessLevel.NONE)
    private Boolean enabled;

    @Schema(description = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserialize.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
