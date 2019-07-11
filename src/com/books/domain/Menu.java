package com.books.domain;

import java.io.Serializable;

/**
 * 文件名称：Menu<br>
 * 初始作者：修罗大人<br>
 * 创建日期：2019-05-27 10:58<br>
 * 邮箱: 1448564807@qq.com<br>
 * 功能说明：<br>
 */
public class Menu implements Serializable {

    private Long id;

    private String menuName;

    private String menuPath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }
}
