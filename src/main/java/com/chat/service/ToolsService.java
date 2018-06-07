package com.chat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaolei hu
 * @date 2018/6/7 19:32
 **/
public interface ToolsService {

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    void makeCaptcha(HttpServletRequest request, HttpServletResponse response);
}
