package com.maple.maple.service;

import com.maple.maple.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.maple.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @param request
     * @return 获取登录信息VO
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    String getEncryptPassword(String password);

    /**
     * 获取登录用户
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 获得脱敏后的登录用户VO
     * @param user
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户注销
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);
}
