package com.convenientbuy.common.utils;

import java.util.Random;

/**
 * Created by bonismo@hotmail.com
 * 下午10:37 on 16/8/26.
 */
public class IDUtils {

    public static String genImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end = random.nextInt(999);
        String string = millis + String.format("%03d", end);
        return string;
    }

    public static void main(String[] args) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());

    }
}
