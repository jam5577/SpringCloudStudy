package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 圆形类
 * @author: Mr.Pu
 * @create: 2022-10-14 15:16
 **/

public class Circle implements Shape {
    @Override
    public void shape() {
        System.out.println("圆形");
    }
}
