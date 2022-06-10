package com.jam.console.snapshot.elements;

/**
 * @program: SpringCloudStudy
 * @description: 表尾类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:18
 **/

public class ConsoleTail {

    private String tail;

    private int length;

    public ConsoleTail(String tail) {
        this.tail = tail;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
