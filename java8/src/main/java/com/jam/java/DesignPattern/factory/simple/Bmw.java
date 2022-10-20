package com.jam.java.DesignPattern.factory.simple;

/**
 * @program: SpringCloudStudy
 * @description: 宝马
 * @author: Mr.Pu
 * @create: 2022-10-14 14:56
 **/

public class Bmw implements Car {
    @Override
    public void color() {
        System.out.println("blue");
    }

    @Override
    public void brand() {
        System.out.println("Bmw");
    }
}
