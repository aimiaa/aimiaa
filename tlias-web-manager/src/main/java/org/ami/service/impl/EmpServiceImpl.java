package org.ami.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.ami.mapper.EmpExprMapper;
import org.ami.mapper.EmpMapper;
import org.ami.pojo.*;
import org.ami.service.EmpService;
import org.ami.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> Page(EmpQueryParam param) {
        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Emp> empList = empMapper.getEmpList(param);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addEmp(Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.addEmp(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.addEmpExpr(exprList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEmp(Integer[] ids) {
        empMapper.deleteEmpById(ids);
        empExprMapper.deleteExprByEmpId(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        List<EmpExpr> exprList = empExprMapper.getInfo(id);
        Emp emp = empMapper.getInfo(id);
        emp.setExprList(exprList);
        return emp;
    }

    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        empExprMapper.deleteExprByEmpId(new Integer[]{emp.getId()});
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.addEmpExpr(exprList);
        }

    }

    @Override
    public List<Emp> getAllEmp() {
        return empMapper.getAllEmp();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empInfo = empMapper.selectByUsernameAndPassword(emp);
        if (empInfo != null){
            Map<String, Object> claims = Map.of("id", empInfo.getId(), "username", empInfo.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(empInfo.getId(), empInfo.getUsername(), empInfo.getName(),jwt);
        }
        return null;
    }

    @Override
    public PageResult<OperateLog> getLogList(LogQueryParam logQueryParam) {
        PageHelper.startPage(logQueryParam.getPage(), logQueryParam.getPageSize());
        List<OperateLog> logList = empMapper.getOperateLogList(logQueryParam);
        Page<OperateLog> p = (Page<OperateLog>) logList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
