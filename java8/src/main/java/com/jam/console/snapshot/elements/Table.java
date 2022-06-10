package com.jam.console.snapshot.elements;

import com.jam.console.snapshot.extreme.Builder;
import com.jam.console.snapshot.extreme.ConsolePrint;

import java.util.Arrays;

import static com.jam.console.snapshot.constants.DelimiterConstants.DUAL_LINE;
import static com.jam.console.snapshot.constants.DualDelimiterConstants.*;

/**
 * @program: SpringCloudStudy
 * @description: 输出表格类
 * @author: Mr.Pu
 * @create: 2022-06-10 14:17
 **/

public class Table extends AbstractConsoleElement implements ConsolePrint {

    private ConsoleTitle title;

    private ConsoleBody body;

    private ConsoleTail tail;

    private String delimiter;

    public Table() {
    }

    public Table(TableBuilder builder) {
        this.title = builder.title;
        this.body = builder.body;
        this.tail = builder.tail;
        this.delimiter = builder.delimiter == null ? DUAL_LINE : builder.delimiter;
    }

    public static TableBuilder builder() {
        return new TableBuilder();
    }

    public static class TableBuilder implements Builder<Table> {

        private ConsoleTitle title;

        private ConsoleBody body;

        private ConsoleTail tail;

        private String delimiter;

        public TableBuilder title(ConsoleTitle title) {
            this.title = title;
            return this;
        }

        public TableBuilder body(ConsoleBody body) {
            this.body = body;
            return this;
        }

        public TableBuilder tail(ConsoleTail tail) {
            this.tail = tail;
            return this;
        }

        public TableBuilder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        @Override
        public Table build() {
            return new Table(this);
        }
    }

    public ConsoleTitle getTitle() {
        return title;
    }

    public void setTitle(ConsoleTitle title) {
        this.title = title;
    }

    public ConsoleBody getBody() {
        return body;
    }

    public void setBody(ConsoleBody body) {
        this.body = body;
    }

    public ConsoleTail getTail() {
        return tail;
    }

    public void setTail(ConsoleTail tail) {
        this.tail = tail;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }


    private String generate(int n, String c) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(c);
        }
        return builder.toString();
    }

    @Override
    public void printDelimiter(Table table) {
        int[] length = new int[]{table.getTitle().getTitle().length(),
                table.getBody().getBody().length(),
                table.getTail().getTail().length()};
        int max = Arrays.stream(length).max().getAsInt();
        String up = TOP_LEFT + generate(max, HORIZONTAL) + TOP_RIGHT;
        System.out.println(up);
        System.out.println(VERTICAL + table.getTitle().getTitle() + VERTICAL);
        String divider = LEFT_T + generate(max, HORIZONTAL) + RIGHT_T;
        System.out.println(divider);
        System.out.println(VERTICAL + table.getBody().getBody() + VERTICAL);
        System.out.println(divider);
        System.out.println(VERTICAL + table.getTail().getTail() + VERTICAL);
        String bottom = BOTTOM_LEFT + generate(max, HORIZONTAL) + BOTTOM_RIGHT;
        System.out.println(bottom);
    }

    @Override
    public void print() {
        printDelimiter(this);
    }
}
