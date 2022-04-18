package com.java.leet;

import java.io.File;
import java.util.*;

/**
 * @program: SpringCloudStudy
 * @description: 记录力扣刷题
 * @author: Mr.Pu
 * @create: 2022-04-08 16:24
 **/

public class LeetCode {

    //链表构造
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //二叉树构造
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void leet3() {
        String s = "pwwkew";
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        System.out.println(max);
    }

    public static void leet9() {
        int x = -121;
        if (x < 0) return;
        StringBuilder builder = new StringBuilder(x);
        System.out.println(builder.toString());
        StringBuilder reverse = builder.reverse();
        System.out.println(reverse.toString());
        System.out.println(reverse.toString().contentEquals(builder));
    }

    public static void leet13() {
        //map集合存储对应的字符和数值
        //读取到字符串中 I X C 的字符时，先查看下一个字符是多少，如果有相关的就将其提取出来
        String s = "III";
        HashMap<Character, Integer> map = new HashMap<>();
        map.put("I".toCharArray()[0], 1);
        map.put("V".toCharArray()[0], 5);
        map.put("X".toCharArray()[0], 10);
        map.put("L".toCharArray()[0], 50);
        map.put("C".toCharArray()[0], 100);
        map.put("D".toCharArray()[0], 500);
        map.put("M".toCharArray()[0], 1000);
        char[] chars = s.toCharArray();
        int j = 0;
        for (int i = 1; i < s.length(); ++i) {
            boolean b = chars[i - 1] >= chars[i];
            System.out.printf("%s", b);
            if (b) {
                j += map.get(chars[i - 1]);
            } else {
                j -= map.get(chars[i - 1]);
            }
        }
        System.out.println(j);
    }

    public static void leet14() {
        String[] s = {"flower", "flow", "flight"};
        Character first = s[0].charAt(0);
        //如果三个单词首字母有两个不相同则返回空
        for (String value : s) {
            char c = value.charAt(0);
            if (first.equals(c)) {
                first = c;
            } else {
                return;
            }
        }
        StringBuilder builder = new StringBuilder();
        //对n个单词进行依次对比，直到有任意两个单词的同一位置的字母不相同，或是某个单词的字母遍历完
        for (String value : s) {

        }
    }

    public static void leet69() {
        int a = 8;
        double pow = Math.pow(a, 0.5);
        System.out.println(Math.floor(pow));
        long x = a;
        while (x * x > a) x = (x + a / x) / 2;
        System.out.println(x);
    }

    public static void leet25(ListNode head, int k) {

        //链表反转
        head = head.next;
    }

    public static void leet148(ListNode head) {
        if (head == null) return;
        int left;
        int right;
        left = head.val;
        right = head.next.val;
        if (left > right) {
            head.val = right;
            head.next.val = left;
        }
    }

    public static void byteD() {
        int key = 0;
        int max = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        Set<Integer> keySet = map1.keySet();
        for (Integer x : keySet) {
            key = x.compareTo(key) > 0 ? x : key;
            if (map1.containsKey(key)) {
                max = map1.get(key);
            }
        }
    }

