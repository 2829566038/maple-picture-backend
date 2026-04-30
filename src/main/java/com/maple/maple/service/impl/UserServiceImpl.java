package com.maple.maple.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.maple.exception.BusinessException;
import com.maple.maple.exception.ErrorCode;
import com.maple.maple.model.entity.User;
import com.maple.maple.service.UserService;
import com.maple.maple.mapper.UserMapper;
import org.springframework.stereotype.Service;

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
        if(userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度不能小于4位");
        }
        return 0;
    }
}




