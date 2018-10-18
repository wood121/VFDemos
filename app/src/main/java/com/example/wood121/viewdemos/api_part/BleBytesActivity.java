package com.example.wood121.viewdemos.api_part;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.BytesUtil;

public class BleBytesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_bytes);

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
