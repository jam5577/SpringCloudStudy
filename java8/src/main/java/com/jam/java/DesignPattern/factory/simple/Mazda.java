package com.jam.java.DesignPattern.factory.simple;

/**
 * @program: SpringCloudStudy
 * @description: 马自达
 * @author: Mr.Pu
 * @create: 2022-10-14 14:55
 **/

public class Mazda implements Car {
    @Override
    public void color() {
        System.out.println("silvery");
    }

    @Override
    public void brand() {
        System.out.println("Mazda");
    }
}
