package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 三角形
 * @author: Mr.Pu
 * @create: 2022-10-14 15:16
 **/

public class Rectangle implements Shape {
    @Override
    public void shape() {
        System.out.println("三角形");
    }
}
