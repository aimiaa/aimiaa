package org.ami.controller;

import lombok.extern.slf4j.Slf4j;
import org.ami.anno.Log;
import org.ami.pojo.Clazz;
import org.ami.pojo.ClazzQueryParam;
import org.ami.pojo.PageResult;
import org.ami.pojo.Result;
import org.ami.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     *分页查询班级列表
     */
    @GetMapping
    public Result getClazzList(ClazzQueryParam param){
        log.info("分页查询班级列表,页码:{},每页大小：{}, 姓名：{}, 开始日期：{}, 结束日期：{}",param.getPage(),
                param.getPageSize(), param.getName(), param.getBegin(), param.getEnd());
        PageResult<Clazz> rageResult = clazzService.getClazzList(param);
        return Result.success(rageResult);
    }
    /**
     * 删除班级
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("删除班级数据: {}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }
    /**
     * 添加班级
     */
    @Log
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加班级数据: {}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }
    /**
     * 根据id查询回显数据
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级回显数据: {}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }
    /**
     * 修改班级
     */
    @Log
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("修改班级数据: {}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }
    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result getAllClazz(){
        log.info("查询所有班级数据");
        List<Clazz> clazzList = clazzService.getAllClazz();
        return Result.success(clazzList);
    }
}
