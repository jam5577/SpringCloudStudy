package com.jam.java.DesignPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 事件类
 * @author: Mr.Pu
 * @create: 2022-10-14 16:13
 **/

public class Subject {
    private List<AbstractObserver> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    public void attach(AbstractObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        for (AbstractObserver observer : observers) {
            observer.update();
        }
    }
}
