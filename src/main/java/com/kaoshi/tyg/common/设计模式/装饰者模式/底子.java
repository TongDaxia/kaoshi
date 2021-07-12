package com.kaoshi.tyg.common.设计模式.装饰者模式;

public abstract class 底子 {
    String desc = "不知名底子";

    public String getDesc() {
        return desc;
    }

    public abstract double cost();
}
