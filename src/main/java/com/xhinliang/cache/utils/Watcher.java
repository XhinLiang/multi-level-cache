package com.xhinliang.cache.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author xhinliang
 */
public class Watcher {

    private volatile long startTimeMills;
    private AtomicBoolean hasStop;

    public Watcher() {
        startTimeMills = System.currentTimeMillis();
        hasStop = new AtomicBoolean(false);
    }

    public long stop() {
        if (hasStop.compareAndSet(false, true)) {
            return System.currentTimeMillis() - startTimeMills;
        } else {
            throw new RuntimeException("watcher stop twice time!");
        }
    }
}
