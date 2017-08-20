/*
java基础数据类型转换：
简单数据类型之间的转换又分为自动类型转换（低级到高级）、强制类型转换（高级到低级以及同级之间）、包装类过度类型转换；
注意事项：
 1、boolean类型不能转换成其它数据类型
 2、低级别自动转换为高级别 ：byte,short,char->int->long->float->double
 3、同级之间不会自动转换，可以强制类型转换
 4、强制类型转换是由高级别像低级别之间转化，会降低精度准确性
 5、包装类：Boolean、Character、Byte、Short、Integer、Long、Float、Double
 */

package com.zm.utilslib.data;


/**
 * Created by 张明_ on 2017/8/18.
 * Email 741183142@qq.com
 */

public class DataConversionUtil {


    /**
     * byte[] --> 16进制String
     * byte[]{0x2B, 0x44, 0xEF,0xD9} --> "2B44EFD9"
     * @param src byte[]
     * @return 16进制String
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 16进制String  --> byte[]
     * @param hexString String 十六进制
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
