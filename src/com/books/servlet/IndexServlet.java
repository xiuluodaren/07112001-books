package com.books.servlet;

import com.books.domain.Menu;
import com.books.util.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-08 9:27<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null)
        {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        String sql = "SELECT id,menuName,menuPath FROM menu order by sort";
        ResultSet resultSet = JDBCConnection.query(sql, null);

        List<Menu> menuList = new ArrayList<>();

        try {
            while (resultSet.next())
            {
                Menu menu = new Menu();
                menu.setId(resultSet.getLong(1));
                menu.setMenuName(resultSet.getString(2));
                menu.setMenuPath(resultSet.getString(3));
                menuList.add(menu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        session.setAttribute("menuinfos",menuList);
        try {
            request.getRequestDispatcher("starter.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
