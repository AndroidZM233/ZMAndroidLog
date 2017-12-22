package com.zm.utilslib.utils.data;

/**
 * CRC16编码
 * Created by 张明_ on 2017/12/7.
 * Email 741183142@qq.com
 */

public class CRC16Utils {

    private static char[] crc_ta = { 0x0000, 0x1021, 0x2042, 0x3063, 0x4084, 0x50a5,
            0x60c6, 0x70e7, 0x8108, 0x9129, 0xa14a, 0xb16b, 0xc18c, 0xd1ad,
            0xe1ce, 0xf1ef };

    //**********************************CRC-16/XMODEM x16+x12+x5+1***************************
    /**
     * 初始值0
     * @param buffer 需要校验的数据
     * @return 校验int结果
     */
    public static int CRCXMode(byte[] buffer) {
        short da;
        char CRC_16_Data = 0xffff;
        for (int i = 0; i < buffer.length; i++) {
            da = (short) (CRC_16_Data >> 12);
            CRC_16_Data <<= 4;
            CRC_16_Data ^= crc_ta[da ^ ((short) (char) (buffer[i] & 0xff) / 16)];
            da = (short) (CRC_16_Data >> 12);
            CRC_16_Data <<= 4;
            CRC_16_Data ^= crc_ta[da
                    ^ ((short) (char) (buffer[i] & 0xff) & 0x0f)];
        }
        return CRC_16_Data;
    }

    /**
     *
     * @param start 初始值
     * @param buffer 需要校验的数据
     * @return 校验结果（结果取非） int
     */
    public static int CRCXModeOFF(char start ,byte[] buffer) {
        short da;
        char CRC_16_Data = start;
        for (int i = 0; i < buffer.length; i++) {
            da = (short) (CRC_16_Data >> 12);
            CRC_16_Data <<= 4;
            CRC_16_Data ^= crc_ta[da ^ ((short) (char) (buffer[i] & 0xff) / 16)];
            da = (short) (CRC_16_Data >> 12);
            CRC_16_Data <<= 4;
            CRC_16_Data ^= crc_ta[da
                    ^ ((short) (char) (buffer[i] & 0xff) & 0x0f)];
        }
        //返回取非结果
        return ~CRC_16_Data;
    }

    //********************************************************************************************



    /**
     *
     * @param CRC
     * @return 返回2byte CRC校验结果
     */
    public static byte[] getCRC16Byte(int CRC) {
        byte[] result = new byte[2];
        result[0] = (byte) ((CRC >> 8) & 0xFF);
        result[1] = (byte) (CRC & 0xFF);
        return result;
    }

}
