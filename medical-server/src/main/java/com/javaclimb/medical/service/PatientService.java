package com.javaclimb.medical.service;

import com.javaclimb.medical.domain.Doctor;
import com.javaclimb.medical.domain.Patient;

import java.util.List;

/*
* 就诊人service接口
* */
public interface PatientService {

    /*
     * 增加
     * */
    public boolean insert(Patient patient);

    /*
     * 修改
     * */
    public boolean update(Patient patient);

    /*
     * 删除
     * */
    public boolean delete(Integer id);



    /*
     * 查询  根据主键查询整个对象
     * */
    public  List<Patient> selectByPrimaryKey(Integer id);

    /*
     * 查询  根据主键查询整个对象
     * */
    public  Patient selectByPatientKey(Integer id);



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
    * 获取该医生下有的就诊人的信息
    * */

    /*
     * 验证密码是否正确
     * */
    public boolean verifyPassword(String name, String password);

    /*
    * 获取就诊人的姓名
    * */
    public Patient getByPatient(String name);





}
