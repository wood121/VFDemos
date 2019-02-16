package com.example.wood121.viewdemos.apis.database_.sqlite_device;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wood121.viewdemos.apis.database_.sqlite_device.tables.TableVirtualidDeviceBean;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 14:49<br>
 * 版本： v2.0<br>
 */
public class DBDeviceHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DATABASE_NAME = "Vertualid.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TYPE_INTEGER = " integer";
    private static final String TYPE_TEXT = " text";
    private static final String TYPE_REAL = " real";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_VIRTUALID_DEVICEBEAN =
            "create table " + TableVirtualidDeviceBean.TABLE_NAME + "(" +
                    TableVirtualidDeviceBean._ID + " integer primary key autoincrement," +
                    TableVirtualidDeviceBean.COLUMN_NAME_DEVICEID + TYPE_TEXT + COMMA_SEP +
                    TableVirtualidDeviceBean.COLUMN_NAME_MACADDRESS + TYPE_TEXT + COMMA_SEP +
                    TableVirtualidDeviceBean.COLUMN_NAME_NETWORKDEVICEID + TYPE_TEXT + COMMA_SEP +
                    TableVirtualidDeviceBean.COLUMN_NAME_MESHID + TYPE_INTEGER + COMMA_SEP +
                    TableVirtualidDeviceBean.COLUMN_NAME_PHONEID + TYPE_INTEGER + COMMA_SEP +
                    TableVirtualidDeviceBean.COLUMN_NAME_VIRTUALID + TYPE_INTEGER + ")";

    public DBDeviceHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        Log.e("wood", "DBHelper===Super");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VIRTUALID_DEVICEBEAN);
        Log.e("wood", "DBHelper===onCreate：" + CREATE_VIRTUALID_DEVICEBEAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);   //重新创建表格
    }
}
