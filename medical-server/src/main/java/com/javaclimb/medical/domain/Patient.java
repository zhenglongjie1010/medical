package com.javaclimb.medical.domain;

import java.io.Serializable;
import java.util.Date;

/***
 *就诊人
 */
public class Patient implements Serializable {
    /*主键*/
    private Integer id;
    /*姓名*/
    private String name;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*生日*/
    private Date birth;
    /*电话号码*/
    private String tel;
    /*头像*/
    private String picture;
    /*就诊医生id*/
    private Integer doctorId;
    /*就诊医生姓名*/
    private String doctorName;

    public Patient(String doctorName) {
        this.doctorName = doctorName;
    }

    public Patient() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
