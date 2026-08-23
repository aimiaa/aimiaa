package org.ami.service;

import org.ami.pojo.ClazzOption;
import org.ami.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderCount();

    List<Map<String, Object>> getStudentDegreeCount();

    ClazzOption getStudentCount();
}
