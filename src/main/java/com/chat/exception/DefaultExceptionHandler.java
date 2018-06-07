package com.chat.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.chat.tools.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolei hu
 * @date 2018/6/7 17:39
 **/
public class DefaultExceptionHandler implements HandlerExceptionResolver {
    // 日志
    private static final Logger logger = Logger.getLogger(DefaultExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        httpServletResponse.setStatus(HttpStatus.OK.value()); //设置状态码
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        httpServletResponse.setCharacterEncoding("UTF-8"); //避免乱码
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            httpServletResponse.getWriter().write(ResultUtil.error(1, e.getMessage()).toString());
        } catch (IOException ex) {
            logger.error("返回json数据时出现异常，" + ex.getMessage(), ex);
        }
        logger.info("有一个异常被捕获，异常信息是：" + e.getMessage());
        return mv;
    }
}
