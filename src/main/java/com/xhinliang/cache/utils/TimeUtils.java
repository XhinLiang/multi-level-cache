package com.xhinliang.cache.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author xhinliang
 */
public class TimeUtils {

    public static void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
