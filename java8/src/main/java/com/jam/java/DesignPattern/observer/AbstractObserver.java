package com.jam.java.DesignPattern.observer;

/**
 * @program: SpringCloudStudy
 * @description: 抽象观察者类
 * @author: Mr.Pu
 * @create: 2022-10-14 16:20
 **/

public abstract class AbstractObserver {
    protected Subject subject;

    public abstract void update();
}
