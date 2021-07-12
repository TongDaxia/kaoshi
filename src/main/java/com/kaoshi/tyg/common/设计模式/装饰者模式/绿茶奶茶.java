package com.kaoshi.tyg.common.设计模式.装饰者模式;

public class 绿茶奶茶 extends 底子 {
    public 绿茶奶茶(){
        desc = "绿茶奶茶！";
    }
    @Override
    public double cost() {
        return 11.5;
    }
}
