package org.ami.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.ami.mapper.StudentMapper;
import org.ami.pojo.PageResult;
import org.ami.pojo.Student;
import org.ami.pojo.StudentQueryParam;
import org.ami.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> getStudentList(StudentQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Student> studentList = studentMapper.getStudentList(param);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteStudent(Integer[] ids) {
        studentMapper.deleteStudentById(ids);
    }

    @Override
    public void addStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }

    @Override
    public Student getInfo(Integer id) {
        Student student = studentMapper.getStuddentByID(id);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    @Override
    public void violation(Integer id, Short score) {
        studentMapper.violation(id, score);
    }
}
