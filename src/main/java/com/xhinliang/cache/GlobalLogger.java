package com.xhinliang.cache;

import java.util.Arrays;

/**
 * @author xhinliang
 */
public class GlobalLogger {

    public static void log(String format, Object... args) {
        String logContent;
        if (args != null && args.length != 0) {
            format = format.replaceAll("\\{}", "%s");
            Object[] actualArgs = Arrays.stream(args) //
                    .map(o -> "" + o) //
                    .toArray();
            logContent = String.format(format, actualArgs);
        } else {
            logContent = String.valueOf(format);
        }
        System.out.println(logContent);
    }
}
