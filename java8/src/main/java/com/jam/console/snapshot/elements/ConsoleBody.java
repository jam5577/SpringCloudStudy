package com.jam.console.snapshot.elements;

/**
 * @program: SpringCloudStudy
 * @description: body类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:17
 **/

public class ConsoleBody {

    private String body;

    private int length;

    public ConsoleBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
