package com.jam.java.leet;

import java.util.*;

/**
 * @program: SpringCloudStudy
 * @description: 记录力扣刷题
 * @author: Mr.Pu
 * @create: 2022-04-08 16:24
 **/

@SuppressWarnings("unused")
public final class LeetCode {

    static void binary() {
        System.out.println("╔═════");
        System.out.println("<========================================================================>");
        System.out.println("==位运算==按位与&==按位或|==异或XOR ^==取反~==左移<<==右移>>==无符号右移>>>==");
        System.out.println("===============================按位与  &==================================");
        System.out.println("== \t\t\t数字5的二进制是:" + Integer.toBinaryString(5) + "\t\t\t数字4的二进制是:" + Integer.toBinaryString(4) + "\t\t\t\t==");
        System.out.println("== \t\t\t\t\t二者按位与&的结果是:  5 & 4 = " + Integer.toBinaryString(5 & 4) + "\t\t\t\t\t==");
        System.out.println("================================按位或  |==================================");
        System.out.println("==二者按位或|的结果是:  5 | 4 = " + Integer.toBinaryString(5 | 4) + "==");
        System.out.println("================================异或XOR  ^=================================");
        System.out.println("==二者按位异或^的结果是:  5 ^ 4 = " + Integer.toBinaryString(5 ^ 4) + "==");
        System.out.println("================================取反  ~==================================");
        System.out.println("==数字按位取反~的结果是:  ~ 4 = " + Integer.toBinaryString(~4) + "==");
        System.out.println("================================左移  <<==================================");
        System.out.println("==数字按位左移<<的结果是:  4 << 2= " + Integer.toBinaryString(4 << 2) + "==");
        System.out.println("================================右移  >>==================================");
        System.out.println("==数字按位右移>>的结果是:  4 >> 2= " + Integer.toBinaryString(4 >> 2) + "==");
        System.out.println("================================无符号右移  >>>==================================");
        System.out.println("==数字按位无符号右移>>>的结果是:  -4 >>> 2= " + Integer.toBinaryString(-4 >>> 2) + "==");
        System.out.println("<========================================================================>");


    }

    /**
     * 链表构造
     */
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

    /**
     * 二叉树构造
     */
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

    /**
     * N叉树构造
     */
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    /**
     * leet117有next指针的二叉树
     */
    public static class NextNode {
        int val;
        NextNode left;
        NextNode right;
        NextNode next;

        NextNode() {
        }

        NextNode(int val) {
            this.val = val;
        }

        NextNode(int val, NextNode left, NextNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void leet3() {
        String s = "pwwkew";
        HashMap<Character, Integer> map = new HashMap<>(6);
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

    public static void leet9(int x) {
        if (x < 0) {
            return;
        }
        StringBuilder builder = new StringBuilder(x);
        System.out.println(builder);
        StringBuilder reverse = builder.reverse();
        System.out.println(reverse);
        System.out.println(reverse.toString().contentEquals(builder));
    }

    public static int leet13(String s) {
        //map集合存储对应的字符和数值
        //读取到字符串中 I X C 的字符时，先查看下一个字符是多少，如果有相关的就将其提取出来
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int value = map.get(s.charAt(i));
            if (i < n - 1 && value < map.get(s.charAt(i + 1))) {
                res -= value;
            } else {
                res += value;
            }
        }
        return res;
    }

    public static void leet69() {
        int a = 8;
        double pow = Math.pow(a, 0.5);
        System.out.println(Math.floor(pow));
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        System.out.println(x);
    }

    public static void leet148(ListNode head) {
        if (head == null) {
            return;
        }
        int left;
        int right;
        left = head.val;
        right = head.next.val;
        if (left > right) {
            head.val = right;
            head.next.val = left;
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
        if (num == 0) {
            return 0;
        }
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
        if (split[0].equals(split[1])) {
            return Collections.singletonList(split[0]);
        }
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

    public static int leet1512(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                res += nums[i] == nums[j] ? 1 : 0;
            }
        }
        return res;
    }

    public static int leet1614(String s) {
        int max = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == "(".charAt(0)) {
                left++;
                max = Math.max(left, max);
            } else if (")".charAt(0) == c) {
                left--;
            }
        }
        return max;
    }

    public static List<Boolean> leet1431(int[] candies, int extraCandies) {
        if (candies.length == 0) {
            return null;
        }
        int temp;
        List<Boolean> list = new ArrayList<>();
        int max = Arrays.stream(candies).max().getAsInt();
        for (int candy : candies) {
            temp = candy + extraCandies;
            list.add(Math.max(temp, max) == temp);
        }
        return list;
    }

    public static int LCP01(int[] guess, int[] answer) {
        int res = 0;
        for (int i = 0; i < guess.length; i++) {
            res += guess[i] == answer[i] ? 1 : 0;
        }
        return res;
    }

