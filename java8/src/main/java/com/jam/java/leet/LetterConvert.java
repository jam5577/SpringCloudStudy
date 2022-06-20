package com.jam.java.leet;

/**
 * @program: SpringCloudStudy
 * @description: 字母数字转换
 * @author: Mr.Pu
 * @create: 2022-06-20 08:15
 **/

public class LetterConvert {

    /**
     * 使用ascii码转换，输入1-26的数字，获取对应大写字母
     *
     * @param num 1-26的数字
     * @return 返回char类型大写字母，如需返回字符串，可以使用String.valueOf()
     */
    public static Character num2upper(int num) {
        return (char) (num + 64);
    }

    /**
     * 使用ascii码转换，输入1-26的数字，获取对应小写字母
     *
     * @param num 1-26的数字
     * @return 返回char类型小写字母，如需返回字符串，可以使用String.valueOf()
     */
    public static Character num2lower(int num) {
        return (char) (num + 96);
    }

    /**
     * 使用ascii码转换，输入任意大小写字母，获取对应数位
     *
     * @param character 任意大小写字母
     * @return 返回字母对应数位
     */
    public static int letter2num(Character character) {
        return character - 96 > 0 ? character - 96 : character - 64;
    }
}
