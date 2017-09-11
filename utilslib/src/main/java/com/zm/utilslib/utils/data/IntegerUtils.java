package com.zm.utilslib.utils.data;

import android.support.annotation.NonNull;

/**
 * Created by 张明_ on 2017/9/5.
 * Email 741183142@qq.com
 */

public class IntegerUtils {

    /**
     * 转16进制String
     * @param result int
     * @return HexString
     */
    @NonNull
    public static String toHexString(int result) {
        String toHexString = Integer.toHexString(result).toUpperCase();
        if (toHexString.length() == 1) {
            toHexString = "0" + toHexString;
        }
        if (toHexString.length() > 2) {
            toHexString = toHexString.substring(1, 3);
        }
        return toHexString;
    }
}
