package com.convenientbuy.common.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by bonismo@hotmail.com
 * 下午10:31 on 16/8/18.
 */
public class Result {

    // Jackson 对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public Result() {
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static Result build(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

}
