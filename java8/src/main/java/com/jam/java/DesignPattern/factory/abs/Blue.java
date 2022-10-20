package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 蓝色
 * @author: Mr.Pu
 * @create: 2022-10-14 15:17
 **/

public class Blue implements Color {
    @Override
    public void color() {
        System.out.println("Blue");
    }
}
