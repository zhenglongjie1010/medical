package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.CommentMapper;
import com.javaclimb.medical.domain.Comment;
import com.javaclimb.medical.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    @Override
    public List<Comment> commentOfPatient(Integer patientId,Integer doctorId) {
        return commentMapper.commentOfPatient(patientId,doctorId);
    }
}
