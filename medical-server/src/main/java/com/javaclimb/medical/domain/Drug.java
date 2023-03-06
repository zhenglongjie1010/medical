package com.javaclimb.medical.domain;

import java.io.Serializable;
import java.util.Date;

/***
 *药品
 */
public class Drug implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*服用方式*/
    private String take;
    /*规格*/
    private String specify;
    /*数量*/
    private Integer number;
    /*进货日期*/
    private Date time;

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

    public String getTake() {
        return take;
    }

    public void setTake(String take) {
        this.take = take;
    }

    public String getSpecify() {
        return specify;
    }

    public void setSpecify(String specify) {
        this.specify = specify;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
