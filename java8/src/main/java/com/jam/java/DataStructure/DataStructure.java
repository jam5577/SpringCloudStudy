package com.jam.java.DataStructure;

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
     * 归并排序的拆分方法
     *
     * @param source 传入要排序的数组
     * @return 返回一个数组
     */
    public static int[] sort(int[] source) {
        //复制出一个数组，不修改原数组
        int[] arr = Arrays.copyOf(source, source.length);
        if (arr.length < 2) {
            return arr;
        }
        //取中点
        int mid = (int) Math.floor(source.length >> 1);
        //将数组分为左右两个数组
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(sort(left), sort(right));
    }

    /**
     * 将左右两个数组进行合并
     *
     * @param left  左侧数组
     * @param right 右侧数组
     * @return 返回排序后的数组
     */
    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0;
        //先进行排序
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                res[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                res[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        //将剩下的数插入结果中
        while (left.length > 0) {
            res[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0) {
            res[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return res;
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

    /**
     * 向量接口，其中存储的结构不一定是同一类型，不一定有相同初始值
     */
    interface Vector {

    }


    public static void main(String[] args) {
        //arrayReverse(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 0, 7);
        //log.info("结果为：{}", powerCal(3));
        //System.out.println(arraySum(new int[]{3, 1, 4, 1, 5, 9, 2, 6}, 0, 7));
        //System.out.println(Fibonacci.recursive(1, 1, 10));
        //System.out.println(Fibonacci.recursive(10));
        //System.out.println(Fibonacci.dp(10));
        System.out.println(Arrays.toString(sort(new int[]{6, 3, 2, 7, 1, 5, 8, 4})));
    }
}
