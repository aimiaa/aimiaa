package org.ami.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.ami.exception.ClazzHasStudentsException;
import org.ami.mapper.ClazzMapper;
import org.ami.mapper.StudentMapper;
import org.ami.pojo.Clazz;
import org.ami.pojo.ClazzQueryParam;
import org.ami.pojo.PageResult;
import org.ami.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> getClazzList(ClazzQueryParam param) {
        PageHelper.startPage(param.getPage(),param.getPageSize());
        List<Clazz> clazzList = clazzMapper.getClazzList(param);
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        Integer count = studentMapper.countId(id);
        if (count != null && count > 0) {
            throw new ClazzHasStudentsException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteClazzById(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {

        return clazzMapper.getClassById( id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> getAllClazz() {

        return clazzMapper.getAllClazz();
    }
}
