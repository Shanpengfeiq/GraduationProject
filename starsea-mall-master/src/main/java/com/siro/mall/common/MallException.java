package com.siro.mall.common;

/**
 * 异常处理类
 * @author shan
 * @date 2025-03-23
 */
public class MallException extends RuntimeException {

    public MallException() {
    }

    public MallException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     * @param message
     */
    public static void fail(String message) {
        throw new MallException(message);
    }
}
