package com.books.servlet;

import com.books.domain.Lease;
import com.books.util.DateUtil;
import com.books.util.EasyUIResult;
import com.books.util.JDBCConnection;
import com.books.util.ResponseUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-07 21:33<br>
 * 邮箱: 1448564807@qq.com
 * 描述: <br>
 */
public class LeaseServlet extends HttpServlet {

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
                String isPayment = request.getParameter("isPayment");
                String startTime= request.getParameter("startTime");
                String endTime = request.getParameter("endTime");
                resultMap = findLease(isPayment,startTime,endTime);
                break;
            }
            case "create":
            {
                //[leaseName], [leaseTypeId], [leaseTypeName], [driverId], [driverName],
                // [state], [customerId], [customerName]
                String carId = request.getParameter("carId");
                String carName = request.getParameter("carName");
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String state = request.getParameter("state");
                String customerId = request.getParameter("customerId");
                String customerName = request.getParameter("customerName");
                String startTime = request.getParameter("startTime");
                String endTime = request.getParameter("endTime");
                String amount = request.getParameter("amount");
                String isPayment = request.getParameter("isPayment");

                String[] params = new String[11];
                params[0] = carId;
                params[1] = carName;
                params[2] = driverId;
                params[3] = driverName;
                params[4] = customerId;
                params[5] = customerName;
                params[6] = state;
                params[7] = startTime;
                params[8] = endTime;
                params[9] = amount;
                params[10] = isPayment;

                resultMap = createLease(params);
                break;
            }
            case "update":
            {
                String id = request.getParameter("id");
                String carId = request.getParameter("carId");
                String carName = request.getParameter("carName");
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String state = request.getParameter("state");
                String customerId = request.getParameter("customerId");
                String customerName = request.getParameter("customerName");
                String startTime = request.getParameter("startTime");
                String endTime = request.getParameter("endTime");
                String amount = request.getParameter("amount");
                String isPayment = request.getParameter("isPayment");

                Map<String,Object> params = new HashMap<>();
                params.put("carId",carId);
                params.put("carName",carName);
                params.put("driverId",driverId);
                params.put("driverName",driverName);
                params.put("state",state);
                params.put("customerId",customerId);
                params.put("customerName",customerName);
                params.put("startTime",startTime);
                params.put("endTime",endTime);
                params.put("amount",amount);
                params.put("isPayment",isPayment);
                params.put("id",id);

                resultMap = updateLease(params);
                break;
            }
            case "delete":
            {
                String ids = request.getParameter("ids");
                resultMap = deleteLease(ids);
                break;
            }

        }

        ResponseUtil.writeResponse(response,resultMap);
    }

    //查询租赁信息
    private Map findLease(String isPayment,String startTime, String endTime) {

        String sql = "SELECT id,carId,carName,driverId,driverName,state," +
                "customerId,customerName,startTime,endTime,amount,isPayment FROM [lease]";
        ResultSet resultSet = null;
        if (isPayment != null && !"".equals(isPayment))
        {
            sql += " WHERE isPayment = ?";

            if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime))
            {
                sql += " and endTime >= ? and endTime <= ?";
                resultSet = JDBCConnection.query(sql, isPayment, startTime, endTime);
            }else{
                resultSet = JDBCConnection.query(sql, isPayment);
            }

        }else{
            resultSet = JDBCConnection.query(sql, null);
        }


        List<Lease> leases = new ArrayList<>();

        Double sumAmount = 0.0;

        try {
            while (resultSet.next())
            {
                //构造一个租赁对象
                Lease lease = new Lease();
                lease.setId(resultSet.getLong(1));
                lease.setCarId(resultSet.getLong(2));
                lease.setCarName(resultSet.getString(3));
                lease.setDriverId(resultSet.getLong(4));
                lease.setDriverName(resultSet.getString(5));
                lease.setState(resultSet.getInt(6));
                lease.setCustomerId(resultSet.getLong(7));
                lease.setCustomerName(resultSet.getString(8));
                lease.setAmount(resultSet.getDouble(11));
                lease.setIsPayment(resultSet.getInt(12));

                Timestamp start = resultSet.getTimestamp(9);
                Timestamp end = resultSet.getTimestamp(10);
                LocalDateTime startTime1 = DateUtil.getDateTimeOfTimestamp(start.getTime());
                LocalDateTime endTime1 = DateUtil.getDateTimeOfTimestamp(end.getTime());
                lease.setStartTime(startTime1);
                lease.setEndTime(endTime1);

                leases.add(lease);
                sumAmount += lease.getAmount();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String, Object> map = EasyUIResult.result(leases);
        map.put("sumAmount",sumAmount);
        return map;
    }

    //创建租赁信息
    private Map createLease(String[] params) {

        String sql = "INSERT INTO [lease] ( [carId], [carName], [driverId], [driverName], [customerId], [customerName], [state], [startTime], [endTime], [amount], [isPayment])" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        boolean execute = JDBCConnection.execute(sql,params);

        String updateCar = "UPDATE [car] SET [state] = 1 WHERE [id] = " + params[0];
        JDBCConnection.execute(updateCar);

        return EasyUIResult.result(execute);
    }

    //更新租赁信息
    private Map updateLease(Map<String,Object> params) {

        //构造一个字符串数组,大小和参数键值对数量相同
        List<String> sqlParams = new ArrayList<>();

        String sql = "UPDATE [lease] SET ";

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

        String updateCar = "UPDATE [car] SET [state] = "+params.get("state")+" WHERE [id] = " + params.get("carId");
        JDBCConnection.execute(updateCar);

        return EasyUIResult.result(execute);
    }

    //删除租赁信息
    private Map deleteLease(String ids) {

        for (String id : ids.split("-")) {

            String sql = "delete FROM [lease] WHERE [id] = ?;";

            boolean execute = JDBCConnection.execute(sql,id);
        }

        return EasyUIResult.result(true);
    }

}
