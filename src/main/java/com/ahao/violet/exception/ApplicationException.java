package com.ahao.violet.exception;

/**
 * 顶层自定义异常
 *
 * @author ahao
 * @since 2019/11/1 下午7:11
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
