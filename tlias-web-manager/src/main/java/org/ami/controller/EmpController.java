package org.ami.controller;

import com.aliyun.oss.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.ami.anno.Log;
import org.ami.pojo.Emp;
import org.ami.pojo.EmpQueryParam;
import org.ami.pojo.PageResult;
import org.ami.pojo.Result;
import org.ami.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @GetMapping
    public Result Page(EmpQueryParam param) {
        log.info("分页查询员工数据，页码：{}，每页大小：{}, 姓名：{}, 性别：{}, 开始日期：{}, 结束日期：{}",
                param.getPage(), param.getPageSize(), param.getName(), param.getGender(), param.getBegin(), param.getEnd());
        PageResult<Emp> pageResult = empService.Page(param);
        return Result.success(pageResult);
    }
    @Log
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("添加员工数据: {}", emp);
        empService.addEmp(emp);
        return Result.success();
    }
    @Log
    @DeleteMapping()
    public Result deleteEmp(@RequestParam("ids") Integer[] ids){
        log.info("删除员工数据: {}", ids);
        empService.deleteEmp(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工数据: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping()
    public Result updateEmp(@RequestBody Emp emp){
        log.info("更新员工数据: {}", emp);
        empService.updateEmp(emp);
        return Result.success();
    }
    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result getAllEmp(){
        log.info("查询全部员工数据");
        List<Emp> empList = empService.getAllEmp();
        return Result.success(empList);
    }

}
