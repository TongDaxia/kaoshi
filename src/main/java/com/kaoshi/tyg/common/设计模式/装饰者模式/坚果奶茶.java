package com.kaoshi.tyg.common.设计模式.装饰者模式;

public class 坚果奶茶 extends 奶茶底子装饰者 {

    底子 base;

    public 坚果奶茶(底子 base) {
        this.base = base;
    }

    @Override
    public String getDesc() {
        return base.getDesc() + "坚果套餐";
    }

    @Override
    public double cost() {
        return 4 + base.cost();
    }


    public static void main(String[] args) {
        底子 红 = new 红茶奶茶();
        System.out.println(红.getDesc() + 红.cost());
        底子 绿 = new 绿茶奶茶();
        System.out.println(绿.getDesc() + 绿.cost());

        坚果奶茶 夏天的快乐 = new 坚果奶茶(绿);
        坚果奶茶 冬天的快乐 = new 坚果奶茶(红);
        System.out.println("夏天的快乐：" + 夏天的快乐.getDesc() + 夏天的快乐.cost());
        System.out.println("冬天的快乐：" + 冬天的快乐.getDesc() + 冬天的快乐.cost());

    }
}
