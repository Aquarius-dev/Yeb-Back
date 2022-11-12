package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Joblevel;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.service.JoblevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 职称等级表 前端控制器
 * </p>
 *
 * @author Aquarius
 * @date 2022-10-02
 */
@Tag(name = "JoblevelController",description = "职称等级表")
@RestController
@RequestMapping("/system/basic/joblevel")
@SecurityRequirement(name = "bearerAuth")
public class JoblevelController {

    @Autowired
    private JoblevelService joblevelService;

    @Operation(summary = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @Operation(summary = "添加职称")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @Operation(summary = "更新职称")
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @Operation(summary = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @Operation(summary = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deletaJoblevelByIds(@RequestBody Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
