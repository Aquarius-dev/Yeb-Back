package com.xxxx.yebserver.controller;

import com.xxxx.yebserver.entity.Position;
import com.xxxx.yebserver.entity.RespBean;
import com.xxxx.yebserver.service.PositionService;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 职位 前端控制器
 * </p>
 *
 * @author Aquarius
 * @since 2022-10-02
 */
@RestController
@RequestMapping("/system/config/pos")
@SecurityRequirement(name = "bearerAuth")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Operation(summary = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPoisitions(){
        return positionService.list();
    }

    @Operation(summary = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Operation(summary = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @Operation(summary = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @Operation(summary = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
