package com.books.servlet;

import com.books.domain.Driver;
import com.books.util.EasyUIResult;
import com.books.util.JDBCConnection;
import com.books.util.ResponseUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-07 21:33<br>
 * 邮箱: 1448564807@qq.com
 * 描述: <br>
 */
public class DriverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String method = request.getParameter("method");

        Map resultMap = new HashMap();

        switch (method)
        {
            case "find":
            {
                resultMap = findDriver();
                break;
            }
            case "create":
            {
                String name = request.getParameter("name");
                String state = request.getParameter("state");
                resultMap = createDriver(name,state);
                break;
            }
            case "update":
            {
                String name = request.getParameter("name");
                String state = request.getParameter("state");
                String id = request.getParameter("id");
                resultMap = updateDriver(id,name,state);
                break;
            }
            case "delete":
            {
                String ids = request.getParameter("ids");
                resultMap = deleteDriver(ids);
                break;
            }

        }

        ResponseUtil.writeResponse(response,resultMap);
    }

    //查询客户信息
    private Map findDriver() {

        String sql = "SELECT id,name,state FROM [driver]";
        ResultSet resultSet = JDBCConnection.query(sql, null);

        List<Driver> drivers = new ArrayList<>();

        try {
            while (resultSet.next())
            {
                Driver driver = new Driver();
                driver.setId(resultSet.getLong(1));
                driver.setName(resultSet.getString(2));
                driver.setState(resultSet.getInt(3));
                drivers.add(driver);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return EasyUIResult.result(drivers);
    }

    //创建客户信息
    private Map createDriver(String name,String state) {

        String sql = "INSERT INTO [driver]([name],[state]) VALUES (?,?);";

        boolean execute = JDBCConnection.execute(sql, name, state);

        return EasyUIResult.result(execute);
    }

    //更新客户信息
    private Map updateDriver(String id, String name,String state) {

        String sql = "UPDATE [driver] SET [name] = ?,[state] = ? WHERE [id] = ?;";

        boolean execute = JDBCConnection.execute(sql, name,state,id);

        return EasyUIResult.result(execute);
    }

    //删除客户信息
    private Map deleteDriver(String ids) {

        for (String id : ids.split("-")) {

            String sql = "delete FROM [driver] WHERE [id] = ?;";

            boolean execute = JDBCConnection.execute(sql,id);
        }

        return EasyUIResult.result(true);
    }

}
