package com.jam.java.io.netty.chat.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jam
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }
}
