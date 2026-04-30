package com.maple.maple.exception;

// 抛出异常工具类
public class ThrowUtils {
    public static void throwIf(boolean condition,RuntimeException runtimeException) {
        /**
         * 如果条件为true，抛出异常
         * @param condition 条件
         * @param runtimeException 异常
         */
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 如果条件为true，抛出异常
     * @param condition 条件
     * @param errorCode 异常码
     * @param message 异常信息
     */
    public static void throwIf(boolean condition,ErrorCode errorCode,String message) {
        throwIf(condition,new BusinessException(errorCode,message));
    }
}