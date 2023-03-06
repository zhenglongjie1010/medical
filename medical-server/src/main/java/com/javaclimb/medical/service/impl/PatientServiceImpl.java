package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.PatientMapper;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
* 歌曲Service实现类
*
*
* */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public boolean insert(Patient patient) {
        return patientMapper.insert(patient)>0;
    }

    @Override
    public boolean update(Patient patient) {
        return patientMapper.update(patient)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return patientMapper.delete(id)>0;
    }

    @Override
    public   List<Patient> selectByPrimaryKey(Integer doctorId){return patientMapper.selectByPrimaryKey(doctorId);}

    @Override
    public List<Patient> allPatient() {
        return patientMapper.allPatient();
    }

    @Override
    public List<Patient> PatientOfDoctorName(String doctorName) {
        return patientMapper.PatientOfDoctorName(doctorName);
    }

    @Override
    public List<Patient> PatientOfName(String name) {
        return patientMapper.PatientOfName(name);
    }

    @Override
    public boolean verifyPassword(String name, String password) {
        return patientMapper.verifyPassword(name ,password)>0;
    }

    @Override
    public Patient getByPatient(String name) {
        return patientMapper.getByPatient(name);
    }

    @Override
    public   Patient selectByPatientKey(Integer Id){return patientMapper.selectByPatientKey(Id);}




}
