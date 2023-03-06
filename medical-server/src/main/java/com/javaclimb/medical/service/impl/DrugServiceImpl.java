package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.DrugMapper;
import com.javaclimb.medical.dao.PatientMapper;
import com.javaclimb.medical.domain.Drug;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.DrugService;
import com.javaclimb.medical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
* 药品Service实现类
*
*
* */
@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMapper drugMapper;


    @Override
    public boolean insert(Drug drug) {
        return drugMapper.insert(drug)>0;
    }

    @Override
    public boolean update(Drug drug) {
        return drugMapper.update(drug)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return drugMapper.delete(id)>0;
    }


    @Override
    public List<Drug> allDrug() {
        return drugMapper.allDrug();
    }



    @Override
    public List<Drug> DrugOfName(String name) {
        return drugMapper.DrugOfName(name);
    }


}
