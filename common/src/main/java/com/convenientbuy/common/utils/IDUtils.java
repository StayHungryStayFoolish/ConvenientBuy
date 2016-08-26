package com.convenientbuy.common.utils;

import java.util.Random;

/**
 * Created by bonismo@hotmail.com
 * 下午10:37 on 16/8/26.
 * <p>
 * ID 工具类
 */
public class IDUtils {

    /**
     * 生成图片名字
     *
     * @return
     */
    public static String genImageName() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end = random.nextInt(999);
        String string = millis + String.format("%03d", end);
        return string;
    }

    /**
     * 生成商品 ID
     *
     * @return
     */
    public static long getItemId() {
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end = random.nextInt(99);
        String string = millis + String.format("%02d", end);
        long id = new Long(string);
        return id;
    }


}
