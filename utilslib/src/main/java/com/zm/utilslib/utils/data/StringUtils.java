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

package com.zm.utilslib.utils.data;


import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String数据判断、转换
 * Created by 张明_ on 2017/8/18.
 * Email 741183142@qq.com
 */

public class StringUtils {


    /**
     * 判断是否是中文名字
     *
     * @param str String
     * @return 验证通过返回true
     */
    public static boolean isChineseName(String str) {
        String regEx = "^[\\u4e00-\\u9fa5]{2,4}+$";
        return Pattern.matches(regEx, str);
    }

    /**
     * 通过判断是否是http开始,来判断是否是 url
     *
     * @param string String
     * @return 验证通过返回true
     */
    public static boolean isUrlString(String string) {
        return !TextUtils.isEmpty(string) && string.startsWith("http");
    }

    /**
     * 字符串都是数字吗
     *
     * @param str String
     * @return 验证通过返回true
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 手机号验证
     *
     * @param str String
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }


    /**
     * 16进制String  --> byte[]
     *
     * @param hexString String 十六进制
     * @return byte[]
     */
    public static byte[] hexStringToByteArray(String hexString) {
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

    /**
     * String转char数组
     *
     * @param value String
     * @return char[]
     */
    public static char[] toCharArray(String value) {
        char[] WriteText = new char[value.length() / 4];
        byte[] b_temp = new byte[value.length()];
        int i;
        for (i = 0; i < b_temp.length; ++i) {
            b_temp[i] = Byte.parseByte(value.substring(i, i + 1), 16);
        }
        for (i = 0; i < WriteText.length; ++i) {
            WriteText[i] = (char) ((b_temp[i * 4] & 15) << 12 | (b_temp[i * 4 + 1] & 15) << 8 | (b_temp[i * 4 + 2] & 15) << 4 | b_temp[i * 4 + 3] & 15);
        }
        return WriteText;
    }

    /**
     * String转16进制String
     *
     * @param value String
     * @return 16进制String
     */
    public static String toHexString(String value) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < value.length(); ++i) {
            char ch = value.charAt(i);
            String s4 = Integer.toHexString(ch);
            str.append(s4);
        }
        return str.toString();
    }
}
