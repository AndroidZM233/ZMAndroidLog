package com.zm.utilslib.utils.data;

/**
 * byte数据转换
 * Created by 张明_ on 2017/8/21.
 * Email 741183142@qq.com
 */

public class ByteUtils {

    /**
     * byte[] --> 16进制String
     * byte[]{0x2B, 0x44, 0xEF,0xD9} --> "2B44EFD9"
     * @param byteArray byte[]
     * @return 16进制String
     */
    public static String toHexString(byte[] byteArray) {
        StringBuilder stringBuilder = new StringBuilder();
        if (byteArray == null || byteArray.length <= 0) {
            return null;
        }
        for (byte aSrc : byteArray) {
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
     * byte[] -> ascii String
     * {0x71,0x72,0x73,0x41,0x42}->"qrsAB"
     * @param byteArray byte[]
     * @return String
     */
    public static String toAsciiString(byte[] byteArray) {
        int byteLength = byteArray.length;
        StringBuilder tStringBuf = new StringBuilder();
        String nRcvString;
        char[] tChars = new char[byteLength];
        for (int i = 0; i < byteLength; i++) {
            tChars[i] = (char) byteArray[i];
        }
        tStringBuf.append(tChars);
        nRcvString = tStringBuf.toString();
        return nRcvString;
    }
}
