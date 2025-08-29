package com.example.base.util.result;

import com.example.base.util.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
//用于定义全局的异常处理器,它可以捕获所有控制器中抛出的异常，并进行统一处理
@RestControllerAdvice
public class GlobalExceptionHandling {

    /**
     * 自定义异常,@ExceptionHandler 用于指定处理特定类型的异常
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultVo processException(CustomException e) {
        log.error("位置:{} -> 错误信息:{}", e.getMethod() ,e.getLocalizedMessage());
        return ResultVo.fail(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    }

    /**
     * 运行时异常
     * @ResponseStatus 是 Spring MVC 提供的一个注解，用于指定 HTTP 响应的状态码。
     * HttpStatus.OK 表示 HTTP 状态码 200，表示请求成功。
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResultVo exception(Exception e) {
        e.printStackTrace();
        return ResultVo.fail(ResultEnum.UNKNOWN_EXCEPTION);
    }

}
