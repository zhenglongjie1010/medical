package com.javaclimb.medical.service.impl;

import com.javaclimb.medical.dao.ManagerMapper;
import com.javaclimb.medical.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 管理员接口实现类
* */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean verifyPassword(String username, String password) {

        return managerMapper.verifyPassword(username,password)>0;
    }
}
