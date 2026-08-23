package org.ami.service;

import org.ami.pojo.PageResult;
import org.ami.pojo.Student;
import org.ami.pojo.StudentQueryParam;

public interface StudentService {
    PageResult<Student> getStudentList(StudentQueryParam param);

    void deleteStudent(Integer[] ids);

    void addStudent(Student student);

    Student getInfo(Integer id);

    void updateStudent(Student student);

    void violation(Integer id, Short score);
}
