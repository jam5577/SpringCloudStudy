package com.jam.console.snapshot.extreme;

import com.jam.console.snapshot.elements.ConsoleBody;
import com.jam.console.snapshot.elements.ConsoleTail;
import com.jam.console.snapshot.elements.ConsoleTitle;

/**
 * @program: SpringCloudStudy
 * @description: 元素打印输出顶级接口
 * @author: Mr.Pu
 * @create: 2022-06-10 13:49
 **/

public interface ElementPrint {

    /**
     * 此方法为各个元素打印输出的接口方法，对于不同元素，实现了此方法就可以打印输出
     */
    void printElement(ConsoleTitle title, ConsoleBody body, ConsoleTail tail);
}
