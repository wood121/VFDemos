package com.example.wood121.viewdemos.apis.database_.sqlite_device;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.SystemClock;
import android.util.Log;

import com.example.wood121.viewdemos.apis.database_.sqlite_device.bean.VirtualidDeviceBean;
import com.example.wood121.viewdemos.apis.database_.sqlite_device.tables.TableVirtualidDeviceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 1.创建 book.db，new DBHelper();
 * 2.mDatabase的创建与关闭
 * 3.增删改查底层方法: excuterInsert,excuteDelete,excuteUpdate,excuteSelect
 * 4.业务线参数传递：
 * 5.开启事物与提前编译SQL语句，优化插入
 * <p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 19:37<br>
 * 版本： v2.0<br>
 */
public class DBDeviceManager {
    private static DBDeviceManager mDBManager;
    private final Context mContext;
    private final DBDeviceHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public DBDeviceManager(Context applicationContext) {
        this.mContext = applicationContext;
        mDbHelper = new DBDeviceHelper(mContext);
    }

    public static DBDeviceManager getInstence(Context context) {
        if (mDBManager == null) {
            mDBManager = new DBDeviceManager(context.getApplicationContext());
        }
        return mDBManager;
    }

    /**
     * 已修改：  re-open an already-closed object
     *
     * @throws SQLException
     */
    private void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    private void close() {
        mDbHelper.close();
    }

    /**
     * ************************************增删改查--start***************************************
     */
    /**
     * @param tableName     需要插入的表
     * @param contentValues 具体的数值
     * @return insert into wood values(003,'java','lzl');   返回的是插入的行数
     */
    private long executeInsert(String tableName, ContentValues contentValues) {
        if (!mDatabase.isOpen()) open();
        long insert = mDatabase.insert(tableName, null, contentValues);
        return insert;
    }

    /**
     * @param tableName   表明
     * @param whereClause 某一个标识字段
     * @param whereArgs   标识字段的数值
     * @return delete from wood where id = '2'
     */
    private boolean executeDelete(String tableName, String whereClause, String[] whereArgs) {
        if (!mDatabase.isOpen()) open();
        int delete = mDatabase.delete(tableName, whereClause, whereArgs);
        return delete > 0;
    }

    /**
     * @param tableName     表的名字
     * @param contentValues 要修改成的数据
     * @param whereClause   where后的字段
     * @param whereArgs     where后字段的数值
     * @return "update wood set name='osi',password='ios1'  where id = 001"
     * @throws SQLException
     */
    private boolean executeUpdate(String tableName, ContentValues contentValues, String whereClause, String[] whereArgs) throws SQLException {
        if (!mDatabase.isOpen()) open();

        long numberOfRowsAffected = mDatabase.update(tableName, contentValues, whereClause, whereArgs);

        return (numberOfRowsAffected > 0);
    }

    /**
     * @param tableName   表的名字
     * @param columns     要查询的列名
     * @param whereClause
     * @param whereArgs
     * @param orderBy
     * @return Cursor
     * @throws SQLException
     */
    private Cursor executeSelect(String tableName, String[] columns, String whereClause, String[] whereArgs, String orderBy) throws SQLException {
        if (!mDatabase.isOpen()) open();
        Cursor cursor = mDatabase.query(tableName, columns, whereClause, whereArgs, null, null, orderBy);
        return cursor;
    }
    /**
     * ************************************增删改查--end***************************************
     */

    /**
     * ****************************************业务线--start*********************************************
     */

