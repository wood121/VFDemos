package com.example.wood121.viewdemos.math;

import android.util.Log;

import com.example.wood121.viewdemos.util.BytesUtil;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/2/16 10:12<br>
 * 版本： v2.0<br>
 */
public class MathPractice {

    public void main(String[] args) {
        bytestransfer();
    }

    private void bytestransfer() {
        byte[] bytes = new byte[3];
        bytes[0] = 0x11;
        for (int i = 0; i < bytes.length; i++) {
            Log.e("wood121", bytes[i] + "");
        }

        //字节数组转16进制字符串
        byte[] hexstring = {0x11, 0x44, 126, 99, 0x55, 0x7e};
        Log.e("wood121,hexstring", BytesUtil.bytesToHexString(hexstring));

        byte[] bytes_1 = new byte[]{(byte) 0xA0, (byte) 0xB1, 0x2};
        Log.e("wood121,bytes_1", BytesUtil.bytesToHexString(bytes_1));

        //字符串转16进制字符串
        String str = "哈哈";
        String str2HexStr = BytesUtil.str2HexStr(str);
        Log.e("wood121,str2HexStr", str2HexStr);

        byte[] bytes1 = BytesUtil.hexStringToByte(str2HexStr);
        for (int i = 0; i < bytes1.length; i++) {
            Log.e("wood121,bytes1", bytes1[i] + "");
        }
    }
}
