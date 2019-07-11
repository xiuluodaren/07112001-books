package com.books.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 作者:修罗大人<br>
 * 时间:2019-05-08 21:06<br>
 * 描述:<br>
 */
public class JDBCConnection {

    private static String  driverName = null;
    private static String jdbcUrl = null;
    private static String jdbcUserName = null;
    private static String jdbcPassword = null;

    private JDBCConnection(){}

    private static Connection conn = null;

    static {

        driverName = LoadPropertiesFile.getProperty("driverName");
        jdbcUrl = LoadPropertiesFile.getProperty("jdbcUrl");
        jdbcUserName = LoadPropertiesFile.getProperty("jdbcUserName");
        jdbcPassword = LoadPropertiesFile.getProperty("jdbcPassword");

    }

    public static Connection getConnection()
    {
        if (conn != null)
        {
            return conn;
        }

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static ResultSet queryByList(String sql,List<String> paramList)
    {
        String[] params = new String[paramList.size()];

        for (int i = 0;i < paramList.size();i++)
        {
            String param = paramList.get(i);
            params[i] = param;
        }


        return query(sql,params);
    }

    public static ResultSet query(String sql)
    {
        return query(sql,null);
    }

    public static ResultSet query(String sql, String... params)
    {
        try {

            // 创建连接
            Connection conn = getConnection();

            // 编译sql语句
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);

            //循环设置参数
            if (params != null)
            {
                for (int i = 1;i <= params.length;i++)
                {
                    pst.setString(i,params[i-1]);
                }
            }

            // 执行查询
            ResultSet rSet = pst.executeQuery();

            return rSet;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static boolean execute(String sql, String... params)
    {
        try {

            // 创建连接
            Connection conn = getConnection();

            // 编译sql语句
            PreparedStatement pst = null;
            pst = conn.prepareStatement(sql);

            if (params != null)
            {
                for (int i = 1;i <= params.length;i++)
                {
                    pst.setString(i,params[i-1]);
                }
            }

            // 执行
            int execute = pst.executeUpdate();

            return execute > 0;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean execute(String sql, List<String> params)
    {
        String[] paramArr = new String[params.size()];

        for (int i = 0;i < params.size();i++)
        {
            paramArr[i] = params.get(i);
        }

        return execute(sql,paramArr);

    }

}
