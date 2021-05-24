package com.kaoshi.tyg.util;

public class TypeUtils {


    private static String[] types = {"单选题", "多选题", "判断题", "母题"};

    public static String getStrTypeByInt(Integer type) {

        if (type == null || type < 0 || type > types.length - 1)
            throw new RuntimeException("type 类型不对，请检查！");
        return types[type];
    }
}
