package com.javaclimb.medical.service;

import com.javaclimb.medical.domain.Drug;
import com.javaclimb.medical.domain.Patient;

import java.util.List;

/*
* 药品service接口
* */
public interface DrugService {

    /*
     * 增加
     * */
    public boolean insert(Drug drug);

    /*
     * 修改
     * */
    public boolean update(Drug drug);

    /*
     * 删除
     * */
    public boolean delete(Integer id);


    /*
     * 查询所有药品
     * */
    public List<Drug> allDrug();


    /*
     * 根据药品名称查询
     * */
    public List<Drug> DrugOfName(String name);




}
