package com.books.servlet;

import com.books.domain.User;
import com.books.util.JDBCConnection;
import com.books.util.RandomValidateCode;
import com.books.util.ResponseUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-07 21:33<br>
 * 邮箱: 1448564807@qq.com
 * 描述: <br>
 */
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Map resultMap = new HashMap();

        if ("logout".equals(request.getParameter("method")))
        {
            request.getSession().setAttribute("user",null);
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }else if ("modifyPassword".equals(request.getParameter("method"))){
            String newPassword = request.getParameter("newPassword");
            User user = (User) request.getSession().getAttribute("user");

            String sql = "UPDATE [user] SET  [password] = ? WHERE [id] = ?;";
            JDBCConnection.execute(sql, newPassword, String.valueOf(user.getId()));

            resultMap.put("success",true);
            ResponseUtil.writeResponse(response,resultMap);
            return;
        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        String randCode = (String)session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        //判断验证码
        if(randCode != null && code != null && randCode.toLowerCase().equals(code.toLowerCase())) {
            try {
                // 创建连接
                Connection conn = JDBCConnection.getConnection();

                // 创建sql语句
                String sql = "SELECT id,username,PASSWORD,type FROM user where username = ? and password = ? ORDER BY id";
                // 编译sql语句
                PreparedStatement pst = null;
                pst = conn.prepareStatement(sql);
                pst.setString(1,userName);
                pst.setString(2,password);

                // 执行查询
                ResultSet rSet = pst.executeQuery();

                // 遍历结果
                User user = null;
                while (rSet.next()) {
                    user = new User();
                    user.setId(rSet.getLong(1));
                    user.setUserName(rSet.getString(2));
                    user.setPassword(rSet.getString(3));
                    user.setType(rSet.getInt(4));
                }

                if (user != null)
                {
                    session.setAttribute("user",user);
                    resultMap.put("success",true);
                }else{
                    resultMap.put("success",false);
                    resultMap.put("errMsg","用户名密码不匹配");
                }

            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("success",false);
            }
        }else{
            resultMap.put("errMsg","验证码错误");
            resultMap.put("success",false);
        }

        ResponseUtil.writeResponse(response,resultMap);
    }

}
