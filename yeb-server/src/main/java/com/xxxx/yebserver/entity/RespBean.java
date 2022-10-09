package com.xxxx.yebserver.entity;

import lombok.*;

/*
 * @Author: Aquarius
 * @Date: 2022-10-03 08:35:17
 * @LastEditors: Aquraius
 * @LastEditTime: 2022-10-06 20:35:44
 * @Description: 统一返回结果
 */
@Data
public class RespBean{
    private long code;
    private String message;
    private Object obj;

    public RespBean(long code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
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