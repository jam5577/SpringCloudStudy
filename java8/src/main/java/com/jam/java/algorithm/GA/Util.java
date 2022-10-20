package com.jam.java.algorithm.GA;

import java.util.Random;

/**
 * Description:
 *
 * @author 诸葛小猿
 * @date 2021-09-21
 */
public class Util {

    /**
     * 获取随机值          min   <=    返回值x    <=   max
     *
     * @return 返回最小最大之间的随机数
     */
    public static Double random(Double min, Double max) {
        Random rand = new Random();
        double result = 0;
        for (int i = 0; i < 10; i++) {
            result = min + (rand.nextDouble() * (max - min));
            result = (double) Math.round(result * 100) / 100;
            // System.out.println(result);
        }
        return result;
    }

    /**
     * 获取随机值 确保随机 均匀分布
     */
    public static Double random(DecisionVariable xRange) {
        return random(xRange.getMin(), xRange.getMax());
    }

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            if (random(0.0, 1.0) == 0.0) {
                System.out.println(i);
                i = 0;
            }
            i++;
        }
    }
}

