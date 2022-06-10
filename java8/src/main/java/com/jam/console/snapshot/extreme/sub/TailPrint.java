package com.jam.console.snapshot.extreme.sub;

import com.jam.console.snapshot.elements.ConsoleTail;
import com.jam.console.snapshot.extreme.ElementPrint;

/**
 * @program: SpringCloudStudy
 * @description: 打印标题接口
 * @author: Mr.Pu
 * @create: 2022-06-10 14:47
 **/

public interface TailPrint extends ElementPrint {

    /**
     * 打印标题方法
     */
    void printTail(ConsoleTail tail);
}
