package org.ami.service;

import lombok.extern.slf4j.Slf4j;
import org.ami.pojo.Clazz;
import org.ami.pojo.ClazzQueryParam;
import org.ami.pojo.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface ClazzService {
    PageResult<Clazz> getClazzList(ClazzQueryParam param);

    void deleteClazz(Integer id);

    void addClazz(Clazz clazz);

    Clazz getInfo(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getAllClazz();
}
