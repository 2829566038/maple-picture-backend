package com.maple.maple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.maple.model.dto.user.UserQueryRequest;
import com.maple.maple.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.maple.model.vo.LoginUserVO;
import com.maple.maple.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 账号
     * @param userPassword 密码
     * @param checkPassword 确认密码
     * @return 用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 用户登录
     * @param userAccount 账号
     * @param userPassword 密码
     * @param request 请求对象
     * @return 获取登录信息VO
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    String getEncryptPassword(String password);

    /**
     * 获取登录用户
     * @param request 请求对象
     * @return 用户
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 获得脱敏后的登录用户VO
     * @param user 用户
     * @return 登录用户VO
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获得脱敏后的用户VO
     *
     * @param user 用户
     * @return 用户VO
     */
    UserVO getUserVO(User user);

    /**
     * 获得脱敏后的用户VO列表
     *
     * @param userList 用户列表
     * @return 用户VO列表
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 用户注销
     * @param request 请求对象
     * @return 是否注销成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获得查询用户列表的条件
     *
     * @param userQueryRequest 查询用户列表的请求
     * @return 查询用户列表的条件
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    /**
     * 判断用户是否为管理员
     */
    boolean isAdmin(User user);
}
