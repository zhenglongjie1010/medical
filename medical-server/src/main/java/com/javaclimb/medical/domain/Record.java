package com.javaclimb.medical.domain;


import java.io.Serializable;
import java.util.Date;

/***
 *病历
 */
public class Record implements Serializable {
    /*主键*/
    private Integer id;
    /*就诊人编号*/
    private Integer pnumber;
    /*就诊人姓名*/
    private String pname;
    /*医生编号*/
    private Integer dnumber;
    /*医生姓名*/
    private String dname;
    /*填写日期*/
    private Date time;
    /*症状*/
    private String symptom;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPnumber() {
        return pnumber;
    }

    public void setPnumber(Integer pnumber) {
        this.pnumber = pnumber;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDnumber() {
        return dnumber;
    }

    public void setDnumber(Integer dnumber) {
        this.dnumber = dnumber;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}