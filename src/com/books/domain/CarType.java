package com.books.domain;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-08 14:57<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class CarType {

    private Long id;

    /**
     * 类型名称
     */
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
