package org.ami.mapper;

import org.ami.pojo.Clazz;
import org.ami.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> getClazzList(ClazzQueryParam param);
    @Delete("delete from clazz where id = #{id}")
    void deleteClazzById(Integer id);
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void addClazz(Clazz clazz);
    @Select("select * from clazz where id = #{id}")
    Clazz getClassById(Integer id);
    @Update(
            "update clazz " +
            "set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, " +
            "master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} " +
            "where id = #{id}"
    )
    void updateClazz(Clazz clazz);
    @Select("select * from clazz")
    List<Clazz> getAllClazz();
}
