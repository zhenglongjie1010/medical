package com.javaclimb.medical.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.domain.Record;
import com.javaclimb.medical.service.PatientService;
import com.javaclimb.medical.service.RecordService;
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
 * 病历管理controller
 * */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    /*
     * 添加病历信息
     * */
    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    public Object addRecord(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String pname = request.getParameter("pname").trim();
        String pnumber = request.getParameter("pnumber").trim();
        String dnumber = request.getParameter("dnumber").trim();
        String dname = request.getParameter("dname").trim();
        String time = request.getParameter("time").trim();
        String symptom = request.getParameter("symptom").trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timedate = new Date();
        try {
            timedate = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Record record = new Record();
        record.setPnumber(Integer.parseInt(pnumber));
        record.setPname(pname);
        record.setDnumber(Integer.parseInt(dnumber));
        record.setDname(dname);
        record.setTime(timedate);
        record.setSymptom(symptom);

        boolean flag = recordService.insert(record);
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
    @RequestMapping(value = "/updateRecord",method = RequestMethod.POST)
    public Object updateRecord(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String pname = request.getParameter("pname").trim();
        String pnumber = request.getParameter("pnumber").trim();
        String dnumber = request.getParameter("dnumber").trim();
        String dname = request.getParameter("dname").trim();
        String time = request.getParameter("time").trim();
        String symptom = request.getParameter("symptom").trim();


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timedate = new Date();
        try {
            timedate = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Record record = new Record();
        record.setId(Integer.parseInt(id));
        System.out.println(Integer.parseInt(id));

        record.setPnumber(Integer.parseInt(pnumber));
        record.setPname(pname);
        record.setDnumber(Integer.parseInt(dnumber));
        record.setDname(dname);
        record.setTime(timedate);
        record.setSymptom(symptom);


        boolean flag = recordService.update(record);
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
     * 删除
     *
     * */
    @RequestMapping(value = "/deleteRecord",method = RequestMethod.GET)
    public Object deleteRecord(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        System.out.println(id);
        boolean flag = recordService.delete(Integer.parseInt(id));
        return flag;
    }
    /*
     * 查询所有药品
     * */
    @RequestMapping(value = "/allRecord",method = RequestMethod.GET)
    public Object allrecord(HttpServletRequest request){ return recordService.allRecord();
    }

}

