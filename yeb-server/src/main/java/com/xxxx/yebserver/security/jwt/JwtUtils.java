package com.xxxx.yebserver.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * @Author: Aquarius
 * @Date: 2022-10-02 17:10:42
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-02 23:41:33
 * @Description: Jwt工具类
 */
@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成Token
     * 
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.setSubject(userDetails.getUsername());
        claims.setIssuedAt(new Date());
        return generateToken(claims);
    }

    /**
     * 从Token中获取用户名
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 认证Token是否有效
     * 
     * @param token
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断Token是否失效
     * 
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从Token中过去失效时间
     * 
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断Token是否可以刷新
     * 
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新Token
     * 
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.setIssuedAt(new Date());
        return generateToken(claims);
    }

    /**
     * 从Token中获取荷载
     * 
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成Jwt Token
     * 
     * @param claims
     * @return
     */
    private String generateToken(Claims claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(key()).compact();
    }

    /**
     * 生成Token过期时间
     * 
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 加密secret
     * 
     * @return
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
