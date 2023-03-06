package com.javaclimb.medical.domain;

import java.io.Serializable;
import java.util.Date;

/***
 *评论
 */
public class Comment implements Serializable {
    /*主键*/
    private Integer id;
    /*就诊人账号*/
    private Integer patientId;
    /*接诊医生账号*/
    private Integer doctorId;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    /*评论者的姓名*/
    private String name;
    /*评论者的照片*/
    private String picture;
    /*评论内容*/
    private String content;
    /*评论日期*/
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
