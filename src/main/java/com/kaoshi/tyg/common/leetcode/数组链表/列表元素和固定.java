package com.kaoshi.tyg.common.leetcode.数组链表;

import java.util.HashMap;
@SuppressWarnings("all")
public class 列表元素和固定 {


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 2,2,3,4,6,7,8,8,8};
        test(arr,12);
    }

    /**
     * 计算数字中两个数字和指定时数值的位置
     *
     * @param arr
     * @param sum
     */
    public static void test(int[] arr, int sum) {

        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer s = sum - arr[i];
            if (hash.containsKey(s)) {
                System.out.println(hash.get(s) + "    " + i);
                break;
            } else {
                hash.put(arr[i], i);
            }
        }

    }
}
