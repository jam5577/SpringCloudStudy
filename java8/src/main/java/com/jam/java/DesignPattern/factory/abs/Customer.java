package com.jam.java.DesignPattern.factory.abs;

/**
 * @program: SpringCloudStudy
 * @description: 消费者
 * @author: Mr.Pu
 * @create: 2022-10-14 15:23
 **/

public class Customer {
    public static void main(String[] args) {
        AbstractFactory shape = FactoryProducer.getFactory("SHAPE");
        Shape circle = shape.getShape("CIRCLE");
        circle.shape();
        AbstractFactory color = FactoryProducer.getFactory("COLOR");
        Color black = color.getColor("Black");
        black.color();
    }
}
