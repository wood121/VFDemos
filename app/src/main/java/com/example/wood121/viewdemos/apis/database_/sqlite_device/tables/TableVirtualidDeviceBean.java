package com.example.wood121.viewdemos.apis.database_.sqlite_device.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.wood121.viewdemos.apis.database_.sqlite_device.bean.VirtualidDeviceBean;

/**
 * <p>描述：(表名、获取列名、对象转存储字段、字段转对象)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 15:03<br>
 * 版本： v2.0<br>
 */
public class TableVirtualidDeviceBean implements BaseColumns {
    public static final String TABLE_NAME = "VirtualidDeviceBean";
    /**
     * private String deviceId;
     * private String macAddress;
     * private String networkDeviceId;
     * private int meshId; //正常的0x8002 ~0xfffd
     * private int phoneId;//转为 1字节的
     * private int virtualId;//两个字节的数据
     */

    public static final String COLUMN_NAME_DEVICEID = "deviceId";
    public static final String COLUMN_NAME_MACADDRESS = "macAddress";
    public static final String COLUMN_NAME_NETWORKDEVICEID = "networkDeviceId";
    public static final String COLUMN_NAME_MESHID = "meshId";
    public static final String COLUMN_NAME_PHONEID = "phoneId";
    public static final String COLUMN_NAME_VIRTUALID = "virtualId";

    public static final String[] mColumns = {
            TableVirtualidDeviceBean._ID,
            TableVirtualidDeviceBean.COLUMN_NAME_DEVICEID,
            TableVirtualidDeviceBean.COLUMN_NAME_MACADDRESS,
            TableVirtualidDeviceBean.COLUMN_NAME_NETWORKDEVICEID,
            TableVirtualidDeviceBean.COLUMN_NAME_MESHID,
            TableVirtualidDeviceBean.COLUMN_NAME_PHONEID,
            TableVirtualidDeviceBean.COLUMN_NAME_VIRTUALID
    };

    public static String[] getColumnsNames() {
        return mColumns;
    }

    /**
     * 进行插入操作的时候需要
     *
     * @param deviceBean
     * @return
     */
    public static ContentValues createContentValues(VirtualidDeviceBean deviceBean) {
        // _ID not included since it is Auto-incremental
        ContentValues values = new ContentValues();
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_DEVICEID, deviceBean.getDeviceId());
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_MACADDRESS, deviceBean.getMacAddress());
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_NETWORKDEVICEID, deviceBean.getNetworkDeviceId());
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_MESHID, deviceBean.getMeshId());
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_PHONEID, deviceBean.getPhoneId());
        values.put(TableVirtualidDeviceBean.COLUMN_NAME_VIRTUALID, deviceBean.getVirtualId());
        return values;
    }

    /**
     * 查询动作结束的时候使用
     *
     * @param cursor
     * @return
     */
    public static VirtualidDeviceBean getVirtualidDeviceFromCursor(Cursor cursor) {
        VirtualidDeviceBean deviceBean = new VirtualidDeviceBean();

        String deviceId = cursor.getString(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_DEVICEID));
        String macAddress = cursor.getString(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_MACADDRESS));
        String networkDeviceId = cursor.getString(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_NETWORKDEVICEID));
        int meshId = cursor.getInt(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_MESHID));
        int phoneId = cursor.getInt(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_PHONEID));
        int virtualId = cursor.getInt(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_VIRTUALID));

        deviceBean.setDeviceId(deviceId);
        deviceBean.setMacAddress(macAddress);
        deviceBean.setNetworkDeviceId(networkDeviceId);
        deviceBean.setMeshId(meshId);
        deviceBean.setPhoneId(phoneId);
        deviceBean.setVirtualId(virtualId);

        return deviceBean;
    }
}
