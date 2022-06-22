package com.jam.java.leet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * @program: SpringCloudStudy
 * @description: c++数据结构算法书题目
 * @author: Mr.Pu
 * @create: 2022-06-22 17:19
 **/
@Slf4j
//@SuppressWarnings("all")
public final class DataStructure {

    /**
     * 冒泡排序算法
     */
    @Test
    public void bubbleSort() {
        int[] num = new int[]{5, 2, 7, 4, 6, 3, 1};
        int n = num.length;
        //标志位flag
        boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = 1; i < n; i++) {
                if (num[i - 1] > num[i]) {
                    int temp = num[i - 1];
                    num[i - 1] = num[i];
                    num[i] = temp;
                    flag = false;
                }
            }
            n--;
        }
        log.info("排序后的数组为：" + Arrays.toString(num));
    }

    /**
     * 计算数字中1的个数
     */
    @Test
    public void bitCount() {
        int n = 441, ones = 0;
        while (n > 0) {
            ones += (1 & n);
            n >>= 1;
        }
        log.info("1的个数为:{}", ones);
        log.info("bitCount方法：{}", Integer.bitCount(n));
    }

    /**
     * 数组倒置，递归版
     * 所有的尾递归，均可以转换为迭代方法
     */
    public static void arrayReverse(int[] num, int lo, int li) {
        if (lo < li) {
            int temp = num[lo];
            num[lo] = num[li];
            num[li] = temp;
            arrayReverse(num, lo + 1, li - 1);
        }
        log.info("转置后的数组：{}", Arrays.toString(num));
    }

    /**
     * 数组倒置，迭代版
     */
    @Test
    public void reverseIterator() {
        int[] num = new int[]{3, 1, 4, 1, 5, 9, 2, 6};
        int lo = 0, li = 7;
        while (lo < li) {
            int temp = num[lo];
            num[lo] = num[li];
            num[li] = temp;
            lo++;
            li--;
        }
        log.info("转置后的数组：{}", Arrays.toString(num));
    }

    /**
     * 优化的幂计算，使用判断奇偶的方法来降低时间复杂度，时间复杂度为O(logn)
     */
    public static int powerCal(int n) {
        if (n == 0) {
            return 1;
        }
        return (n & 1) == 1 ? (int) Math.pow(powerCal(n >> 1), 2) << 1 :
                (int) Math.pow(powerCal(n >> 1), 2);
    }

    /**
     * 分治算法求数组的元素和
     *
     * @param num 数组
     * @param lo  左边界
     * @param li  右边界
     * @return 返回最终的和
     */
    public static int arraySum(int[] num, int lo, int li) {
        if (lo == li) {
            return num[li];
        }
        int mid = (lo + li) >> 1;
        return arraySum(num, lo, mid) + arraySum(num, mid + 1, li);
    }

    @Slf4j
    static class Fibonacci {

        /**
         * 斐波那契数列的二分递归算法，时间复杂度为O(2^n)
         *
         * @param n 计算的数字
         * @return 返回计算结果
         */
        public static int recursive(int n) {
            return n < 2 ? n : recursive(n - 1) + recursive(n - 2);
        }

        /**
         * 斐波那契数列的递归优化版，自底向上的方法，从fib(0)+fib(1)开始
         * 用传递的a值来记录计算结果，
         */
        public static int recursive(int a, int b, int n) {
            if (n > 2) {
                return recursive(a + b, a, n - 1);
            }
            return a;
        }

        /**
         * 斐波那契数列动态规划版
         */
        public static int dp(int n) {
            int f = 0, g = 1;//初始化：fib(0) = 0, fib(1) = 1
            while (n-- > 0) {
                g += f;
                f = g - f;//依据原始定义，通过n次加法和减法计算fib(n)
            }
            return f;
        }
    }

    public static void main(String[] args) {
        //arrayReverse(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 0, 7);
        //log.info("结果为：{}", powerCal(3));
        //System.out.println(arraySum(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 0, 7));
        System.out.println(Fibonacci.recursive(1, 1, 10));
        //System.out.println(Fibonacci.recursive(10));
        //System.out.println(Fibonacci.dp(10));
    }
}
