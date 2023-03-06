package com.javaclimb.medical.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.medical.domain.Drug;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.DrugService;
import com.javaclimb.medical.service.PatientService;
import com.javaclimb.medical.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 药品管理controller
* */
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;
    /*
    * 添加药品信息
    * */
    @RequestMapping(value = "/addDrug",method = RequestMethod.POST)
    public Object addDrug(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();         //药品名称
        String take = request.getParameter("take").trim();            //服用方式
        String specify = request.getParameter("specify").trim();      //规格
        String number = request.getParameter("number").trim();         //联系方式
        String time = request.getParameter("time").trim();        //药品日期

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timedate = new Date();
        try {
            timedate = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Drug drug = new Drug();
        drug.setName(name);
        drug.setTake(take);
        drug.setSpecify(specify);
        drug.setNumber(Integer.parseInt(number));
        drug.setTime(timedate);

        boolean flag = drugService.insert(drug);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }
    /*
     * 修改药品信息
     * */
    @RequestMapping(value = "/updateDrug",method = RequestMethod.POST)
    public Object updateDrug(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String take = request.getParameter("take").trim();
        String specify = request.getParameter("specify").trim();
        String number = request.getParameter("number").trim();
        String time = request.getParameter("time").trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timedate = new Date();
        try {
            timedate = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Drug drug = new Drug();
        drug.setId(Integer.parseInt(id));
        drug.setName(name);
        drug.setTake(take);
        drug.setSpecify(specify);
        drug.setNumber(Integer.parseInt(number));
        drug.setTime(timedate);


        boolean flag = drugService.update(drug);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /*
     * 删除就诊人
     * */
    @RequestMapping(value = "/deleteDrug",method = RequestMethod.GET)
    public Object deleteDrug(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        System.out.println(id);

        boolean flag = drugService.delete(Integer.parseInt(id));
        return flag;
    }
    /*
     * 查询所有药品
     * */
    @RequestMapping(value = "/allDrug",method = RequestMethod.GET)
    public Object alldrug(HttpServletRequest request){ return drugService.allDrug();
    }

}

