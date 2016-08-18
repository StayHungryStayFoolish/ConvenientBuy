package com.convenientbuy.common.pojo;

import com.fasterxml.jackson.databind.JsonNode;
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

//  构造函数  -----------------------------------------------------------------------
    public Result() {
    }

    // 传入数据,响应状态就是200,表示正常
    public Result(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

//  get/set 方法  -----------------------------------------------------------------------

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

//  静态方法   -------------------------------------------------------------

    // 传入数据，反回响应状态 200 ,表示成功
    public static Result ok(Object data) {
        return new Result(data);
    }

    // 没有传输数据，反回响应状态 200 ,表示成功
    public static Result ok() {
        return new Result(null);
    }

    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public static Result build(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

// Json / Result 互相转换   -----------------------------------------------------------------------

    public static Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object object = null;
            if (clazz != null) {
                if (data.isObject()) {
                    object = MAPPER.readValue(data.traverse(), clazz);
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

}
