package com.javaclimb.medical.service;

import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.domain.Record;

import java.util.List;

/*
 * 病历表service接口
 * */
public interface RecordService {

    /*
     * 增加
     * */
    public boolean insert(Record record);

    /*
     * 修改
     * */
    public boolean update(Record record);

    /*
     * 删除
     * */
    public boolean delete(Integer id);


    /*
     * 查询所有病历
     * */
    public List<Record> allRecord();


    /*
     * 根据病历名称查询
     * */
    public List<Record> RecordOfName(String pname);




}



