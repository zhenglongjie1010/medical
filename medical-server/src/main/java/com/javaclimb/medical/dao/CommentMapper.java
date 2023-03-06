package com.javaclimb.medical.dao;

import com.javaclimb.medical.domain.Comment;
import org.springframework.stereotype.Repository;


import java.util.List;

/*
* dao
* */
@Repository
public interface CommentMapper {
    /*
    * 增加
    * */
    public int insert(Comment comment);

    /*
    * 修改
    * */
    public int update(Comment comment);

    /*
    * 删除
    * */
    public int delete(Integer id);


    /*
    * 查询
    * */
    public  List<Comment> allComment();

    /*
    * 查询该就诊人的所有评论
    * */
    public List<Comment> commentOfPatient(Integer patientId,Integer doctorId);



}
