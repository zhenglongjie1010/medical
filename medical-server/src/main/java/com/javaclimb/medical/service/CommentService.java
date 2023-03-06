package com.javaclimb.medical.service;

import com.javaclimb.medical.domain.Comment;

import java.util.List;

/*
* 药品service接口
* */
public interface CommentService {

    /*
     * 增加
     * */
    public boolean insert(Comment comment);

    /*
     * 修改
     * */
    public boolean update(Comment comment);

    /*
     * 删除
     * */
    public boolean delete(Integer id);


    /*
     * 查询所有评论
     * */
    public List<Comment> allComment();


    /*
     * 根据就诊人的id
     * */
    public List<Comment> commentOfPatient(Integer patientId,Integer doctorId);




}
