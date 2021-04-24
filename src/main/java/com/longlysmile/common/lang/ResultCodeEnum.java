package com.longlysmile.common.lang;

import lombok.Getter;

/**
 * 结果枚举类
 *
 * @author wujie
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    UNKNOWN_ERROR(500, "未知错误"),
    PARAM_ERROR(20002, "参数错误"),
    NULL_POINT(20003, "空指针异常"),
    HTTP_CLIENT_ERROR(20004, "客户端连接异常"),
    ;

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
