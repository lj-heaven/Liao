package com.example.base.util.exception;

import com.example.base.util.result.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//用于自动生成 equals 和 hashCode 方法; callSuper = false 表示不考虑父类中的字段，只基于当前类的字段生成 equals 和 hashCode 方法
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private final Integer code;

    //方法名称
    private final String method;

    /**
     * 自定义异常
     */
    public CustomException(ResultEnum resultEnum, String method) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.method = method;
    }

    /**
     * 自定义异常
     */
    public CustomException(Integer code, String message, String method) {
        super(message);
        this.code = code;
        this.method = method;
    }
}
