package com.book.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 作�??:修罗大人<br>
 * 时间:2019-05-08 22:17<br>
 * 描述:<br>
 */
public class LoadPropertiesFile {

    private static Properties prop = null;

   public static void load(){
       try {
           // 获取文件�?
           InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.config"); //方法2
           prop = new Properties();
           prop.load(new InputStreamReader(inputStream, "UTF-8")); //加载格式化后的流
       } catch (FileNotFoundException e) {
           System.out.println("properties文件路径有误�?");
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public static String getProperty(String propertyName)
   {
       if (prop == null)
            load();

       String property = prop.getProperty(propertyName);
       return property;
   }

}
