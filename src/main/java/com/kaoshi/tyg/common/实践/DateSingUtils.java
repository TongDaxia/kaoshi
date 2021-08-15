package com.kaoshi.tyg.common.实践;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class DateSingUtils {

    /**
     * 指定日期签到
     *
     * @param sign 已签到的集合
     * @param day  需要添加的签到日期 1,2,3...
     * @return
     */
    public static Integer sign(Integer sign, Integer day) {

        return sign | 1 << day;
    }

    /**
     * 检查指定日期是否已签到
     *
     * @param sign
     * @param day
     * @return
     */
    public static boolean checkSign(Integer sign, Integer day) {
        return 0 != (sign & 1 << day);
    }


    /**
     * 统计本月已签到次数
     *
     * @param sign
     * @return
     */
    public static Integer countSign(Integer sign) {
        int count = 0;
        while (sign != 0) {
            count += (sign & 1);
            sign = sign >>> 1;
        }
        return count;
    }

    /**
     * 获取以前到的日期列表
     *
     * @param sign
     * @return
     */
    public static List<String> getSignDays(int sign) {
        List<String> result = new ArrayList<String>();
        int day = 0;
        while (sign != 0) {
            day++;
            sign = sign >>> 1;
            if (1 == (sign & 1)) {
                result.add(String.valueOf(day));
            }

        }
        return result;

    }

    public static void main(String[] args) {

        Integer defaultSign = 0;

        Integer sign = sign(defaultSign, 2);
        sign = sign(defaultSign, 2);
        Assert.isTrue(countSign(sign) == 1);
        Assert.isTrue(checkSign(sign, 2));

        sign = sign(sign, 13);

        Assert.isTrue(!checkSign(sign, 12));
        Assert.isTrue(checkSign(sign, 2));
        Assert.isTrue(checkSign(sign, 13));
        Assert.isTrue(!checkSign(sign, 12));
        Assert.isTrue(countSign(sign) == 2);

        for (int i = 0; i <= 31; i++) {
            sign = sign(sign, i);
        }

        System.out.println(getSignDays(sign).toString());

    }

}



