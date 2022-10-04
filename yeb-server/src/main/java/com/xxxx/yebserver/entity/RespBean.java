/*
 * @Author: Aquarius
 * @Date: 2022-10-03 08:35:17
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-03 08:39:58
 * @Description: comment
 */
package com.xxxx.yebserver.entity;

import lombok.*;

@Data
public class RespBean{
    private long code;
    private String message;
    private Object object;

    public RespBean(long code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}