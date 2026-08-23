package org.ami.service;

import org.ami.pojo.*;

import java.util.List;

public interface EmpService {

    PageResult<Emp> Page(EmpQueryParam param);

    void addEmp(Emp emp);

    void deleteEmp(Integer[] ids);

    Emp getInfo(Integer id);

    void updateEmp(Emp emp);

    List<Emp> getAllEmp();

    LoginInfo login(Emp emp);

    PageResult<OperateLog> getLogList(LogQueryParam logQueryParam);
}
