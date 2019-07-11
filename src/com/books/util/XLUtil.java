package com.books.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-15 16:29<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class XLUtil {

    //如果target中的某个属性为null,用source中的对应属性填充
    public static void fillObjectWithNull(Object source, Object target)
    {
        Class cls = source.getClass();
        Field[] fields = cls.getDeclaredFields();

        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            // 修改访问控制权限
            f.setAccessible(true);
            try {
                String name =  f.getName();
                name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
                Object value = f.get(source);
                if (value != null && f.get(target) == null)
                {
                    Method method = cls.getMethod("set" +name);
                    method.invoke(target,value);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //提示
    public static void showAlert(String title, String content)
    {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle(title); //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText(content); //设置头标题，默认标题为本地语言的information
        information.setWidth(Constant.SCENE_WIDTH);
        information.showAndWait();
    }

    //确认框
    public static boolean showConfirm(String title, String content)
    {
        Alert information = new Alert(Alert.AlertType.CONFIRMATION);
        information.setTitle(title); //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText(content); //设置头标题，默认标题为本地语言的information
        information.setWidth(Constant.SCENE_WIDTH);
        Optional<ButtonType> _buttonType = information.showAndWait();
//        根据点击结果返回
        return _buttonType.get().getButtonData().isDefaultButton();

    }

}
