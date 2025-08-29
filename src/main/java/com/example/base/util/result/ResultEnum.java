package com.example.base.util.result;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),

    FAIL(100, "失败"),

    UNKNOWN_EXCEPTION(102, "未知异常"),

    ADD_ERROR(103, "添加失败"),

    UPDATE_ERROR(104, "更新失败"),

    DELETE_ERROR(105, "删除失败"),

    GET_ERROR(106, "查询失败"),

    TOO_FREQUENT(107, "频繁点击"),

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     */
    public static ResultEnum getByCode(Integer code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }
}
