package com.javaclimb.medical.service;

/*
* 管理员service接口
* */
public interface ManagerService {
    /*
     * 验证密码是否正确
     * */
    public boolean verifyPassword(String username, String password);
}
