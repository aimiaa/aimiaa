package org.ami.mapper;

import org.ami.pojo.Student;
import org.ami.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    @Select("select count(*) from student where clazz_id = #{clazz_id}")
    Integer countId(@Param("clazz_id") Integer id);

    List<Student> getStudentList(StudentQueryParam param);

    void deleteStudentById(@Param("ids") Integer[] ids);
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void addStudent(Student student);
    @Select("select * from student where id =#{id}")
    Student getStuddentByID(Integer id);

    void updateStudent(Student student);

    void violation(Integer id, Short score);

    List<Map<String, Object>> getStudentDegreeCount();
    @Select("select c.name,count(*) value from student s left join clazz c on s.clazz_id = c.id group by c.name order by value desc ")
    List<Map<String, Object>> getClazzStudentCount();
}
