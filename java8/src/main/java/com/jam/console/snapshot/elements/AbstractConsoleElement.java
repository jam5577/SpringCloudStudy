package com.jam.console.snapshot.elements;

import com.jam.console.snapshot.extreme.sub.BodyPrint;
import com.jam.console.snapshot.extreme.sub.TailPrint;
import com.jam.console.snapshot.extreme.sub.TitlePrint;

/**
 * @program: SpringCloudStudy
 * @description: 抽象类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:41
 **/

public abstract class AbstractConsoleElement implements TitlePrint, BodyPrint, TailPrint {

    public abstract void printDelimiter(Table table);

    @Override
    public void printBody(ConsoleBody body) {
        System.out.println("body");
    }

    @Override
    public void printTail(ConsoleTail tail) {
        System.out.println("tail");
    }

    @Override
    public void printTitle(ConsoleTitle title) {
        System.out.println(title.getTitle());
    }

    @Override
    public void printElement(ConsoleTitle title, ConsoleBody body, ConsoleTail tail) {
    }
}
