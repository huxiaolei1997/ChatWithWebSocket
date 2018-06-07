package com.chat.model;

import java.util.Map;

/**
 * http 请求返回的最外层对象
 * @author  xiaolei hu
 * @create  2018/5/29 16:46
 * @email huxiaolei1997@gmail.com
 **/
public class Result<T> {
    // 错误码
    private Integer code;

    // 提示信息
    private String msg;

    // 提示信息的具体内容
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":\"" + msg +
                "\", \"data\":" + data +
                '}';
    }

//    public static void main(String[] args) {
//        Result result = new Result();
//        result.setCode(0);
//        result.setMsg("test");
//        result.setData(null);
//        //Map<String, Object> resultMap = (Map<String, Object>) result;
//        System.out.println(result.toString());
//    }
}
