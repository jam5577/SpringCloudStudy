package com.jam.java.leet;

import java.io.File;
import java.io.IOException;
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

    public static List<Boolean> leet1431(int[] candies, int extraCandies) {
        if (candies.length == 0) return null;
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
            left += first.equals("R") ? 1 : 0;
            right += first.equals("L") ? 1 : 0;
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

    public static int leet1725(int[][] rectangles) {
        int res = 0;
        int maxLen = Math.min(rectangles[0][0], rectangles[0][1]);
        for (int[] rectangle : rectangles) {
            int k = Math.min(rectangle[0], rectangle[1]);
            if (maxLen < k) {
                res += maxLen == k ? 1 : 0;
            }
        }
        return res;
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

    public static int[] leet1313(int[] nums) {
        int length = 0;
        for (int i = 0; i < nums.length; i += 2) {
            length += nums[i];
        }
        int[] numbers = new int[length];
        for (int i = 0; i < numbers.length; i++) {
        }
        return numbers;
    }

    public static String leet1678(String command) {
        StringBuilder builder = new StringBuilder();
        char[] chars = command.toCharArray();
        for (int i = 1; i < chars.length + 1; i++) {
            if (String.valueOf(chars[i - 1]).equals("G")) {
                builder.append("G");
            }
            if (String.valueOf(chars[i - 1]).equals("(")) {
                if (String.valueOf(chars[i]).equals(")")) {
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
            return (int) requests.stream().sorted().filter((x) -> x >= t - 3000 && x <= t).count();
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
            a = a.replace(c, "".toCharArray()[0]);
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
        String[] b = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
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

    public static int leet2202(int[] nums, int k) {
        //如果长度为1，只进行1次操作，返回-1
        //如果长度 >= k，则先执行k-1次删除操作，最后一次选择已删除的最大值加入，并返回，也就是选择除最后一个元素之前最大的数字返回
        //如果长度 < k：
        //            k - 长度 + 1 如果为偶数，则可以直接返回数组最大值
        //            k - 长度 + 1 如果为1，则就是k=长度的情况，如果为>1的奇数，则返回最大值
        if (nums.length <= 1) {
            return -1;
        }
        if (k == 0) {
            return nums[0];
        }
        int max = 0;
        int max1 = 0;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
        }
        for (int i = 0; i < k - 1; i++) {
            if (max1 < nums[i]) {
                max1 = nums[i];
            }
        }
        if (nums.length > k) {
            return Math.max(max1, nums[k]);
        } else if (nums.length == k) {
            return max1;
        } else if (nums.length < k && (k - nums.length + 1 % 2) == 0) {
            return max;
        } else if ((k - nums.length + 1) == 1) {
            return max1;
        } else {
            return max;
        }
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


    public static void main(String[] args) throws IOException {
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
//        System.out.println(leet1431(new int[]{2, 3, 5, 1, 3}, 3));
//        System.out.println(LCP0sa1(new int[]{3, 2, 3}, new int[]{3, 2, 1}));
//        System.out.println(leet1221("RLRRRLLRLL"));
//        System.out.println(leet1688(14));
//        System.out.println(1 % 2);
//        System.out.println(leet1281(234));
//        System.out.println(leet1678("G()()()()(al)"));
//        System.out.println(leet1342(14));
//        System.out.println(leet1832("qklccnqeicrabxpggieplwjhakurwwhxbugbryvhazoofifidzvxczmpdjfcyiuhqyedxhzexvpitxknjogpetvgxeqrjuuxzzfblhmhbgibocbhtcbgyxzchlawvnhczlecsrioapggorouzcputqsxhvoxbqxxydiumxwg"));
        System.out.println(leet461(4, 14));
        System.out.println(leet1689("27346209830709182346"));
    }
}
