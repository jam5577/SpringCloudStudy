package test;

import org.junit.Test;

import java.util.HashMap;

/**
 * @program: SpringCloudStudy
 * @description: 测试泛型
 * @author: Mr.Pu
 * @create: 2022-02-22 14:43
 **/

public class Person<T> {

    @Test
    public void leet3() {
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

}
