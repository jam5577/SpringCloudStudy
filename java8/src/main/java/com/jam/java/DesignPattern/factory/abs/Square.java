package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 方形
 * @author: Mr.Pu
 * @create: 2022-10-14 15:17
 **/

public class Square implements Shape {
    @Override
    public void shape() {
        System.out.println("方形");
    }
}
