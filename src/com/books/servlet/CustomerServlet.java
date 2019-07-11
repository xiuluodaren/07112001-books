package com.books.servlet;

import com.books.domain.Customer;
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
public class CustomerServlet extends HttpServlet {

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
                resultMap = findCustomer();
                break;
            }
            case "create":
            {
                String name = request.getParameter("name");
                resultMap = createCustomer(name);
                break;
            }
            case "update":
            {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                resultMap = updateCustomer(id,name);
                break;
            }
            case "delete":
            {
                String ids = request.getParameter("ids");
                resultMap = deleteCustomer(ids);
                break;
            }

        }

        ResponseUtil.writeResponse(response,resultMap);
    }

    //查询客户信息
    private Map findCustomer() {

        String sql = "SELECT id,name FROM [customer]";
        ResultSet resultSet = JDBCConnection.query(sql, null);

        List<Customer> customers = new ArrayList<>();

        try {
            while (resultSet.next())
            {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong(1));
                customer.setName(resultSet.getString(2));
                customers.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return EasyUIResult.result(customers);
    }

    //创建客户信息
    private Map createCustomer(String name) {

        String sql = "INSERT INTO [customer]([name]) VALUES (?);";

        boolean execute = JDBCConnection.execute(sql, name);

        return EasyUIResult.result(execute);
    }

    //更新客户信息
    private Map updateCustomer(String id, String name) {

        String sql = "UPDATE [customer] SET [name] = ? WHERE [id] = ?;";

        boolean execute = JDBCConnection.execute(sql, name,id);

        return EasyUIResult.result(execute);
    }

    //删除客户信息
    private Map deleteCustomer(String ids) {

        for (String id : ids.split("-")) {

            String sql = "delete FROM [customer] WHERE [id] = ?;";

            boolean execute = JDBCConnection.execute(sql,id);
        }

        return EasyUIResult.result(true);
    }

}
