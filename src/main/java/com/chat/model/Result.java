package com.chat.model;

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
}
