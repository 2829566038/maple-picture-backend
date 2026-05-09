package com.maple.maple.aop;

import com.maple.maple.annotation.AuthCheck;
import com.maple.maple.exception.BusinessException;
import com.maple.maple.exception.ErrorCode;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.enums.UserRoleEnum;
import com.maple.maple.service.UserService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private UserService userService;

    /**
     * 认证拦截器
     * @param joinPoint
     * @param authCheck
     * @return
     * @throws Throwable
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取当前登录用户角色
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //如果不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        //必须有权限才通过以下代码
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //要求必须有管理员权限，但用户没有
        if (!UserRoleEnum.ADMIN.equals(userRoleEnum) && UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //如果用户角色与必须角色相同，放行
        return joinPoint.proceed();
    }
}
