package com.maple.maple.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.maple.exception.BusinessException;
import com.maple.maple.exception.ErrorCode;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.enums.UserRoleEnum;
import com.maple.maple.service.UserService;
import com.maple.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author A
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2026-04-30 20:12:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if(StrUtil.hasBlank(userAccount, userPassword, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }
        if(userAccount.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度不能小于8位");
        }
        if(userPassword.length()<6){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不能小于6位");
        }
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码输入不一致");
        }
        // 检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Account",userAccount);
        long count=this.baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号已存在");
        }
        //密码加密
        String encryptPassword=getEncryptPassword(userPassword);
        // 插入用户
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("无名");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean saveResult=this.save(user);
        if(!saveResult){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"用户注册失败,数据库错误");
        }
        // mybatis自动返回主键值
        return user.getId();
    }

    /**
     * 密码加密
     * @return
     */
    @Override
    public String getEncryptPassword(String userPassword){
        //加盐，混淆密码
        final String SALT="maple";
        return DigestUtils.md5DigestAsHex((SALT+userPassword).getBytes());
    }
}




