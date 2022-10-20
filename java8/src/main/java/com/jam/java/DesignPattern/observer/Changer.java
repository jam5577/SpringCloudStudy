package com.jam.java.DesignPattern.observer;

/**
 * @program: SpringCloudStudy
 * @description: 修改事件类
 * @author: Mr.Pu
 * @create: 2022-10-14 16:27
 **/

public class Changer {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
