package com.javaclimb.medical.domain;

import java.io.Serializable;
import java.util.Date;

/***
 *医生
 */
public class Doctor implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*密码*/
    private String password;
    /*性别*/
    private Byte sex;
    /*生日*/
    private Date birth;
    /*职称*/
    private String title;
    /*电话号码*/
    private String tel;
    /*所属部门*/
    private String depart;
    /*简介*/
    private String introduction;
    /*头像*/
    private String picture;

    /* 预约人数*/
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
