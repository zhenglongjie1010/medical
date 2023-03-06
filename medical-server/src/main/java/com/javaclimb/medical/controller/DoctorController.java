package com.javaclimb.medical.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.medical.domain.Doctor;

import com.javaclimb.medical.domain.Patient;
import com.javaclimb.medical.service.DoctorService;
import com.javaclimb.medical.utils.Consts;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 医生控制类
* */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    /*
    * 添加医生类
    * */
    @RequestMapping(value = "/addDcotor",method = RequestMethod.POST)
    public Object addDoctor(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String title = request.getParameter("title").trim();
        String tel = request.getParameter("tel").trim();
        String depart = request.getParameter("depart").trim();
        String introduction = request.getParameter("introduction").trim();
        String picture = request.getParameter("picture").trim();
        String number = request.getParameter("number").trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = dateFormat.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Doctor doctor = new Doctor();

        doctor.setName(name);
        doctor.setPassword(password);
        doctor.setSex(new Byte(sex));
        doctor.setBirth(birthdate);
        doctor.setTitle(title);
        doctor.setTel(tel);
        doctor.setDepart(depart);
        doctor.setIntroduction(introduction);
        doctor.setPicture(picture);
        doctor.setNumber(Integer.parseInt(number));
        boolean flag = doctorService.insert(doctor);
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
     * 修改医生类
     * */
    @RequestMapping(value = "/updateDcotor",method = RequestMethod.POST)
    public Object updateDoctor(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String title = request.getParameter("title").trim();
        String tel = request.getParameter("tel").trim();
        String depart = request.getParameter("depart").trim();
        String number = request.getParameter("number").trim();
        String introduction = request.getParameter("introduction").trim();


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = dateFormat.parse(birth);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Doctor doctor = new Doctor();
        doctor.setId(Integer.parseInt(id));
        doctor.setName(name);
        doctor.setPassword(password);
        doctor.setSex(new Byte(sex));
        doctor.setBirth(birthdate);
        doctor.setTitle(title);
        doctor.setTel(tel);
        doctor.setDepart(depart);
        doctor.setNumber(Integer.parseInt(number));

        doctor.setIntroduction(introduction);
        boolean flag = doctorService.update(doctor);
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
     * 删除医生类
     * */
    @RequestMapping(value = "/deleteDcotor",method = RequestMethod.GET)
    public Object deleteDoctor(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = doctorService.delete(Integer.parseInt(id));
        return flag;
    }

    /*
     * 查询  根据主键查询整个对象
     * */
    @RequestMapping(value="/selectByPrimaryKey",method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return doctorService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /*
     * 查询所有医生
     * */
    @RequestMapping(value = "/alldoctor",method = RequestMethod.GET)
    public Object alldoctor(HttpServletRequest request){
        return doctorService.alldoctor();
    }

    /*
     * 根据医生部门模糊查询列表
     * */
    @RequestMapping(value="/DoctorOfDepart",method = RequestMethod.GET)
    public Object DoctorOfDepart(HttpServletRequest request){
        String depart = request.getParameter("depart").trim();
        return doctorService.DoctorOfDepart(depart);
    }

    /*
     * 根据医生名字模糊查询列表
     * */
    @RequestMapping(value="/DoctorOfName",method = RequestMethod.GET)
    public Object DoctorOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return doctorService.DoctorOfName(name);
    }

    /*
     * 根据性别查询
     * */
    @RequestMapping(value="/doctorOfSex",method = RequestMethod.GET)
    public Object doctorOfSex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();
        return doctorService.doctorOfSex(Integer.parseInt(sex));
    }
    /*
     * 上传/更新医生图片
     * */
    @RequestMapping(value="/updatedoctorPic",method = RequestMethod.POST)
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
                Doctor doctor = new Doctor();
                doctor.setId(id);
                doctor.setPicture(storeAvatorPath);
                boolean flag = doctorService.update(doctor);
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
     * 前端用户登录
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String password = request.getParameter("password").trim();
        String name = request.getParameter("name").trim();

        Doctor doctor = new Doctor();
        doctor.setPassword(password);
        doctor.setName(name);


        boolean flag = doctorService.verifyPassword(name,password);
        if (flag) {  //密码验证成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            System.out.println(1);

            jsonObject.put("doctorMsg", doctorService.getByDoctor(name));
            System.out.println(2);


            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "用户名/密码错误");
        return jsonObject;


    }
}
