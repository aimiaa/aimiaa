package org.ami.mapper;

import org.ami.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//员工工作经历Mapper
@Mapper
public interface EmpExprMapper {
    void addEmpExpr(List<EmpExpr> exprList);

    void deleteExprByEmpId(@Param("empIds") Integer[] empIds);
    @Select("select * from emp_expr where emp_id = #{emp-id}")
    List<EmpExpr> getInfo(Integer empId);
}
