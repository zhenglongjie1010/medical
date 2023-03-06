package com.javaclimb.medical.dao;

import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.domain.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 处方dao
 * */
@Repository
public interface RecordMapper {
    /*
     * 增加
     * */
    public int insert(Record Record);

    /*
     * 修改
     * */
    public int update(Record Record);

    /*
     * 删除
     * */
    public int delete(Integer Record);


    /*
     * 查询  根据主键查询整个对象
     * */
    public  List<Record> selectByPrimaryKey(Integer id);

    /*
     * 查询所有病历
     * */
    public List<Record> allRecord();


    /*
     * 根据病历名称查询
     * */
    public List<Record> RecordOfName(String pname);



}
