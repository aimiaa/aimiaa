package org.ami.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.ami.anno.Log;
import org.ami.pojo.Dept;
import org.ami.pojo.Result;
import org.ami.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    @RequestMapping("/depts")
    public Result list(){
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping("/depts")
    public Result delete( Integer id){
        deptService.deleteById(id);
        log.info("删除部门数据"+id);
        return Result.success();
    }
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        deptService.add(dept);
        log.info("添加部门数据: " + dept.getName());
        return Result.success();
    }
    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改部门数据: " + dept.getName());
        return Result.success();
    }
    @GetMapping("/depts/{id}")
    public Result get(@PathVariable("id") Integer id){
        Dept dept = deptService.findById(id);
        log.info("查询部门数据: " + dept.getName());
        return Result.success(dept);
    }
}
