package org.ami.service.impl;

import org.ami.mapper.EmpMapper;
import org.ami.mapper.StudentMapper;
import org.ami.pojo.ClazzOption;
import org.ami.pojo.JobOption;
import org.ami.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    EmpMapper empMapper;
    @Autowired
    StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> jobOption = empMapper.getJobOption();
        List<Object> jobList = jobOption.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = jobOption.stream().map(map -> map.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderCount() {
        List<Map<String, Object>> empGenderCount = empMapper.getEmpGenderCount();
        return empGenderCount;
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeCount() {
        return studentMapper.getStudentDegreeCount();
    }

    @Override
    public ClazzOption getStudentCount() {
        List<Map<String, Object>> clazzStudentCount = studentMapper.getClazzStudentCount();
        List<Object> clazzList = clazzStudentCount.stream().map(map -> map.get("name")).toList();
        List<Object> dataList = clazzStudentCount.stream().map(map -> map.get("value")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
