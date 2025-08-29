package com.example.base.util.result;

import lombok.Data;

//通用返回类
@Data
public class ResultVo<T> {

    //状态码
    private Integer code;

    //消息
    private String message;

    //返回数据
    private T data;

    public ResultVo(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
        this.data = data;
    }

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(ResultEnum.SUCCESS, data);
    }

    public static <T> ResultVo<T> fail(T data) {
        return new ResultVo<>(ResultEnum.FAIL, data);
    }

    public static <T> ResultVo<T> code(ResultEnum resultEnum, T data) {
        return new ResultVo<>(resultEnum, data);
    }
}
