package org.ami.mapper;

import org.ami.pojo.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//    原始分页查询
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long getEmpCount();
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start}, #{pageSize} ")
//    public List<Emp> getEmpList(@Param("start") int start  , @Param("pageSize") int pageSize);
//    使用PageHelper插件分页查询
    public List<Emp> getEmpList(EmpQueryParam param);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void addEmp(Emp emp);

    void deleteEmpById(Integer[] ids);
    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time" +
            " from emp where id = #{id}")
    Emp getInfo(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> getJobOption();
    /**
     * 员工性别统计
     */

    List<Map<String,Object>> getEmpGenderCount();
    @Select("select * from emp")
    List<Emp> getAllEmp();
    @Select("select id, username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);

    @Select("select * from operate_log ")
    List<OperateLog> getOperateLogList(LogQueryParam logQueryParam);
}
