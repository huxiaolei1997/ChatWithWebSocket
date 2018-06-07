package com.chat.model;

/**
 * @author xiaolei hu
 * @date 2018/6/4 19:02
 **/
public class UserLogin {
    private int id;
    private String userName;
    private String password;
    private String captcha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"userName\":\"" + userName +
                "\", \"password\":\"" + password +
                "\", \"captcha\":\"" + captcha +
                "\"}";
    }
}