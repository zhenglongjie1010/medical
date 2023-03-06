package com.javaclimb.medical.dao;

import com.javaclimb.medical.domain.Drug;
import com.javaclimb.medical.domain.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 就诊人dao
* */
@Repository
public interface DrugMapper {
    /*
    * 增加
    * */
    public int insert(Drug Drug);

    /*
    * 修改
    * */
    public int update(Drug Drug);

    /*
    * 删除
    * */
    public int delete(Integer Drug);


    /*
    * 查询  根据主键查询整个对象
    * */
    public  List<Drug> selectByPrimaryKey(Integer id);

    /*
    * 查询所有药品
    * */
    public List<Drug> allDrug();


    /*
    * 根据药品名称查询
    * */
    public List<Drug> DrugOfName(String name);



}