    /**
     * 1000-63,2000-65,5000-125,10k-202,20k-330
     * 批量插入
     *
     * @param list
     */
    public synchronized void executeTranStateInsert(ArrayList<VirtualidDeviceBean> list) {
        long start = SystemClock.currentThreadTimeMillis();
        String sql = "insert into " + TableVirtualidDeviceBean.TABLE_NAME
                + " (" + TableVirtualidDeviceBean.COLUMN_NAME_DEVICEID + ","
                + TableVirtualidDeviceBean.COLUMN_NAME_MACADDRESS + ","
                + TableVirtualidDeviceBean.COLUMN_NAME_NETWORKDEVICEID + ","
                + TableVirtualidDeviceBean.COLUMN_NAME_MESHID + ","
                + TableVirtualidDeviceBean.COLUMN_NAME_PHONEID + ","
                + TableVirtualidDeviceBean.COLUMN_NAME_VIRTUALID + ")"
                + " values (?,?,?,?,?,?)";
        if (!mDatabase.isOpen()) open();
        SQLiteStatement sqLiteStatement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            VirtualidDeviceBean deviceBean = list.get(i);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, deviceBean.getDeviceId());
            sqLiteStatement.bindString(2, deviceBean.getMacAddress());
            sqLiteStatement.bindString(3, deviceBean.getNetworkDeviceId());
            sqLiteStatement.bindString(4, deviceBean.getMeshId() + "");
            sqLiteStatement.bindString(5, deviceBean.getPhoneId() + "");
            sqLiteStatement.bindString(6, deviceBean.getVirtualId() + "");
            sqLiteStatement.executeInsert();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        mDatabase.close();
        Log.e("wood", "insert traceaction use time " + (SystemClock.currentThreadTimeMillis() - start));
    }

    /**
     * insertBook,getVirtualidDeviceBean,getVirtualidCursor，插入后又查出来
     *
     * @param deviceBean
     * @return
     */
    public synchronized VirtualidDeviceBean insertVirtualidDeviceBean(VirtualidDeviceBean deviceBean) {
        open();

        VirtualidDeviceBean insertDeviceBean = null;
        //插入
        long executeInsert = executeInsert(TableVirtualidDeviceBean.TABLE_NAME, TableVirtualidDeviceBean.createContentValues(deviceBean));
        //查询
        if (executeInsert > 0) {
            Log.e("wood", "DBManager,插入成功:" + deviceBean.toString());
            insertDeviceBean = getVirtualidDeviceBean(executeInsert);
        }

        close();
        return insertDeviceBean;
    }

    /**
     * 已修改：  cursor.moveToFirst();
     *
     * @param executeInsert
     * @return
     */
    private VirtualidDeviceBean getVirtualidDeviceBean(long executeInsert) {
        open();
        VirtualidDeviceBean virtualidDeviceBean = null;
        Cursor cursor = getVirtualidCursor(executeInsert);   //插入的那一行，获取油表对象
        Log.e("wood", "DBManager,cursor:" + cursor);
        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToFirst();
            virtualidDeviceBean = TableVirtualidDeviceBean.getVirtualidDeviceFromCursor(cursor);
            Log.e("wood", "bookFromCursor:" + virtualidDeviceBean.toString());
            cursor.close();
        }
        close();
        return virtualidDeviceBean;
    }

    private Cursor getVirtualidCursor(long executeInsert) {
        String queryString = "_id = ?";
        String[] queryParameters = new String[]{String.valueOf(executeInsert)};

        return executeSelect(TableVirtualidDeviceBean.TABLE_NAME, TableVirtualidDeviceBean.getColumnsNames(), queryString, queryParameters, null);
    }

    private Cursor getVirtualidCursor(int key, String value, String[] des) {
        String queryString = TableVirtualidDeviceBean.getColumnsNames()[key] + " = ?";
        String[] queryParameters = new String[]{String.valueOf(value)};
        return executeSelect(TableVirtualidDeviceBean.TABLE_NAME, des, queryString, queryParameters, null);
    }

    //根据network查 网关id
    public synchronized int getDevicePhoneId(String networkId) {
        open();

        Cursor cursor = getVirtualidCursor(3, networkId, new String[]{TableVirtualidDeviceBean.COLUMN_NAME_PHONEID});
        int phoneId = -1;
        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToFirst();
            phoneId = cursor.getInt(cursor.getColumnIndexOrThrow(TableVirtualidDeviceBean.COLUMN_NAME_PHONEID));
            cursor.close();
        }

        close();
        return phoneId;
    }

    //根据虚拟id，得到网关id和设备meshId
    public synchronized VirtualidDeviceBean getPhoneIdAndMeshId(int virtualId) {
        open();

        Cursor cursor = getVirtualidCursor(6
                , virtualId + ""
                , new String[]{TableVirtualidDeviceBean.COLUMN_NAME_PHONEID, TableVirtualidDeviceBean.COLUMN_NAME_MESHID});
        VirtualidDeviceBean deviceBean = null;
        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToFirst();
            deviceBean = TableVirtualidDeviceBean.getVirtualidDeviceFromCursor(cursor);
            cursor.close();
        }

        close();
        return deviceBean;
    }

    //查询所有的数据
    public synchronized List<VirtualidDeviceBean> getAllBooks() {
        open();
        ArrayList<VirtualidDeviceBean> list = new ArrayList<>();

        Cursor cursor = getAllDeviceCursor();
        if (cursor != null) {
            while (cursor.moveToNext()) {   //移动一条条的数据
                VirtualidDeviceBean bookFromCursor = TableVirtualidDeviceBean.getVirtualidDeviceFromCursor(cursor);
                list.add(bookFromCursor);
            }
            cursor.close();
        }

        close();
        return list;
    }

    private Cursor getAllDeviceCursor() {
        return executeSelect(TableVirtualidDeviceBean.TABLE_NAME, null, null, null, null);
    }

    public synchronized boolean deleteDeviceBean(int phoneId) {
        String queryString = "phoneId = ?";
        String[] queryParameters = new String[]{String.valueOf(phoneId)};

        return executeDelete(TableVirtualidDeviceBean.TABLE_NAME, queryString, queryParameters);
    }

    public synchronized boolean removeAll() {
        return executeDelete(TableVirtualidDeviceBean.TABLE_NAME, null, null);
    }

    /**
     * ****************************************业务线--end*********************************************
     */
}
