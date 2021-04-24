package com.longlysmile.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author wujie
 * @version 1.0
 * @date 2020/10/25 21:18
 */
@Data
public class Result implements Serializable {

    private int code;

    private String msg;

    private Object data;

    Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    Result(ResultCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
    }

    public static Result success() {
        return new Result(ResultCodeEnum.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result(ResultCodeEnum.SUCCESS).addData(data);
    }

    /**
     * 链式添加数据
     *
     * @param data 要返回的数据
     * @return
     */
    private Result addData(Object data) {
        this.setData(data);
        return this;
    }

    public static Result fail() {
        return new Result(ResultCodeEnum.UNKNOWN_ERROR);
    }

    public static Result fail(String msg) {
        return new Result(500, msg, null);
    }

    public static Result fail(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

}
