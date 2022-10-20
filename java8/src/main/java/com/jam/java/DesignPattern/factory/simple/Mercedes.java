package com.jam.java.DesignPattern.factory.simple;

/**
 * @program: SpringCloudStudy
 * @description: 梅赛德斯奔驰
 * @author: Mr.Pu
 * @create: 2022-10-14 14:53
 **/

public class Mercedes implements Car {
    @Override
    public void color() {
        System.out.println("black");
    }

    @Override
    public void brand() {
        System.out.println("Mercedes-Benz");
    }
}
