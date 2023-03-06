package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.DoctorMapper;
import com.javaclimb.medical.domain.Doctor;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 医生service实现类
* */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;


    @Override
    public boolean insert(Doctor doctor) {
        return doctorMapper.insert(doctor)>0;
    }

    @Override
    public boolean update(Doctor doctor) {
        return doctorMapper.update(doctor)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return doctorMapper.delete(id)>0;
    }

    @Override
    public boolean updatenumber(Integer id) {
        return doctorMapper.updatenumber(id)>0;
    }

    @Override
    public Doctor selectByPrimaryKey(Integer id) {
        return doctorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Doctor> alldoctor() {
        return doctorMapper.alldoctor();
    }

    @Override
    public List<Doctor> DoctorOfDepart(String depart) {
        return doctorMapper.DoctorOfDepart("%"+depart+"%");
    }

    @Override
    public List<Doctor> DoctorOfName(String name) {
        return doctorMapper.DoctorOfName("%"+name+"%");
    }


    @Override
    public List<Doctor> doctorOfSex(Integer sex) {
        return doctorMapper.doctorOfSex(sex);
    }


    @Override
    public boolean verifyPassword(String name, String password) {
        return doctorMapper.verifyPassword(name ,password)>0;
    }

    @Override
    public Doctor getByDoctor(String name) {
        return doctorMapper.getByDoctor(name);
    }

}
