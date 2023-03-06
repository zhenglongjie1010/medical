package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.RecordMapper;
import com.javaclimb.medical.domain.Record;
import com.javaclimb.medical.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 * 病历表Service实现类
 *
 *
 * */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;


    @Override
    public boolean insert(Record record) {
        return recordMapper.insert(record)>0;
    }

    @Override
    public boolean update(Record record) {
        return recordMapper.update(record)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return recordMapper.delete(id)>0;
    }


    @Override
    public List<Record> allRecord() {
        return recordMapper.allRecord();
    }



    @Override
    public List<Record> RecordOfName(String pname) {
        return recordMapper.RecordOfName(pname);
    }


}
