package com.books.util;


import com.books.domain.User;

import java.io.Serializable;

/**
 * 作者:修罗大人
 * 时间:2019/1/15 下午4:58
 * 描述:本地线程变量
 */

public class LocalData implements Serializable {

    private static final ThreadLocal<User> currencyUserHolder = new ThreadLocal();

    public static User getCurrentUser() {
        return (User)currencyUserHolder.get();
    }

    public static void setCurrentUser(User user) {
        currencyUserHolder.set(user);
    }

}
