package com.jam.java.DesignPattern.factory.simple;

/**
 * @program: SpringCloudStudy
 * @description: 汽车工厂类，用于产生不同种的汽车
 * @author: Mr.Pu
 * @create: 2022-10-14 14:50
 **/

public class CarFactory {
    public Car create(Class clazz) {
        if (clazz == Mercedes.class) {
            return new Mercedes();
        } else if (clazz == Mazda.class) {
            return new Mazda();
        } else {
            return new Bmw();
        }
    }
}
