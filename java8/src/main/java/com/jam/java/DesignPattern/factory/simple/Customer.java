package com.jam.java.DesignPattern.factory.simple;

/**
 * @program: SpringCloudStudy
 * @description: 消费者，消费生产的车辆
 * @author: Mr.Pu
 * @create: 2022-10-14 15:00
 **/

public class Customer {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        // 奔驰
        Car mercedes = factory.create(Mercedes.class);
        mercedes.color();
        mercedes.brand();
        // 马自达
        Car mazda = factory.create(Mazda.class);
        mazda.color();
        mazda.brand();
        // 宝马
        Car bmw = factory.create(Bmw.class);
        bmw.color();
        bmw.brand();
    }
}