    public static void removeEmptyFile() {
        final String path = "E:\\迅雷下载\\Elles";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (Objects.requireNonNull(f.list()).length == 0) {
                if (f.delete()) {
                    System.out.println(f.getName() + "文件已删除");
                }
            } else {
                System.out.println(f.getName() + "文件不为空");
            }
        }
    }

    public static void leet1920() {
        int[] nums = {0, 2, 1, 5, 3, 4};
        int[] ans = new int[nums.length];
        for (int i : nums) {
            ans[i] = nums[nums[i]];
        }
        System.out.println(Arrays.toString(ans));
    }

    public static void leet1929() {
        int[] nums = {1, 2, 1};
        int[] ans = new int[2 * nums.length];
        System.arraycopy(nums, 0, ans, 0, nums.length);
        System.arraycopy(nums, 0, ans, nums.length, nums.length);
        System.out.println(Arrays.toString(ans));
    }

    public static boolean leet2236(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

    public static void leet1863() {
        //XOR 异或运算，相同取0，相异取1,java中使用 ^ 进行运算
        //java的>>表示二进制右移，如14>>2就是14的二进制右移2位，移走的数字抛弃，计算剩下的值
        //>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。
//        System.out.println(2 >> 5);
//        int i = 2 ^ 5 ^ 6;
//        System.out.println(i);
        int[] nums = {1, 3};
        int res = 0;
        //获取数组的子集：使用二进制进行计算，一个n长度的集合，子集个数为2^n个，包括子集
        //其次，对于长度为n的数组，使用000...，001...来表示子集
        //如，长度为3的数组，子集可以表示为000，001，010，100...等，用0和1表示每个位上的元素不保留和保留
        for (long i = 0, max = 1L << nums.length; i < max; i++) {
            Set<Integer> set = new HashSet<>();
            int tmp = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(nums[j]);
                    tmp ^= nums[j];
                }
            }
            res += tmp;
            System.out.println(set);
            System.out.println(res);
        }
    }

    public static void leet1480() {
        int[] nums = {1, 1, 1, 1, 1};
        int[] res = new int[nums.length];
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] + tmp;
            tmp += nums[i];
        }
        System.out.println(Arrays.toString(res));
    }

    public static void leet1720(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            arr[i + 1] = encoded[i] ^ arr[i];
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void leet2011(String[] operations) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("X++", 1);
        map.put("++X", 1);
        map.put("X--", -1);
        map.put("--X", -1);
        int X = 0;
        for (String s : operations) {
            if (Objects.isNull(map.get(s))) {
                return;
            }
            X += map.get(s);
        }
        System.out.println(X);
    }

    public static void leet2114(String[] sentences) {
        int max = 0;
        for (String s : sentences) {
            String[] split = s.split(" ");
            max = Math.max(split.length, max);
        }
        System.out.println(max);
    }

    public static void offer58(String s, int n) {
        String builder = s.substring(n) +
                s.substring(0, n);
        System.out.println(builder);
    }

    public static ListNode leet237(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
        return node;
    }

    public static ListNode leet203(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = leet203(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void leet1486(int n, int start) {
        int[] nums = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = start + 2 * i;
            res ^= nums[i];
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(res);
    }

    public static void leet2006(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res = res + (Math.abs(nums[i] - nums[j]) == k ? 1 : 0);
            }
        }
        System.out.println(res);
    }

    public static int leet1672(int[][] accounts) {
        int max = 0;
        int res = 0;
        for (int[] account : accounts) {
            for (int i : account) {
                max += i;
            }
            res = Math.max(res, max);
            max = 0;
        }
        return res;
    }

    public static int leet2160(int num) {
        if (num == 0) return 0;
        //整数拆分
        int[] ints = new int[4];
        for (int i = 0; i < 4; i++) {
            ints[i] = (int) ((num % Math.pow(10, i + 1)) / Math.pow(10, i));
        }
        int[] array = Arrays.stream(ints).sorted().toArray();
        return 10 * (array[0] + array[1]) + array[2] + array[3];
    }

    public static int leet771(String jewels, String stones) {
        char[] jewel = jewels.toCharArray();
        System.out.println(jewel);
        int res = 0;
        char[] stone = stones.toCharArray();
        for (char s : stone) {
            for (char c : jewel) {
                if (s == c) {
                    res++;
                }
            }
        }
        return res;
    }

    public static List<String> leet2194(String s) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //字母是列，数字是行，先遍历列，再遍历行
        String[] split = s.split(":");
        if (split[0].equals(split[1])) return Collections.singletonList(split[0]);
        System.out.println(Arrays.toString(split));
        List<String> res = new ArrayList<>();
        int x = alpha.indexOf(String.valueOf(split[0].charAt(0)));
        int y = alpha.indexOf(String.valueOf(split[1].charAt(0)));
        System.out.println(x + " : " + y);
        for (int j = x; j < y - x; j++) {
            for (int i = Integer.parseInt(String.valueOf(split[0].charAt(1)));
                 i < Integer.parseInt(String.valueOf(split[1].charAt(1))); i++) {
                System.out.println(i + " : " + j);
                res.add(String.valueOf(alpha.charAt(j) + (char) i));
            }
        }
        System.out.println(res);
        return res;
    }

    public static ListNode leet21(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return null;
        } else if (list2 == null) {
            return null;
        } else if (list1.val < list2.val) {
            list1.next = leet21(list1.next, list2);
            return list1;
        } else {
            list2.next = leet21(list2.next, list1);
            return list2;
        }
    }

    public static int leet1512(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                res += nums[i] == nums[j] ? 1 : 0;
            }
        }
        return res;
    }

    public static void leet1614(String s) {

    }

    public static void main(String[] args) {
//        leet3();
//        leet69();
//        removeEmptyFile();
//        leet13();
//        leet1929();
//        leet1863();
//        leet1480();
//        leet1720(new int[]{1, 2, 3}, 1);
//        leet2011(new String[]{"--X", "X++", "X++"});
//        leet2114(new String[]{"alice and bob love leetcode", "i think so too", "this is great thanks very much"});
//        offer58("abcdefg", 2);
//        leet1486(4, 3);
//        leet2006(new int[]{10, 2, 10, 9, 1, 6, 8, 9, 2, 8}, 5);
//        System.out.println(leet1672(new int[][]{{1, 2, 3}, {3, 2, 1}}));
//        System.out.println(leet2160(4802));
//        System.out.println(leet2194("K1:L2"));


    }
}
