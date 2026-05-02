package com.maple.maple.service;

import com.maple.maple.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author A
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2026-04-30 20:12:39
*/
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    String getEncryptPassword(String password);
}
