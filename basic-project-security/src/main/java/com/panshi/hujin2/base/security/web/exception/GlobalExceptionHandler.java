package com.panshi.hujin2.base.security.web.exception;

import com.panshi.hujin2.base.common.factory.MessageFactory;
import com.panshi.hujin2.base.domain.result.BasicResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangZH 2017/11/18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public BasicResult<Void> methodArgumentNotValidExceptionHandler(Exception e) {
        BindingResult bindingResult;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            bindingResult = ((BindException) e).getBindingResult();
        }
        return BasicResult.error(HttpServletResponse.SC_BAD_REQUEST,
                MessageFactory.getMsg(bindingResult.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public BasicResult<Void> exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return BasicResult.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
