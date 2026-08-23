package org.ami.controller;

import lombok.extern.slf4j.Slf4j;
import org.ami.anno.Log;
import org.ami.pojo.PageResult;
import org.ami.pojo.Result;
import org.ami.pojo.Student;
import org.ami.pojo.StudentQueryParam;
import org.ami.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 分页查询所有学生列表
     */
    @GetMapping
    public Result getStudentList(StudentQueryParam param) {
        log.info("分页查询班级列表,页码:{},每页大小：{}, 姓名：{}, 学历：{}, 班级id：{}",param.getPage(),
                param.getPageSize(), param.getName(), param.getDegree(), param.getClazzId());
        PageResult<Student> pageResult = studentService.getStudentList(param);
        return Result.success(pageResult);
    }
    /**
     * 删除多个学生信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable Integer[] ids) {
        log.info("删除学生信息,ids:{}",ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }
    /**
     * 添加学生信息
     */
    @Log
    @PostMapping
    public Result addStudent(@RequestBody Student student){
        log.info("添加学生信息: {}", student);
        studentService.addStudent(student);
        return Result.success();
    }
    /**
     * 查询单个学生
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询学生信息: {}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    /**
     * 修改学生信息
     */
    @Log
    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        log.info("修改学生信息: {}", student);
        studentService.updateStudent(student);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Short score){
        log.info("违纪处理,id:{},score:{}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
