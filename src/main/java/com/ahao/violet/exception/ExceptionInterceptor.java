package com.ahao.violet.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常拦截器
 *
 * @author ahao
 * @since 2019/11/1 下午7:08
 */
@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(value = {Exception.class})
    public String exceptionInterceptor(Exception e) {
        if (e instanceof ApplicationException) {
            return e.getMessage();
        }

        // some else exceptions
        return e.getMessage();
    }
}
