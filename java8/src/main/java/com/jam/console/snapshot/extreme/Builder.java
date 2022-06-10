package com.jam.console.snapshot.extreme;

/**
 * @program: SpringCloudStudy
 * @description: 一个builder实现接口
 * @author: Mr.Pu
 * @create: 2022-06-10 14:01
 **/

public interface Builder<T> {

    /**
     * 类实现此方法可以使类具有builder生成的功能
     */
    T build();
}
