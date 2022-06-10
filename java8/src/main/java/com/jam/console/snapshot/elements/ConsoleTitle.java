package com.jam.console.snapshot.elements;

/**
 * @program: SpringCloudStudy
 * @description: 标题类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:18
 **/

public class ConsoleTitle {

    private String title;

    private int length;

    public ConsoleTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
