package com.books.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-07 21:54<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class ResponseUtil {

    public static void writeResponse(HttpServletResponse response, Map map) {

        response.setHeader("Access-Control-Allow-Credentials","ture");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Connection","keep-alive");
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
