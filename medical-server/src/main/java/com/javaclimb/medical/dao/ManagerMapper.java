package com.javaclimb.medical.dao;

import org.springframework.stereotype.Repository;
/*
* 管理员dao
* */
@Repository
public interface ManagerMapper {
    /*
    * 验证密码是否正确
    * */
    public int verifyPassword(String username,String password);
}
