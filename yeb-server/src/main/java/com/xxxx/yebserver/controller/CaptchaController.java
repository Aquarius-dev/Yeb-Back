package com.xxxx.yebserver.controller;

import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODD
 * @Author aquarius
 * @Date 2022/10/9 10:08
 */

@Tag(name = "CaptchaController",description = "验证码")
@RestController
public class CaptchaController {

    @Operation(summary = "获取验证码",description = "验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request,response);
    }
}
