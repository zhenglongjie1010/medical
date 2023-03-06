package com.javaclimb.medical.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.medical.domain.Comment;
import com.javaclimb.medical.domain.Drug;
import com.javaclimb.medical.service.CommentService;
import com.javaclimb.medical.service.DrugService;
import com.javaclimb.medical.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 评论管理controller
* */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    /*
    * 添加评论
    * */
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String patientId = request.getParameter("patientId").trim();
        String doctorId = request.getParameter("doctorListsId").trim();
        String name = request.getParameter("name").trim();
        String picture = request.getParameter("picture").trim();
        String content = request.getParameter("content").trim();

        System.out.println(patientId);
        System.out.println(name);
        System.out.println(picture);
        System.out.println(content);



        Comment comment = new Comment();
        comment.setPatientId(Integer.parseInt(patientId));
        comment.setDoctorId(Integer.parseInt(doctorId));
        comment.setName(name);
        comment.setPicture(picture);
        comment.setContent(content);


        boolean flag = commentService.insert(comment);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }

    /*
     * 添加评论
     * */
    @RequestMapping(value = "/adddoctorComment",method = RequestMethod.POST)
    public Object adddoctorComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String patientId = request.getParameter("patientListsId").trim();
        String doctorId = request.getParameter("doctorId").trim();
        String name = request.getParameter("name").trim();
        String picture = request.getParameter("picture").trim();
        String content = request.getParameter("content").trim();

        System.out.println(patientId);
        System.out.println(name);
        System.out.println(picture);
        System.out.println(content);



        Comment comment = new Comment();
        comment.setPatientId(Integer.parseInt(patientId));
        comment.setDoctorId(Integer.parseInt(doctorId));
        comment.setName(name);
        comment.setPicture(picture);
        comment.setContent(content);


        boolean flag = commentService.insert(comment);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }


    /*
     * 修改评论信息
     * */
    @RequestMapping(value = "/updateComment",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String patientId = request.getParameter("patientId").trim();
        String name = request.getParameter("name").trim();
        String picture = request.getParameter("picture").trim();
        String content = request.getParameter("content").trim();
        String time = request.getParameter("time").trim();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timedate = new Date();
        try {
            timedate = dateFormat.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setPatientId(Integer.parseInt(patientId));
        comment.setName(name);
        comment.setPicture(picture);
        comment.setContent(content);
        comment.setTime(timedate);

        boolean flag = commentService.update(comment);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改评论失败");
        return jsonObject;
    }

    /*
     * 删除评论
     * */
    @RequestMapping(value = "/deleteComment",method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();

        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }
    /*
     * 查询所有评论
     * */
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public Object allComment(HttpServletRequest request){ return commentService.allComment();
    }


    /*
     * 根据就诊人的id查询所有的评论
     * */
    @RequestMapping(value = "/commentOfPatient",method = RequestMethod.GET)
    public Object commentOfPatient(HttpServletRequest request){
        String patientId = request.getParameter("id");
        System.out.println(patientId);
        String doctorId = request.getParameter("idd");
        System.out.println(doctorId);
        return commentService.commentOfPatient(Integer.parseInt(patientId),Integer.parseInt(doctorId));
    }

}

