package com.books.domain;

/**
 * 作者: 修罗大人<br>
 * 时间: 2019-06-08 20:07<br>
 * 邮箱: 1448564807@qq.com<br>
 * 描述: <br>
 */
public class Driver {

    private Long id;

    /**
     * 司机名称
     */
    private String name;

    /**
     * 司机状态 0可用 1不可用
     */
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
