package com.javaclimb.medical.dao;

import com.javaclimb.medical.domain.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 就诊人dao
* */
@Repository
public interface PatientMapper {
    /*
    * 增加
    * */
    public int insert(Patient Patient);

    /*
    * 修改
    * */
    public int update(Patient Patient);

    /*
    * 删除
    * */
    public int delete(Integer Patient);

    /*
     * 验证密码是否正确
     * */
    public int verifyPassword(String name,String password);

    /*
    * 查询  根据主键查询整个对象
    * */
    public  List<Patient> selectByPrimaryKey(Integer doctorId);
    /*
     * 查询  根据主键查询整个对象
     * */
    public  Patient selectByPatientKey(Integer Id);

    /*
     * 查询  根据主键查询整个对象
     * */
    public  List<Patient> kkey(Integer Id);

    /*
    * 查询所有就诊人
    * */
    public List<Patient> allPatient();

    /*
    * 根据就诊人的就诊医生模糊查询列表
    * */
    public List<Patient> PatientOfDoctorName(String doctorName);

    /*
    * 根据就诊人的姓名查询
    * */
    public List<Patient> PatientOfName(String name);

    /*
    * 根据账号查询
    * */
    public Patient getByPatient(String name);



}
