package com.javaclimb.medical.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.medical.dao.PatientMapper;
import com.javaclimb.medical.domain.Doctor;
import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.PatientService;
import com.javaclimb.medical.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 就诊人管理controller
* */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    /*
    * 添加该医生下的就诊人的信息
    * */
    @RequestMapping(value = "/addPatient",method = RequestMethod.POST)
    public Object addPatient(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String doctorId = request.getParameter("doctorId").trim();  //所属就诊医生的id
        String doctorName = request.getParameter("doctorName").trim();//所属就诊医生的名字
        String password = request.getParameter("password").trim();  //就诊人的密码
        String name = request.getParameter("name").trim();         //就诊人的姓名
        String sex = request.getParameter("sex").trim();            //性别
        String birth = request.getParameter("birth").trim();      //出生日期
        String tel = request.getParameter("tel").trim();         //联系方式
        String picture = "/imgg/hhh.jpg" ; //就诊人的默认图片

        if(name==null||name.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }




        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = dateFormat.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Patient patient = new Patient();
        patient.setDoctorId(Integer.parseInt(doctorId));
        patient.setDoctorName(doctorName);
        patient.setPassword(password);
        patient.setName(name);
        patient.setSex(new Byte(sex));
        patient.setBirth(birthdate);
        patient.setTel(tel);
        patient.setPicture(picture);
        boolean flag = patientService.insert(patient);
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
     * 就诊人信息的添加
     * */
    @RequestMapping(value = "/addPa",method = RequestMethod.POST)
    public Object addPa(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();


        String password = request.getParameter("password").trim();  //就诊人的密码
        String name = request.getParameter("name").trim();         //就诊人的姓名
        String sex = request.getParameter("sex").trim();            //性别
        String birth = request.getParameter("birth").trim();      //出生日期
        String tel = request.getParameter("tel").trim();         //联系方式
        String picture = "/imgg/hhh.jpg" ; //就诊人的默认图片

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = dateFormat.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(name==null||name.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }

        Patient patient1 = patientService.getByPatient(name);
        if(patient1!=null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名已存在");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        Patient patient = new Patient();

        patient.setPassword(password);
        patient.setName(name);
        patient.setSex(new Byte(sex));
        patient.setBirth(birthdate);
        patient.setTel(tel);
        patient.setPicture(picture);
        boolean flag = patientService.insert(patient);
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
     * 前端用户登录
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String password = request.getParameter("password").trim();  //就诊人的密码
        String name = request.getParameter("name").trim();         //就诊人的姓名


        Patient patient = new Patient();
        patient.setPassword(password);
        patient.setName(name);

        boolean flag = patientService.verifyPassword(name,password);
        if (flag) {  //密码验证成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            System.out.println(1);

            jsonObject.put("patientMsg", patientService.getByPatient(name));
            System.out.println(2);


            return jsonObject;
        }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名/密码错误");
            return jsonObject;


    }

    /*
     * 查询该医生就诊过的所有患者
     * */
    @RequestMapping(value = "/allpatient",method = RequestMethod.GET)
    public Object allpatient(HttpServletRequest request){ return patientService.allPatient();
    }

    /*
    * 获取该医生下有的就诊人的信息
    * */
    @RequestMapping(value = "/someOfpatient",method = RequestMethod.GET)
    public Object PatientOfDoctorName(HttpServletRequest request){
        String doctorName = request.getParameter("doctorName").trim();
        return patientService.PatientOfDoctorName(doctorName);
    }

    /*
     * 上传/更新医生图片
     * */
    @RequestMapping(value="/updatepatientPic",method = RequestMethod.POST)
    public Object updateDoctorPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        System.out.println("asd");
        System.out.println(avatorFile);


        if(avatorFile.isEmpty()){
            System.out.println("ooooo");
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }

        //文件名=当前时间到毫秒+原来的文件名
        String fileName = avatorFile.getOriginalFilename();
        //文件路径
        String filePath = "E:/medical/medical-server/src/main/resources/static/imgg";

        File file1 = new File(filePath);
        //如果文件路径不存在，新增该路径
        if(!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        System.out.println(dest);

        //存储到数据库里的文件地址
        String storeAvatorPath = "/imgg/" + fileName;
        System.out.println(storeAvatorPath);
        try {
            avatorFile.transferTo(dest);

            Patient patient = new Patient();
            patient.setId(id);
            patient.setPicture(storeAvatorPath);
            boolean flag = patientService.update(patient);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("picture", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            System.out.println("o2");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }



    /*
     * 查询  根据主键查询整个对象
     * */
    @RequestMapping(value="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        //System.out.println(id);
        return patientService.selectByPrimaryKey(Integer.parseInt(id));
    }
    /*
     *
     * */
    @RequestMapping(value="/kKey",method = RequestMethod.GET)
    public Object selectByPatientKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        System.out.println(id);
        return patientService.selectByPatientKey(Integer.parseInt(id));
    }







    /*
     * 修改就诊人
     * */
    @RequestMapping(value = "/updatePatient",method = RequestMethod.POST)
    public Object updatePatient(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String tel = request.getParameter("tel").trim();
        String doctorName = request.getParameter("doctorName").trim();
        String doctorId = request.getParameter("doctorId").trim();



        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = dateFormat.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Patient patient = new Patient();
        patient.setId(Integer.parseInt(id));
        patient.setName(name);
        patient.setPassword(password);
        patient.setSex(new Byte(sex));
        patient.setBirth(birthdate);
        patient.setTel(tel);
        patient.setDoctorName(doctorName);
        patient.setDoctorId(Integer.parseInt(doctorId));


        boolean flag = patientService.update(patient);
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
    @RequestMapping(value = "/deletePatient",method = RequestMethod.GET)
    public Object deleteDoctor(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        System.out.println(id);

        boolean flag = patientService.delete(Integer.parseInt(id));
        return flag;
    }


}

