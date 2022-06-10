package com.jam.console.snapshot.test;

import com.jam.console.snapshot.elements.ConsoleBody;
import com.jam.console.snapshot.elements.ConsoleTail;
import com.jam.console.snapshot.elements.ConsoleTitle;
import com.jam.console.snapshot.elements.Table;
import org.junit.Test;

import java.util.Collections;

/**
 * @program: SpringCloudStudy
 * @description: 测试类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:27
 **/

public class ConsoleTest {

    @Test
    public void test() {
        Table.builder().title(new ConsoleTitle("title"))
                .body(new ConsoleBody("body"))
                .tail(new ConsoleTail("tail"))
                .build().print();
//        DefaultConsoleTable.builder().build().print();
    }

    @Test
    public void out() {
//        System.out.print((char) ((char) 4 * '='));
        long start = System.currentTimeMillis();
        System.out.println(String.join("", Collections.nCopies(4, "=")));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void out1() {
        long start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append('=');
        }
        System.out.println(builder);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
