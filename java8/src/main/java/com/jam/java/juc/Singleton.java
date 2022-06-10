package com.jam.java.juc;

/**
 * @program: SpringCloudStudy
 * @description: 单例模式双重检验实现
 * @author: Mr.Pu
 * @create: 2022-06-10 15:56
 **/

public class Singleton {

    /**
     * 创建静态实例，但并不赋初始值，并使用volatile关键字保证原子性和多线程之间的可见性
     */
    private static volatile Singleton INSTANCE;

    private Singleton() {
    }

    /**
     * 使用锁将判断的逻辑锁住，并通过双重判空来保证只有一个单例
     * 使用双重判空是因为在高并发时有可能在第一个判空时进入了多个线程，从而创建多个实例
     *
     * @return 返回单例
     */
    public Singleton getSingleton() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