    public static int leet1221(String s) {
        int left = 0;
        int right = 0;
        int temp = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            String first = String.valueOf(s.charAt(i));
            left += "R".equals(first) ? 1 : 0;
            right += "L".equals(first) ? 1 : 0;
            if (s.charAt(i) != s.charAt(i + 1)) {
                temp++;
                i++;
            }
            if (left > 1 & right > 1) {
                temp++;
                left = 0;
                right = 0;
            }
        }
        return temp;
    }

    public static int leet1688(int n) {
        int res = 0;
        int match;
        if (n % 2 == 0 && n >= 2) {
            match = n / 2;
            n = n / 2;
        } else if (n % 2 != 0 && n >= 2) {
            match = (n - 1) / 2;
            n = (n - 1) / 2 + 1;
        } else {
            return res;
        }
        res += leet1688(n) + match;
        return res;
    }

    public static int leet1773(List<List<String>> items, String ruleKey, String ruleValue) {
        int res = 0;
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            map.put("type", items.get(i).get(0));
            map.put("color", items.get(i).get(1));
            map.put("name", items.get(i).get(2));
            if (map.containsKey(ruleKey)) {
                if (ruleValue.equals(map.get(ruleKey))) {
                    res = i + 1;
                    break;
                }
            }
            map.clear();
        }
        return res;
    }

    public static int LCP06(int[] coins) {
        int res = 0;
        for (int coin : coins) {
            if (coin == 1) {
                res += 1;
                continue;
            }
            //为偶数，除以2得到的结果直接加上，要过滤掉1
            if (coin / 2 == 0) {
                res += coin / 2;
            } else {
                //为奇数，除以2得到的结果加上余数再加和到最终结果
                res += (coin / 2) + (coin % 2);
            }
        }
        return res;
    }

    public static int[] leet1470(int[] nums, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(nums[i]);
            list.add(nums[n + i]);
        }
        int[] ints = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static int leet1588(int[] arr) {
        int res = 0;
        //定义start
        for (int start = 0; start < arr.length; start++) {
            //定义length
            for (int length = 1; length < arr.length + 1; length += 2) {
                int end = start + length;
                //定义end
                for (int i = start; i < end - 1; i++) {
                    res += arr[i];
                }
            }
        }
        return res;
    }

    public static String leet1108(String address) {
        return address.replace(".", "[.]");
    }


    public static int leet1281(int n) {
        int left = 1;
        int right = 0;
        //拆解数字
        while (n > 0) {
            int temp = n % 10;
            n /= 10;
            left *= temp;
            right += temp;
        }
        return left - right;
    }

    public static String leet1678(String command) {
        StringBuilder builder = new StringBuilder();
        char[] chars = command.toCharArray();
        for (int i = 1; i < chars.length + 1; i++) {
            if ("G".equals(String.valueOf(chars[i - 1]))) {
                builder.append("G");
            }
            if ("(".equals(String.valueOf(chars[i - 1]))) {
                if (")".equals(String.valueOf(chars[i]))) {
                    builder.append("o");
                    i++;
                } else {
                    builder.append("al");
                    i += 2;
                }
            }
        }
        return builder.toString();
    }

    public static int[] leet1389(int[] nums, int[] index) {
        int length = nums.length;
        int[] target = new int[length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(index[i], nums[i]);
        }
        for (int i = 0; i < target.length; i++) {
            target[i] = list.get(i);
        }

        return target;
    }

    static class RecentCounter {

        public static List<Integer> requests;

        public RecentCounter() {
        }

        public int ping(int t) {
            requests.add(t);
            return (int) requests.stream().filter((x) -> x >= t - 3000 && x <= t).count();
        }

        public List<Integer> offer042(List<Integer> list) {
            RecentCounter counter = new RecentCounter();
            List<Integer> integers = new ArrayList<>();
            for (Integer i : list) {
                if (Objects.isNull(i)) {
                    integers.add(null);
                } else {
                    integers.add(counter.ping(i));
                }
            }
            return integers;
        }
    }

    public static int leet1342(int num) {
        int res = 0;
        if (num == 0) {
            return res;
        }
        if (num % 2 == 0) {
            num /= 2;
        } else {
            num -= 1;
        }
        res++;
        res += leet1342(num);
        return res;
    }

    public static int leet1266(int[][] points) {
        int res = 0;
        //判断每两个点之间的距离，然后递归
        if (points.length == 1) {
            return res;
        }
        for (int i = 0; i < points.length - 1; i++) {
            int first = Math.abs(points[i + 1][0] - points[i][0]);
            int second = Math.abs(points[i + 1][1] - points[i][1]);
            res += Math.max(first, second);
        }
        return res;
    }

    public static int leet1684(String allowed, String[] words) {
        int res = words.length;
        String a = "abcdefghijklmnopqrstuvwxyz";
        for (char c : allowed.toCharArray()) {
            a = a.replace(c, ' ');
        }
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (a.contains(String.valueOf(c))) {
                    res--;
                    break;
                }
            }
        }
        return res;
    }

    public static int[] leet1365(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums) {
                if (num > nums[i]) {
                    res[i]++;
                }
            }
        }
        return res;
    }

    public static boolean leet1832(String sentence) {
        if (sentence.length() < 26) {
            return false;
        }
        HashSet<Character> set = new HashSet<>();
        for (char c : sentence.toCharArray()) {
            set.add(c);
        }
        return set.size() == 26;
    }

    public static int leet2037(int[] seats, int[] students) {
        int res = 0;
        int[] array = Arrays.stream(seats).sorted().toArray();
        int[] stu = Arrays.stream(students).sorted().toArray();
        for (int i = 0; i < seats.length; i++) {
            res += Math.abs(array[i] - stu[i]);
        }
        return res;
    }

    public static int leet804(String[] words) {
        String a = "abcdefghijklmnopqrstuvwxyz";
        String[] b = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
                "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...",
                "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> set = new HashSet<>();
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            map.put(a.charAt(i), b[i]);
        }
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (char c : word.toCharArray()) {
                builder.append(map.get(c));
            }
            set.add(builder.toString());
        }
        return set.size();
    }

    public static String leet1436(List<List<String>> paths) {
        if (paths.size() == 1) {
            return paths.get(0).get(1);
        }
        HashSet<String> set = new HashSet<>();
        for (List<String> path : paths) {
            set.add(path.get(0));
        }
        for (List<String> path : paths) {
            if (!set.contains(path.get(1))) {
                return path.get(1);
            }
        }
        return null;
    }

    public static int leet938(TreeNode root, int low, int high) {
        //二叉搜索树，左小右大
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return leet938(root.right, low, high);
        }
        if (root.val > high) {
            return leet938(root.left, low, high);
        }
        return root.val + leet938(root.left, low, high) + leet938(root.right, low, high);
    }

    public static int leet461(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static List<Integer> leet2089(int[] nums, int target) {
        int[] array = Arrays.stream(nums).sorted().toArray();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                list.add(i + 1);
            }
        }
        return list;
    }

    public static boolean leet1662(String[] word1, String[] word2) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String s : word1) {
            builder.append(s);
        }
        for (String s : word2) {
            builder1.append(s);
        }
        return builder.toString().equals(builder1.toString());
    }

    public static int leet2176(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int leet1290(ListNode head) {
        int res = 0;
        while (head != null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }

    public static int leet1295(int[] nums) {
//        return (int) Arrays.stream(nums).filter((x) -> String.valueOf(x).length() % 2 != 0).count();
        int res = 0;
        for (int num : nums) {
            res += String.valueOf(num).length() % 2 == 0 ? 1 : 0;
        }
        return res;
    }

    public static int leet1572(int[][] mat) {
        int res = 0;
        for (int i = 0; i < mat.length; ) {
            for (int j = 0; j < mat.length; j++) {
                res += mat[i][j];
                i++;
            }
        }
        for (int i = 0; i < mat.length; ) {
            for (int j = mat.length; j > 0; j--) {
                res += mat[i][j];
                i++;
            }
        }
        if (mat.length % 2 != 0) {
            res -= mat[mat.length / 2][mat.length / 2];
        }
        return res;
    }

    public static int[] leet1828(int[][] points, int[][] queries) {
        int[] answer = new int[queries.length];
        //给出点与圆心的距离平方小于半径平方
        //对圆循环操作，取出圆心和每个点，<=的就在answer的对应位置加1
        for (int i = 0; i < queries.length; i++) {
            int x0 = queries[i][0];
            int y0 = queries[i][1];
            for (int[] point : points) {
                int x1 = point[0];
                int y1 = point[1];
                answer[i] += Math.pow(y1 - y0, 2) + Math.pow(x1 - x0, 2) <= Math.pow(queries[i][2], 2) ? 1 : 0;
            }
        }
        return answer;
    }

    public static int leet807(int[][] grid) {
        int res = 0;
        int[] row = new int[grid.length];
        int[] column = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int left = grid[i][0], right = grid[0][i];
            for (int j = 0; j < grid.length; j++) {
                if (left < grid[i][j]) {
                    left = grid[i][j];
                }
                if (right < grid[j][i]) {
                    right = grid[j][i];
                }
            }
            row[i] = right;
            column[i] = left;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                res += Math.min(row[j], column[i]) - grid[i][j];
            }
        }
        return res;
    }

    public static List<List<Integer>> leet78(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0, max = (int) Math.pow(2, nums.length); i < max; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    set.add(nums[j]);
                }
            }
            lists.add(new ArrayList<>(set));
        }
        return lists;
    }

    public static int leet1689(String n) {
        char[] cs = n.toCharArray();
        int ans = 0;
        for (int c : cs) {
            ans = Math.max(ans, c - '0');
            System.out.println(c - '0');
        }
        return ans;
    }

    public static String leet14(String[] strs) {
        if (strs != null) {
            for (String str : strs) {
                if (str == null || str.length() == 0) {
                    return "";
                }
            }
        } else {
            return "";
        }
        Arrays.sort(strs);
        int length = strs.length;
        int n = strs[0].length();
        int m = strs[length - 1].length();
        int num = Math.min(n, m);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[length - 1].charAt(i)) {
                builder.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return builder.toString();
    }

    public static TreeNode leet226(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = leet226(root.left);
        root.left = leet226(root.right);
        root.right = left;
        return root;
    }

    public static TreeNode leet617(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode treeNode = new TreeNode(root1.val + root2.val);
        treeNode.left = leet617(root1.left, root2.left);
        treeNode.right = leet617(root1.right, root2.right);
        return treeNode;
    }

    public static int[] leet338(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    public static int leet104(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = leet104(root.left);
        int right = leet104(root.right);
        return Math.max(left, right) + 1;
    }

    public static List<Integer> leet94(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        order(root, res);
        return res;
    }

    public static void order(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        order(root.left, res);
        res.add(root.val);
        order(root.right, res);
    }

    public static List<Integer> leet589(Node root) {
        //先序遍历 根 左 右
        //leet589(root)
        traverse(root);
        return list;
    }

    public static List<Integer> list = new ArrayList<>();

    public static void traverse(Node root) {
        if (root == null) {
            return;
        }
        List<Node> children = root.children;
        list.add(root.val);
        for (Node child : children) {
            traverse(child);
        }
    }

    public static boolean leet100(TreeNode p, TreeNode q) {
        //同时先序遍历 根 左 右
        //遇到不相同的值就返回false
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return leet100(p.left, q.left) && leet100(p.right, q.right);
    }

    /**
     * leet341迭代器接口
     */
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    /**
     * leet341扁平化嵌套列表迭代器
     */
    public static class NestedIterator implements Iterator<Integer> {

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return false;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Integer next() {
            return null;
        }
    }

    public static void test(int n) {
        if (n == 0) {
            return;
        }
        test(n - 1);
        System.out.println(n);
    }

    public static int leet543(TreeNode root) {
        maxDepth(root);
        return max;
    }

    public static int max = 0;

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(left + right, max);
        return max + 1;
    }

    public static List<Integer> leet144(TreeNode root) {
        traverse(root);
        return list;
    }

    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        traverse(root.left);
        traverse(root.right);
    }

    @SuppressWarnings("all")
    public static boolean leet234(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = head.next;
            fast = head.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode p1 = head;
        ListNode p2 = reverse(slow);
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode leet21(ListNode list1, ListNode list2) {
        //创建虚拟结点，一个dummy指针，一个list1指针，一个list2指针
        ListNode dummy = new ListNode(-1), p = dummy, p1 = list1, p2 = list2;
        //结束标志为p1或者p2为空
        while (p1 != null && p2 != null) {
            //判断元素大小并插入到p指针位置，在p1或者p2指针插入后才执行指针移动
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            //指针不断向前移动
            p = p.next;
        }
        //在循环结束后需要将没有遍历完的那一条链表剩余元素插入到p指针位置
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        //返回dummy除第一个节点后的链表
        return dummy.next;
    }

    public static ListNode leet23(ListNode[] lists) {
        ListNode dummy = new ListNode(-1), p = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    public static ListNode leet19(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode node = find(head, n);
        node.next = node.next.next;
        return dummy;
    }

    public static ListNode find(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode leet206(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static int leet169(int[] nums) {
        int n = nums.length / 2;
        return Arrays.stream(nums).sorted().skip(n).toArray()[0];
    }

    public static int leet11(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int ans = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, ans);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static List<List<Integer>> leet15(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = n - 1;
        int a, b, c;
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        while (left < right) {
            a = nums[left];
            b = nums[right];
            c = -(a + b);
            if (a + b < c) {
                while (left < right && a == nums[left]) {
                    left++;
                }
            } else if (a + b > c) {
                while (left < right && b == nums[right]) {
                    right--;
                }
            } else {
                res.add(Arrays.asList(a, b, c));
                while (left < right && a == nums[left]) {
                    left++;
                }
                while (left < right && b == nums[right]) {
                    right--;
                }
            }
        }
        return res;
    }

    public static int leet136(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    public static void leet283(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        for (; left < nums.length; left++) {
            nums[left] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static int leet1913(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }

    public static ListNode offer22(ListNode head, int k) {
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static List<List<Integer>> linkedList = new LinkedList<>();

    public static List<List<Integer>> leet46(int[] nums) {
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> list = new LinkedList<>();
        trackback(nums, list, used);
        return linkedList;
    }

    static void trackback(int[] nums, LinkedList<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            linkedList.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            trackback(nums, list, used);
            list.removeLast();
            used[i] = false;
        }
    }

    public static int leet111(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //记录树结构，使用队列存储，为了先进先出
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()) {
            //拿到size做循环，对每一个node相邻节点做判断
            //此时的size是上一次循环后加入的数量，就是要对q中每一个节点都做判断
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //取出当前节点做判断
                TreeNode node = q.poll();
                //如果左右子树都为空则是叶子节点
                assert node != null;
                if (node.left == null && node.right == null) {
                    return depth;
                }
                //如果左子树是空则对左子树进行判断，直到叶子节点
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void leet48(int[][] matrix) {
        //先在对角线反转，然后对每一行使用reverse
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            reverse(row);
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void reverse(int[] row) {
        int i = 0, j = row.length - 1;
        while (j > i) {
            int temp = row[i];
            row[i] = row[j];
            row[j] = row[i];
            i++;
            j--;
        }
    }

    public static int leet462(int[] nums) {
        //对每一个元素都进行对比，然后取出最小值
        Arrays.sort(nums);
        int res = 0, n = nums.length, x = nums[n / 2];
        for (int num : nums) {
            res += Math.abs(num - x);
        }
        return res;
    }

    public static int leet961(int[] nums) {
        /*Arrays.sort(nums);
        int length = nums.length;
        boolean b = nums[length / 2] == nums[length / 2 + 1];
        return b ? nums[length / 2] : nums[length / 2 - 1];*/
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }

    public static int offer64(int n) {
//        return IntStream.range(1, n + 1).sum();
        boolean b = n > 0 && (n += offer64(n - 1)) > 0;
        return n;
    }

    public static int[] interview1601(int[] numbers) {
        int diff = numbers[1] - numbers[0];
        numbers[0] += diff;
        numbers[1] -= diff;
        return numbers;
    }

    public static TreeNode leet654(int[] nums) {
        return build(nums, 0, nums.length);
    }

    private static TreeNode build(int[] nums, int lo, int li) {
        if (lo > li) {
            return null;
        }
        int index = -1, max = Integer.MIN_VALUE;
        for (int i = lo; i < li; i++) {
            if (max < nums[i]) {
                index = i;
                max = nums[i];
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, li);
        return root;
    }

    public static List<Integer> leet448(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                list.add(i + 1);
            }
        }
        return list;
    }

    public static boolean leet101(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    static boolean check(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public static int leet53(int[] nums) {
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }

    public static int[] leet1313(int[] nums) {
        int length = 0;
        for (int i = 0; i < nums.length; i += 2) {
            length += nums[i];
        }
        int[] res = new int[length];
        int index = 0;
        for (int i = 1; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i - 1]; j++) {
                res[index++] = nums[i];
            }
        }
        return res;
    }

    public static boolean leet965(TreeNode root) {
        if (root == null) {
            return true;
        }
        val = root.val;
        same(root);
        return is;
    }

    static int val;
    static boolean is = true;

    public static void same(TreeNode root) {
        if (root == null || !is) {
            return;
        }
        if (root.val != val) {
            is = false;
            return;
        }
        same(root.left);
        same(root.right);
    }

    public static int leet2278(String s, char letter) {
        int len = 0;
        for (char c : s.toCharArray()) {
            len += c == letter ? 1 : 0;
        }
        return (int) Math.floor((1.0 * len / s.length()) * 100);
    }

    public static int leet2255(String[] words, String s) {
        int res = 0;
        for (String word : words) {
            res += s.startsWith(word) ? 1 : 0;
        }
        return res;
    }

    public static int leet1450(int[] startTime, int[] endTime, int queryTime) {
        int res = 0;
        for (int i = 0; i < startTime.length; i++) {
            res += (startTime[i] <= queryTime && endTime[i] >= queryTime) ? 1 : 0;
        }
        return res;
    }

    public static int LCP17(String s) {
        int x = 1, y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == "A".charAt(0)) {
                x = 2 * x + y;
            } else if (s.charAt(i) == "B".charAt(0)) {
                y = 2 * y + x;
            }
        }
        return x + y;
    }

    public static int LCP44(TreeNode root) {
        traverse(root, set);
        return set.size();
    }

    static Set<Integer> set = new HashSet<>();

    static void traverse(TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        set.add(root.val);
        traverse(root.left, set);
        traverse(root.right, set);
    }

    public static int leet2185(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            res += word.startsWith(pref) ? 1 : 0;
        }
        return res;
    }

    public static int leet467(String p) {
        int[] res = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) {
                k++;
            } else {
                k = 1;
            }
            res[p.charAt(i) - 'a'] = Math.max(res[p.charAt(i) - 'a'], k);
        }
        return Arrays.stream(res).sum();
    }

    public static int leet1967(String[] patterns, String word) {
        int res = 0;
        for (String s : patterns) {
            if (s.contains(word)) {
                res++;
            }
        }
        return res;
    }

    public static int leet704(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    static class BinarySearch {
        /**
         * 普通二分查找
         */
        public static int ordinary(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    return mid;
                }
            }
            return -1;
        }

        /**
         * 二分查找左边界
         */
        public static int left(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    right = mid - 1;
                }
            }
            if (left >= nums.length || nums[left] != target) {
                return -1;
            }
            return left;
        }

        /**
         * 二分查找右边界
         */
        public static int right(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    left = mid + 1;
                }
            }
            if (right < 0 || nums[right] != target) {
                return -1;
            }
            return right;
        }
    }

    public static int leet35(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.trimToSize();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return left;
    }

    public static int interview1711(String[] words, String word1, String word2) {
        int length = words.length, res = length;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 >= 0 && index2 >= 0) {
                res = Math.min(res, Math.abs(index1 - index2));
            }
        }
        return res;
    }

    public static String leet76(String s, String t) {
        Map<Character, Integer> needs = new HashMap<>(), window = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (needs.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                char d = s.charAt(left);
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                left++;
                if (needs.containsKey(d)) {
                    if (needs.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        System.out.println(left);
        System.out.println(len);
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static String leet1021(String s) {
        int left = 0, right = 1;
        LinkedList<String> temp = new LinkedList<>();
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else if (s.charAt(i) == ')') {
                r++;
            }
            if (l == r) {
                temp.add(s.substring(left, i + 1));
                l = 0;
                r = 0;
                left = i + 1;
            }
        }
        System.out.println(temp);
        StringBuilder builder = new StringBuilder();
        for (String s1 : temp) {
            builder.append(s1, 1, s1.length() - 1);
        }
        return builder.toString();
    }

    public static String leet1021new(String s) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();
    }

    public static String leet2000(String word, char ch) {
        if (!word.contains(String.valueOf(ch))) {
            return word;
        }
        int flag = word.indexOf(ch);
        StringBuilder builder = new StringBuilder(word.substring(0, flag + 1));
        return builder.reverse() + word.substring(flag + 1);
    }

    public static List<Integer> leet728(int left, int right) {
        List<Integer> res = new ArrayList<>();
        out:
        for (int i = left; i <= right; i++) {
            int cur = i;
            while (cur != 0) {
                int t = cur % 10;
                if (t == 0 || i % t != 0) continue out;
                cur /= 10;
            }
            res.add(i);
        }
        return res;
    }

    public static int leet1022(TreeNode root) {
        return traverse1022(root, 0);
    }

    static int traverse1022(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = (sum << 1) | root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return traverse1022(root.left, sum) + traverse1022(root.right, sum);
    }

    public static boolean leet2283(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char item = num.charAt(i);
            Integer value = item - '0';
            if (!map.containsKey(i)) {
                map.put(i, 0);
            }
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            char item = num.charAt(i);
            Integer value = item - '0';
            if (!value.equals(map.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int[] leet1299(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                max = Math.max(max, arr[j]);
            }
            if (i < arr.length - 1) {
                arr[i] = max;
            } else {
                arr[i] = -1;
            }
        }
        return arr;
    }

    public static int leet1837(int n, int k) {
        int res = 0, remain;
        while (n != 0) {
            remain = n % k;
            n = n / k;
            res += remain;
        }
        return res;
    }

    public static String leet1844(String s) {
        char[] chars = s.toCharArray();
        for (int i = 1; i < s.length(); i += 2) {
            chars[i] = (char) (chars[i] - '0' + chars[i - 1]);
        }
        return new String(chars);
    }

    public static String leet2108(String[] words) {
        for (String word : words) {
            StringBuilder builder = new StringBuilder(word);
            if (builder.reverse().toString().equals(word)) {
                return word;
            }
        }
        return "";
    }

    public static int leet929(String[] emails) {
        Set<String> hashSet = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            String s = split[0];
            if (s.indexOf('+') > 0) {
                s = s.substring(0, s.indexOf('+'));
            }
            hashSet.add(s.replace(".", "") + "@" + split[1]);
        }
        return hashSet.size();
    }

    public static void leet344(char[] s) {
        int length = s.length, semi = length % 2 == 0 ? length / 2 : length / 2 + 1;
        for (int i = 0; i < semi; i++) {
            char c = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = c;
            System.out.println(s[i]);
            System.out.println(s[length - i - 1]);
        }
    }

    public static String leet1528(String s, int[] indices) {
        int length = s.length();
        char[] res = new char[indices.length];
        for (int i = 0; i < length; i++) {
            res[indices[i]] = s.charAt(i);
        }
        return String.valueOf(res);
    }

    static class Leet478 {

        double radius, x, y;

        public Leet478(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.x = x_center;
            this.y = y_center;
        }

        public double[] randPoint() {
            while (true) {
                double a = new Random().nextDouble() * (2 * radius) - radius;
                double b = new Random().nextDouble() * (2 * radius) - radius;
                if (a * a + b * b <= radius * radius) {
                    return new double[]{a + x, b + y};
                }
            }
        }
    }

    public static int[] offer003(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    public static int leet1748(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                map.put(num, 0);
            }
        }
        for (Integer i : map.keySet()) {
            res += map.get(i) == 1 ? i : 0;
        }
        return res;
    }

    public static int leet1732(int[] gain) {
        int res = 0, max = 0;
        for (int j : gain) {
            max += j;
            res = Math.max(res, max);
        }
        return res;
    }

    public static boolean leet657(String moves) {
        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U':
                    y++;
                    continue;
                case 'R':
                    x++;
                    continue;
                case 'D':
                    y--;
                    continue;
                case 'L':
                    x--;
            }
        }
        return x == 0 && y == 0;
    }

    static class MyCalendarThree {

        private final TreeMap<Integer, Integer> map;

        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            int res = 0;
            int max = 0;
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                max += entry.getValue();
                res = Math.max(res, max);
            }
            return res;
        }
    }

    public static boolean leet1812(String coordinates) {
        int letter = coordinates.charAt(0) - 'a' + 1, number = coordinates.charAt(1) - '0';
        return ((letter + number) & 1) == 0;
    }

    public static int leet561(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    public static int interview0202(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;
    }

    public static int leet1979(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums[0]; i > 0; i--) {
            if (nums[0] % i == 0 && nums[nums.length - 1] % i == 0) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static int leet1827(int[] nums) {
        int res = 0, step = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                res += step - nums[i] + 1;
                nums[i] = step + 1;
                step = nums[i];
            } else {
                step = nums[i];
            }
        }
        return res;
    }

    public static boolean leet1037(int[][] points) {
        int k1 = (points[1][1] - points[0][1]) * (points[2][0] - points[0][0]);
        int k2 = (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
        return k1 != k2;

    }

    public static int[] offer17(int n) {
        int length = (int) Math.pow(10, n) - 1;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    public static boolean leet1704(String s) {
        String x = "aeiouAEIOU";
        int a = 0, b = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = x.contains(String.valueOf(s.charAt(i))) ? 1 : 0;
            if (i < s.length() / 2) {
                a += j;
            } else {
                b += j;
            }
        }
        return a == b;
    }

    public static TreeNode leet700(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val < val) {
            root = root.right;
        } else if (root.val > val) {
            root = root.left;
        } else {
            return root;
        }
        return leet700(root, val);
    }

    public static String leet709(String s) {
        return s.toLowerCase();
    }

    public static boolean leet1880(String firstWord, String secondWord, String targetWord) {
        return parse(targetWord) == parse(firstWord) + parse(secondWord);
    }

    static int parse(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            builder.append(c - 'a');
        }
        System.out.println(builder);
        return Integer.parseInt(builder.toString());
    }

    public static int leet191(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    public static int leet926(String s) {
        int dp = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                dp = Math.min(dp + 1, count);
            }
        }
        return dp;
    }

    public static String leet1309(String s) {
        StringBuilder sb = new StringBuilder();
        char[] map = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < s.length(); ) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                sb.append(map[Integer.parseInt(s.substring(i, i + 2)) - 1]);
                i += 3;
            } else {
                sb.append((char) (map[s.charAt(i) - '0'] - 1));
                i++;
            }
        }
        return sb.toString();
    }

    public static int[] leet942(String s) {
        int n = s.length(), min = 0, max = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[i] = s.charAt(i) == 'I' ? min++ : max--;
        }
        res[n] = min;
        return res;
    }

    public static List<String> leet890(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        Map<Character, Character> wor = new HashMap<>();
        Map<Character, Character> pat = new HashMap<>();
        for (String word : words) {
            int flag = 0;
            for (int i = 0; i < word.toCharArray().length; i++) {
                wor.put(pattern.charAt(i), wor.getOrDefault(pattern.charAt(i), word.charAt(i)));
                pat.put(word.charAt(i), pat.getOrDefault(word.charAt(i), pattern.charAt(i)));
                if (word.charAt(i) == wor.get(pattern.charAt(i)) && pattern.charAt(i) == pat.get(word.charAt(i))) {
                    flag++;
                }
            }
            wor.clear();
            pat.clear();
            if (flag == pattern.length()) {
                res.add(word);
            }
        }
        return res;
    }

    public static boolean leet2119(int num) {
        if (num == 0) {
            return true;
        }
        String s = String.valueOf(num);
        int length = s.length();
        return s.charAt(length - 1) == '0';
    }

    public static int leet1051(int[] heights) {
        int n = heights.length;
        int[] old = new int[n];
        System.arraycopy(heights, 0, old, 0, n);
        int res = 0;
        Arrays.sort(heights);
        for (int i = 0; i < n; i++) {
            if (old[i] != heights[i]) {
                res++;
            }
        }
        System.out.println(Arrays.toString(old));
        System.out.println(Arrays.toString(heights));
        return res;
    }

    public static int leet1464(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }

    public static int[] leet498(int[][] mat) {
        int m = mat.length, n = mat[0].length, pos = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 1) {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[pos] = mat[x][y];
                    pos++;
                    x++;
                    y--;
                }
            } else {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    res[pos] = mat[x][y];
                    pos++;
                    x--;
                    y++;
                }
            }
        }
        return res;
    }

    public static int leet2057(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean leet2206(int[] nums) {
        int left = 0, right = 1;
        Arrays.sort(nums);
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                return false;
            }
            left += 2;
            right += 2;
        }
        return true;
    }

    public static int leet2169(int num1, int num2) {
        int res = 0;
        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
            res++;
        }
        return res;
    }

    public static String leet1768(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length(), flag = 0;
        char[] res = new char[len1 + len2];
        for (int i = 0; i < len1 || i < len2; i++) {
            if (i < len1) {
                res[flag++] = word1.charAt(i);
            }
            if (i < len2) {
                res[flag++] = word2.charAt(i);
            }
        }
        return new String(res);
    }

    public static int leet762(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            int count = Integer.bitCount(i);
            if (isPrime(count)) {
                res++;
            }
        }
        return res;
    }

    static boolean isPrime(int j) {
        if (j < 2) {
            return false;
        }
        for (int i = 2; i * i <= j; i++) {
            if (j % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int leet532(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            if (visited.contains(num + k)) {
                res.add(num);
            }
            visited.add(num);
        }
        return res.size();
    }

    public static int leet1323(int num) {
        String n = String.valueOf(num);
        int flag = n.length() - 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '6') {
                builder.append('9');
                flag = i;
                break;
            }
            builder.append(n.charAt(i));
        }
        return Integer.parseInt(builder.append(n.substring(flag + 1)).toString());
    }

    public static ListNode offer024(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static String leet1859(String s) {
        String[] split = s.split(" ");
        String[] res = new String[split.length];
        for (String ss : split) {
            int c = Integer.parseInt(String.valueOf(ss.charAt(ss.length() - 1)));
            res[c] = ss.substring(0, ss.length() - 2);
        }
        return Arrays.toString(res);
    }

    public static void leet1089(int[] arr) {
        int n = arr.length;
        int top = 0;
        int i = -1;
        while (top < n) {
            i++;
            if (arr[i] != 0) {
                top++;
            } else {
                top += 2;
            }
        }
        int j = n - 1;
        if (top == n + 1) {
            arr[j] = 0;
            j--;
            i--;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            j--;
            if (arr[i] == 0) {
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }

    public static List<List<Integer>> leet118(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arrayList.add(1);
                } else {
                    arrayList.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(arrayList);
        }
        return res;
    }

    public static boolean leet572(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return leet572(root.left, subRoot) || leet572(root.right, subRoot);
    }

    static boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
    }

    public static int[] leet821(String s, char c) {
        int length = s.length();
        int[] res = new int[length];
        for (int i = 0, idx = -length; i < length; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = i - idx;
        }

        for (int i = length - 1, idx = 2 * length; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = Math.min(res[i], idx - i);
        }
        return res;
    }

    public static String leet67(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    public static String leet1047(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (res.length() > 0 && res.charAt(res.length() - 1) == c) {
                res.deleteCharAt(res.length() - 1);
                continue;
            }
            res.append(c);
        }
        return res.toString();
    }

    public static void leet88(int[] nums1, int m, int[] nums2, int n) {
        int[] temp1 = Arrays.copyOf(nums1, m), temp2 = Arrays.copyOf(nums2, n);
        int length = Math.min(m, n);
        for (int i = 0; i < length; i++) {
            nums1[i] = Math.min(temp1[i], temp2[i]);
        }
        if (m > length) {
            if (m - length - length >= 0) System.arraycopy(temp1, length, nums1, length, m - length - length);
        }
        if (n > length) {
            if (n - length - length >= 0) System.arraycopy(temp2, length, nums1, length, n - length - length);
        }
    }

    public static String leet648(List<String> dictionary, String sentence) {
        Set<String> dictionarySet = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (dictionarySet.contains(word.substring(0, 1 + j))) {
                    words[i] = word.substring(0, 1 + j);
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    public static int leet1974(String word) {
        byte[] bytes = word.getBytes();
        int seconds = Math.min(26 - Math.abs(97 - bytes[0]), Math.abs(97 - bytes[0])) + 1;
        for (int i = 1; i < bytes.length; i++) {
            int tmp = Math.abs(bytes[i - 1] - bytes[i]);
            seconds += Math.min(26 - tmp, tmp) + 1;
        }
        return seconds;
    }

    public static int leet682(String[] ops) {
        LinkedList<Integer> arrayList = new LinkedList<>();
        int res = 0;
        for (String op : ops) {
            switch (op) {
                case "C":
                    res -= arrayList.getLast();
                    arrayList.removeLast();
                case "D":
                    arrayList.addLast(arrayList.getLast() * 2);
                    res += arrayList.getLast();
                case "+":
                    arrayList.addLast(arrayList.get(arrayList.size() - 2) + arrayList.getLast());
                    res += arrayList.getLast();
                default:
                    res += Integer.parseInt(op);
                    arrayList.addLast(Integer.parseInt(op));
            }
        }
        return res;
    }

    public static ListNode leet2181(ListNode head) {
        ListNode res = new ListNode(-1);
        ListNode p = res;
        int sum = 0;
        head = head.next;
        while (head != null) {
            if (head.val != 0) {
                sum += head.val;
            } else {
                p.next = new ListNode(sum);
                p = p.next;
                sum = 0;
            }
            head = head.next;
        }
        return res.next;
    }

    public static List<List<Integer>> offer079(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            temp.clear();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    temp.add(nums[j]);
                }
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }

    static class MagicDictionary {

        List<String> dict;

        public MagicDictionary() {
            this.dict = new ArrayList<>();
        }

        public void buildDict(String[] dictionary) {
            dict.addAll(Arrays.asList(dictionary));
        }

        public boolean search(String searchWord) {
            int flag;
            for (String s : dict) {
                flag = 0;
                if (s.length() != searchWord.length()) {
                    continue;
                }
                int n = s.length();
                for (int i = 0; i < n; i++) {
                    if (s.charAt(i) != searchWord.charAt(i)) {
                        flag++;
                    }
                }
                if (flag == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    public static TreeNode offer054(TreeNode root) {
        int sum = 0;
        if (root != null) {
            offer054(root.right);
            sum += root.val;
            root.val = sum;
            offer054(root.left);
        }
        return root;
    }

    public static int[] leet735(int[] asteroids) {
        int[] res;
        List<Integer> temp = new ArrayList<>();
        int n = asteroids.length, left = 0, right = 1;
        while (right < n) {
            if (asteroids[left++] * asteroids[right++] > 0) {
                temp.add(asteroids[left]);
            } else if (asteroids[left] > 0 && asteroids[right] < 0) {
                if (Math.abs(asteroids[left]) > Math.abs(asteroids[right])) {
                    right++;
                } else if (Math.abs(asteroids[left]) == Math.abs(asteroids[right])) {
                    left = right;
                    right++;
                } else {
                    left++;
                    right++;
                }
            } else if (asteroids[left] < 0 && asteroids[right] > 0) {
                left++;
                right++;
            }
        }
        res = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    public static void leet73(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static class MovingAverage {

        Queue<Integer> queue;
        int size;
        double sum;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            this.queue = new ArrayDeque<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if (queue.size() == this.size) {
                sum -= queue.poll();
            }
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }
    }

    @Deprecated
    public static int leet565(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int res = 0, temp;
        for (int num : nums) {
            temp = num;
            s.add(num);
            while (s.add(nums[temp])) {
                temp = nums[temp];
            }
            res = Math.max(res, s.size());
            s.clear();
        }
        return res;
    }

    public static int leet2315(String s) {
        int flag = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == '|') {
                flag++;
            }
            if (flag % 2 == 0 && c == '*') {
                res++;
            }
        }
        return res;
    }

    public static int leet1455(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    public static int leet2367(int[] nums, int diff) {
        int res = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int num : nums) {
            if (hashSet.contains(num + diff) && hashSet.contains(num + diff + diff)) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        binary();
//        leet3();
//        leet69();
//        removeEmptyFile();
//        System.out.println(leet13("III"));
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
//        System.out.println(leet1431(new int[]{2, 3, 5, 1, 3}, 3));
//        System.out.println(LCP0sa1(new int[]{3, 2, 3}, new int[]{3, 2, 1}));
//        System.out.println(leet1221("RLRRRLLRLL"));
//        System.out.println(leet1688(14));
//        System.out.println(1 % 2);
//        System.out.println(leet1281(234));
//        System.out.println(leet1678("G()()()()(al)"));
//        System.out.println(leet1342(14));
//        System.out.println(leet1832("qklccnqeicrabxpggieplwjhakurwwhxbugbryvhazoofifidzvxc
//        zmpdjfcyiuhqyedxhzexvpitxknjogpetvgxeqrjuuxzzfblhmhbgibocbhtcbgyxzchlawvnhczlecsrio
//        apggorouzcputqsxhvoxbqxxydiumxwg"));
//        System.out.println(leet461(4, 14));
//        System.out.println(leet1689("27346209830709182346"));
//        test(10);
//        long start = System.currentTimeMillis();
//        System.out.println(leet11(new int[]{8361, 5302, 8672, 2400, 5150, 3527, 9216,
//        6713, 2902, 310, 555, 9176, 311, 9968, 5705, 3983, 7992, 8553, 6953,
//        9541, 5828, 1750, 6731, 3552, 5274, 7303, 3724, 5387, 9504,
//        1900, 937, 1146, 7266, 7943, 7911, 9055, 8046, 7180, 6516, 7810,
//        686, 5210, 1956, 4540, 7540, 2083, 1579, 4260, 2450, 2527, 6524,
//        5723, 6766, 777, 5694, 6018, 2880, 3653, 6011, 8172, 5943, 2862, 6594, 2902, 9887,
//        5878, 3065, 8197, 9195, 4560, 3428, 2209, 475, 852, 9488, 3368, 4319, 6230, 1975,
//        5829, 9474, 4490, 2067, 6048, 9136, 5344, 6022, 1787, 5553, 140, 5130, 524, 3450,
//        4008, 721, 6154, 5598, 8219, 4614, 3404, 8232, 9023, 4552, 7711, 6057, 5324, 8578,
//        3595, 4663, 4, 3703, 1429, 7921, 3085, 3694, 1461, 8932, 2632, 7046, 801, 6043, 617,
//        7565, 3469, 1627, 1464, 3050, 7982, 6702, 5467, 8604, 5515, 9155, 3260, 5040, 313,
//        8885, 929, 4103, 7947, 1139, 702, 1047, 2889, 1439, 3945, 4738, 2462, 8491, 7699,
//        376, 4639, 1329, 3644, 7408, 3665, 7417, 1388, 861, 7510, 7908, 4568, 2618, 4565,
//        7222, 2003, 1586, 9494, 1744, 7997, 7389, 9476, 2752, 701, 5925, 4963, 6859, 1634,
//        7170, 1336, 1514, 6757, 698, 5123, 4390, 7910, 7527, 9520, 156, 6402, 1428, 789,
//        3411, 106, 3206, 8216, 700, 994, 337, 9329, 5310, 7897, 1462, 5709, 872, 1482}));
//        long end = System.currentTimeMillis();
//        System.out.println((end - start));
//        System.out.println(leet136(new int[]{4, 1, 2, 1, 2}));
//        leet283(new int[]{0, 2, 6, 8, 4, 1, 0, 6, 8, 7, 6, 0, 0, 0, 4, 0, 0});
//        System.out.println(leet1614("(1 + (2 * 3) + ((8) / 4)) + 1)"));
//        System.out.println(leet1913(new int[]{5, 6, 2, 7, 4}));
//        System.out.println(leet462(new int[]{1, 10, 2, 9}));
//        System.out.println(leet961(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
//        System.out.println(offer64(6));
//        System.out.println(interview1601(new int[]{1, 2})[0]);
//        System.out.println(interview1601(new int[]{1, 2})[1]);
//        System.out.println((4 >> 2) & 1);
//        System.out.println(4 | 6);
//        System.out.println(~4);
//        System.out.println(6 ^ 4);
//        System.out.println(15 % 8);
//        System.out.println(leet53(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        System.out.println(Arrays.toString("abcdefghijklmnopqrstuvwxyz".split("d")));
//        System.out.println("ab" + "a");
//        System.out.println(leet704(new int[]{-1, 0, 3, 5, 9, 12}, 2));
//        System.out.println(BinarySearch.ordinary(new int[]{-1, 0, 3, 5, 9, 12}, 2));
//        System.out.println(BinarySearch.right(new int[]{-1, 0, 3, 3, 9, 12}, 3));
//        System.out.println(leet35(new int[]{1, 3, 5, 6}, 7));
//        System.out.println(interview1711(new String[]{"I",
//                "am", "a", "student", "from",
//                "a", "university", "in", "a", "city"}, "a", "student"));
//        System.out.println(leet76("ADOBECODEBANC", "ABC"));
//        System.out.println(leet1021("(()())(())"));
//        System.out.println(leet1021new("(()())(())"));
//        System.out.println(leet2000("abcdefd", 'd'));
//        System.out.println(leet2283("1210"));
//        System.out.println(Arrays.toString(leet1299(new int[]{17, 18, 5, 4, 6, 1})));
//        System.out.println(leet1837(10, 10));
        //System.out.println(leet1844("a1c1e1"));
//        System.out.println((char) ('a' + 4));
//        System.out.println(leet929(new String[]{"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}));
//        leet344("hello".toCharArray());
//        System.out.println(Integer.bitCount(2));
//        System.out.println(leet1748(new int[]{1, 1, 1, 1, 1}));
//        System.out.println(leet1732(new int[]{-5, 1, 5, 0, -7}));
//        System.out.println(leet657("UD"));
//        System.out.println('c' - 'a');
//        System.out.println(10 & 1);
//        System.out.println(Integer.valueOf("021"));
//        System.out.println(parse("acb"));
//        System.out.println(Integer.toBinaryString(10));
//        System.out.println(Integer.toBinaryString(9));
//        System.out.println(Integer.toBinaryString(10 | 9));
//        System.out.println(leet926("0100001"));
//        System.out.println((char) ((int) 'z' - 25));
//        System.out.println(leet1309("10#11#12"));
//        System.out.println(Arrays.toString(new int[3]));//默认全为0
//        System.out.println(Arrays.toString(leet942("IDID")));
//        int i = 0, min = 0;
        //i += min++;//这样写的时候是先赋值再++
//        int[] res = {0, 1, 2, 3};
        //res[i++] 相当于 res[0]，会先执行res[i]，再++
//        System.out.println(res[i++]);
//        System.out.println(leet2119(5260));
//        System.out.println(leet1051(new int[]{1, 1, 4, 2, 1, 3}));
//        System.out.println(leet1464(new int[]{3, 4, 5, 2}));
//        System.out.println(leet2169(2, 3));
//        System.out.println((int) '2');
//        System.out.println(Integer.parseInt(String.valueOf('2')));
        //leet1089(new int[]{1, 0, 2, 3, 0, 4, 5, 0});
//        System.out.println((char) (5 + 96));
//        System.out.println(Arrays.equals(new int[]{1, 2}, new int[]{1, 2}));
//        List<String> s1 = new ArrayList<>();
//        List<String> s2 = new ArrayList<>();
//        s1.add("hello");
//        s2.add("hello");
//        System.out.println(s1.equals(s2));
//        System.out.println(leet67("11", "1"));
        System.out.println(leet1047("abbaca"));
    }
}
