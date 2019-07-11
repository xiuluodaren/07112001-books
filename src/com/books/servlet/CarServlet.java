package com.books.servlet;

import com.books.domain.Car;
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
public class CarServlet extends HttpServlet {

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
                resultMap = findCar();
                break;
            }
            case "create":
            {
                //[carName], [carTypeId], [carTypeName], [driverId], [driverName],
                // [state], [customerId], [customerName]
                String carName = request.getParameter("carName");
                String carTypeId = request.getParameter("carTypeId");
                String carTypeName = request.getParameter("carTypeName");
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String state = request.getParameter("state");
                String customerId = request.getParameter("customerId");
                String customerName = request.getParameter("customerName");
                String[] params = new String[8];
                params[0] = carName;
                params[1] = carTypeId;
                params[2] = carTypeName;
                params[3] = driverId;
                params[4] = driverName;
                params[5] = state;
                params[6] = customerId;
                params[7] = customerName;

                resultMap = createCar(params);
                break;
            }
            case "update":
            {
                String id = request.getParameter("id");
                String carName = request.getParameter("carName");
                String carTypeId = request.getParameter("carTypeId");
                String carTypeName = request.getParameter("carTypeName");
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String state = request.getParameter("state");
                String customerId = request.getParameter("customerId");
                String customerName = request.getParameter("customerName");
                Map<String,Object> params = new HashMap<>();
                params.put("carName",carName);
                params.put("carTypeId",carTypeId);
                params.put("carTypeName",carTypeName);
                params.put("driverId",driverId);
                params.put("driverName",driverName);
                params.put("state",state);
                params.put("customerId",customerId);
                params.put("customerName",customerName);
                params.put("id",id);

                resultMap = updateCar(params);
                break;
            }
            case "delete":
            {
                String ids = request.getParameter("ids");
                resultMap = deleteCar(ids);
                break;
            }

        }

        ResponseUtil.writeResponse(response,resultMap);
    }

    //查询车辆信息
    private Map findCar() {

        String sql = "SELECT id,carName,carTypeId,carTypeName," +
                "driverId,driverName,state,customerId,customerName FROM [car]";
        ResultSet resultSet = JDBCConnection.query(sql, null);

        List<Car> cars = new ArrayList<>();

        try {
            while (resultSet.next())
            {
                //构造一个车辆对象
                Car car = new Car();
                car.setId(resultSet.getLong(1));
                car.setCarName(resultSet.getString(2));
                car.setCarTypeId(resultSet.getLong(3));
                car.setCarTypeName(resultSet.getString(4));
                car.setDriverId(resultSet.getLong(5));
                car.setDriverName(resultSet.getString(6));
                car.setState(resultSet.getInt(7));
                car.setCustomerId(resultSet.getLong(8));
                car.setCustomerName(resultSet.getString(9));

                cars.add(car);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return EasyUIResult.result(cars);
    }

    //创建车辆信息
    private Map createCar(String[] params) {

        String sql = "INSERT INTO [car] ( [carName], [carTypeId], [carTypeName], [driverId], [driverName], " +
                "[state], [customerId], [customerName] ) " +
                "VALUES (?,?,?,?,?,?,?,?);";

        boolean execute = JDBCConnection.execute(sql,params);

        return EasyUIResult.result(execute);
    }

    //更新车辆信息
    private Map updateCar(Map<String,Object> params) {

//        String sql = "UPDATE [car]" +
//                "SET [carName]=?,[carTypeId]=?,[carTypeName]=?,[driverId]=?,[driverName]=?," +
//                "[state]=?,[customerId]=?,[customerName]=? WHERE [id]=?;";

        //构造一个字符串数组,大小和参数键值对数量相同
        List<String> sqlParams = new ArrayList<>();

        String sql = "UPDATE [car] SET ";

        //遍历键值对,动态拼接sql
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        //索引
        for (Map.Entry e : entries)
        {
            String key = (String) e.getKey();
            String value = (String) e.getValue();

            if (key != null && value != null && !"id".equals(key))
            {
                sql += "[" + key +"]=?,";
                sqlParams.add(value);
            }

        }
        sql = sql.substring(0,sql.length() - 1);
        sql += " WHERE [id]=?;";
        sqlParams.add((String) params.get("id"));

        boolean execute = JDBCConnection.execute(sql, sqlParams);

        return EasyUIResult.result(execute);
    }

    //删除车辆信息
    private Map deleteCar(String ids) {

        for (String id : ids.split("-")) {

            String sql = "delete FROM [car] WHERE [id] = ?;";

            boolean execute = JDBCConnection.execute(sql,id);
        }

        return EasyUIResult.result(true);
    }

}
