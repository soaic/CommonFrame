package com.socketlib;

/**
 * TODO
 *
 * @author XiaoSai
 * @version V1.0
 * @since 2017/5/23.
 */

public class ByteUtil{
    /**
     * 将byte数组转换为int数据
     *
     * @param b 字节数组
     * @return 生成的int数据
     */
    public static int bytesToInt(byte[] b) {
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            ret += (b[i] & 0xFF) << 8 * (3 - i);
        }
        return ret;
    }

    /**
     * 将int类型的数据转换为byte数组 原理：将int数据中的四个byte取出，分别存储
     *
     * @param n 数据
     * @return 生成的byte数组
     */
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> 8 * (3 - i) & 0xFF);
        }
        return b;
    }
}
