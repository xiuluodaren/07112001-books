package com.books.servlet;

import com.books.domain.CarType;
import com.books.util.EasyUIResult;
import com.books.util.JDBCConnection;
import com.books.util.ResponseUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.*;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-07 21:33<br>
 * 邮箱: 1448564807@qq.com
 * 描述: <br>
 */
public class CarTypeServlet extends HttpServlet {

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
                resultMap = findCarType();
                break;
            }
            case "create":
            {
                String typeName = request.getParameter("typeName");
                resultMap = createCarType(typeName);
                break;
            }
            case "update":
            {
                String typeName = request.getParameter("typeName");
                String id = request.getParameter("id");
                resultMap = updateCarType(id,typeName);
                break;
            }
            case "delete":
            {
                String ids = request.getParameter("ids");
                resultMap = deleteCarType(ids);
                break;
            }

        }

        ResponseUtil.writeResponse(response,resultMap);
    }

    //查询车辆类型信息
    private Map findCarType() {

        String sql = "SELECT id,typeName FROM [carType]";
        ResultSet resultSet = JDBCConnection.query(sql, null);

        List<CarType> carTypes = new ArrayList<>();

        try {
            while (resultSet.next())
            {
                CarType carType = new CarType();
                carType.setId(resultSet.getLong(1));
                carType.setTypeName(resultSet.getString(2));
                carTypes.add(carType);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return EasyUIResult.result(carTypes);
    }

    //创建车辆类型信息
    private Map createCarType(String typeName) {

        String sql = "INSERT INTO [carType]([typeName]) VALUES (?);";

        boolean execute = JDBCConnection.execute(sql, typeName);

        return EasyUIResult.result(execute);
    }

    //更新车辆类型信息
    private Map updateCarType(String id, String typeName) {

        String sql = "UPDATE [carType] SET [typeName] = ? WHERE [id] = ?;";

        boolean execute = JDBCConnection.execute(sql, typeName,id);

        return EasyUIResult.result(execute);
    }

    //删除车辆类型信息
    private Map deleteCarType(String ids) {

        for (String id : ids.split("-")) {

            String sql = "delete FROM [carType] WHERE [id] = ?;";

            boolean execute = JDBCConnection.execute(sql,id);
        }

        return EasyUIResult.result(true);
    }

}
