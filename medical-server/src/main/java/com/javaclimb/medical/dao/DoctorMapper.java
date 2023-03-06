package com.javaclimb.medical.dao;

import com.javaclimb.medical.domain.Doctor;
import com.javaclimb.medical.domain.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 医生dao
* */
@Repository
public interface DoctorMapper {
    /*
    * 增加
    * */
    public int insert(Doctor doctor);

    /*
    * 修改
    * */
    public int update(Doctor doctor);

    /*
    * 删除
    * */
    public int delete(Integer doctor);

    /*
    * 修改预约人数，用户预约后 预约人数加1
    * */
    public int updatenumber(Integer doctor);

    /*
    * 查询  根据主键查询整个对象
    * */
    public  Doctor selectByPrimaryKey(Integer id);

    /*
    * 查询所有医生
    * */
    public List<Doctor> alldoctor();

    /*
    * 根据医生部门模糊查询列表
    * */
    public List<Doctor> DoctorOfDepart(String depart);

    /*
     * 根据医生姓名模糊查询列表
     * */
    public List<Doctor> DoctorOfName(String name);

    /*
    * 根据性别查询
    * */
    public List<Doctor> doctorOfSex(Integer sex);

    /*
     * 验证密码是否正确
     * */
    public int verifyPassword(String name,String password);

    /*
     * 根据账号查询
     * */
    public Doctor getByDoctor(String name);

}
