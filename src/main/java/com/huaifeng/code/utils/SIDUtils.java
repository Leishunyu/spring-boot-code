package com.huaifeng.code.utils;

import java.util.Random;

/**
 * SIDUtils
 *
 * @author huaifeng
 * @since 2018-07-23
 */
public class SIDUtils {
    
    public static String getSerialVersionUID() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append(random.nextInt(7) + 1);
        for (int i = 0; i < 18; i++) {
            builder.append(random.nextInt(10));
        }
        builder.append("L");
        return builder.toString();
    }
}
