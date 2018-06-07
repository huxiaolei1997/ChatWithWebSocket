package com.chat.serviceImpl;

import com.chat.service.ToolsService;
import com.chat.tools.Constant;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xiaolei hu
 * @date 2018/6/7 19:32
 **/
@Service
public class ToolsServiceImpl implements ToolsService {

    // 日志
    private static final Logger logger = Logger.getLogger(ToolsServiceImpl.class);

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @Override
    public void makeCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 获得 当前请求 对应的 会话对象
        HttpSession session = request.getSession();
        logger.info("开始生成验证码");
        final int width = 180; // 图片宽度
        final int height = 40; // 图片高度
        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)
        OutputStream output = null;
        try {
            output = response.getOutputStream();// 获得可以向客户端返回图片的输出流
            logger.info("验证码生成成功！"); // (字节流)
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建验证码图片并返回图片上的字符串
        String code = Constant.createCaptcha(width, height, imgType, output);
        // 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )
        session.setAttribute("captcha", code);
    }
}
