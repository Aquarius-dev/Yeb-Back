package com.xxxx.yebserver.expection;

import com.xxxx.yebserver.entity.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Description: TODD
 * @Author aquarius
 * @Date 2022/10/11 15:12
 */
@RestControllerAdvice
public class GlobalExecption {
    @ExceptionHandler(SQLException.class)
    public RespBean mySqlExpection(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据,操作失败");
        }
        return RespBean.error("数据库异常,操作失败");
    }
}
