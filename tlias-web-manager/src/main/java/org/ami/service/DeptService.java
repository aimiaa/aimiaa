package org.ami.service;

import org.ami.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();


    void deleteById(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    Dept findById(Integer id);
}
