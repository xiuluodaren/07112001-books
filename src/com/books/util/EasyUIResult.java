package com.books.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件名称：EasyUIResult<br>
 * 初始作者：修罗大人<br>
 * 创建日期：2019-05-27 16:14<br>
 * 功能说明：<br>
 * <br>
 */
public class EasyUIResult {

    public static final String SUCCESS = "success";
    public static final String ERROR_MSG = "errorMsg";
    public static final String MSG = "msg";
    public static final String ROWS = "rows";
    public static final String TOTAL = "total";

    public static Map<String, Object> result(List list) {
        Map<String, Object> map = new HashMap<>();
        map.put(EasyUIResult.ROWS, list);
        map.put(EasyUIResult.TOTAL, list.size());
        return map;
    }

    public static Map<String, Object> result(Boolean success) {
        Map<String, Object> map = new HashMap<>();
        map.put(EasyUIResult.SUCCESS,success);
        return map;
    }


}
