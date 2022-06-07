package com.jam.java.feature.dao;

/**
 * 函数式接口，其中只能有一个抽象方法，但可以有多个default
 */
@FunctionalInterface
public interface MyInterface {

    Integer count(Integer a, Integer b);

//    void run();

    default void run() {
        System.out.println("第一个default方法");
    }

    ;
}
