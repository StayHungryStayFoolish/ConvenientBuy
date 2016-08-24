package com.convenientbuy.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by bonismo@hotmail.com
 * 下午11:47 on 16/8/24.
 */
public class ExceptionUtil {

    public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        try {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }
    }
}
