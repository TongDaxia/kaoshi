package com.kaoshi.tyg.彻底弄懂动态规划;

public class 初识动归 {

    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

    /**
     * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，
     * 在满足背包最大重量限制的前提下，
     * 背包中物品总重量的最大值是多少呢？
     *
     * @param i
     * @param cw
     */
    public void func(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了,i==n表示物品都考察完了
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }

        if (mem[i][cw]) {
            return; // 重复状态
        }
        mem[i][cw] = true; // 记录(i, cw)这个状态

        func(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            func(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }


    //weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j] = true;
                }
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        //从满足条件从大到小， 输出第一个结果
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }

    /**
     * states索引值代表当前的重量是否可以被满足
     * 只需要考虑
     *
     * @param items
     * @param n
     * @param w
     * @return
     */
    //weight:物品重量，n:物品个数，w:背包可承载重量
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;

        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {
                //把第i个物品放入背包
                if (states[j] == true) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) {
                return i;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        初识动归 s = new 初识动归();
        s.func(0, 0);
        System.out.println(s.maxW);
        System.out.println("-----------------------");

        int[] weight = {2, 2, 4, 6, 3}; // 物品重量
        System.out.println(s.knapsack(weight, 5, 9));
    }


}
