package org.ami.controller;

import lombok.extern.slf4j.Slf4j;
import org.ami.pojo.ClazzOption;
import org.ami.pojo.JobOption;
import org.ami.pojo.Result;
import org.ami.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("查询员工数据");
        JobOption jobData = reportService.getEmpJobData();
        return Result.success(jobData);
    }
    @GetMapping("/empGenderData")
    public Result getMpGenderData(){
        log.info("查询员工性别数据");
        List<Map<String, Object>> mpGenderData = reportService.getEmpGenderCount();
        return Result.success(mpGenderData);
    }
    /**
     * 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("查询学员学历数据");
        List<Map<String,Object>> studentDegreeData = reportService.getStudentDegreeCount();
        return Result.success(studentDegreeData);
    }
    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("查询班级人数数据");
        ClazzOption clazzOption = reportService.getStudentCount();
        return Result.success(clazzOption);
    }

}
