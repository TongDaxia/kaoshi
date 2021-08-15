package com.kaoshi.tyg.common.leetcode;

public class 一维蓄水数组 {


    /**
     * 求出一个一维数组的蓄水数。数组中的每个位置上的值，就是高度，
     * 如果某个值的左右两边都有比它高的值，那么这个位置就可以蓄水。
     * 蓄水的容量 = max(左边， 右边) - 当前位置的值
     *
     * @param args
     */
    public static void main(String[] args) {


        //   能蓄水的位置和值：          0, 1, 2, 0, 0, 2, 2, 0
        int[] waterArray1 = new int[]{4, 3, 2, 5, 6, 4, 4, 7};
        int result1 = findTotalWaterStorage(waterArray1);
        System.out.println("蓄水总量为：" + result1);

        //   能蓄水的位置和值：          0, 0, 0, 3, 4, 1, 4, 0, 1, 2, 0, 0
        int[] waterArray2 = new int[]{2, 1, 5, 2, 1, 4, 1, 7, 2, 1, 3, 1};
        int result2 = findTotalWaterStorage(waterArray2);
        System.out.println("蓄水总量为：" + result2);

        //   能蓄水的位置和值：          0, 0, 0, 3, 4, 1, 4, 0, 1, 2, 0, 0
        int[] waterArray3 = new int[]{4, 1, 3, 5, 3, 6, 2, 1, 4, 7, 2, 6, 4};
        int result3 = findTotalWaterStorage(waterArray3);
        System.out.println("蓄水总量为：" + result3);

    }

    /**
     * 先求最大值和最大值的索引
     * 在分别求左边和右边的值
     * 从左到中间，记录之前的最大值
     * 比他小的累计差值
     * 比他大的
     *
     * @param waterArray
     * @return
     */
    private static int findTotalWaterStorage(int[] waterArray) {

        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < waterArray.length; i++) {
            if (waterArray[i] >= max) {
                max = waterArray[i];
                maxIndex = i;
            }
        }


        int sum = 0;
        int leftMax = 0;

        for (int i = 0; i < maxIndex; i++) {
            if (waterArray[i] >= leftMax) {
                leftMax = waterArray[i];
            } else if (waterArray[i] < leftMax) {
                sum = sum + (leftMax - waterArray[i]);
            }
        }

        int rightMax = 0;
        for (int i = waterArray.length - 1; i > maxIndex; i--) {
            if (waterArray[i] >= rightMax) {
                rightMax = waterArray[i];
            } else if (waterArray[i] < rightMax) {
                sum = sum + (rightMax - waterArray[i]);
            }
        }

        return sum;

    }


}
