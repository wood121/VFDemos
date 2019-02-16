package com.example.wood121.viewdemos.apis.database_.sqlite_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.SystemClock;
import android.util.Log;

import com.example.wood121.viewdemos.apis.database_.sqlite_book.bean.Book;
import com.example.wood121.viewdemos.apis.database_.sqlite_book.tables.TableBook;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 1.创建 book.db，new DBHelper();
 * 2.mDatabase的创建于关闭
 * 3.增删改查底层方法: excuterInsert,excuteDelete,excuteUpdate,excuteSelect
 * 4.业务线参数传递
 * <p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 19:37<br>
 * 版本： v2.0<br>
 */
public class DBManager {
    private static DBManager mDBManager;
    private final Context mContext;
    private final DBHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    public DBManager(Context applicationContext) {
        this.mContext = applicationContext;
        mDbHelper = new DBHelper(mContext);
    }

    public static DBManager getInstence(Context context) {
        if (mDBManager == null) {
            mDBManager = new DBManager(context.getApplicationContext());
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
     * ****************************************业务线--start*********************************************
     */

    /**
     * insertBook,getVirtualidDeviceBean,getBookCursor，插入后又查出来
     *
     * @param book
     * @return
     */
    public synchronized Book insertBook(Book book) {
        open();

        Book insertBook = null;
        //插入
        long executeInsert = executeInsert(TableBook.TABLE_NAME, TableBook.createContentValues(book));
        //查询
        if (executeInsert > 0) {
            Log.e("wood", "DBManager,插入成功:" + book.toString());
            insertBook = getBook(executeInsert);
        }

        close();
        return insertBook;
    }

    /**
     * 已修改：  cursor.moveToFirst();
     *
     * @param executeInsert
     * @return
     */
    private Book getBook(long executeInsert) {
        open();
        Book bookFromCursor = null;
        Cursor cursor = getBookCursor(executeInsert);   //插入的那一行，获取油表对象
        Log.e("wood", "DBManager,cursor:" + cursor);
        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToFirst();
            bookFromCursor = TableBook.getBookFromCursor(cursor);
            Log.e("wood", "bookFromCursor:" + bookFromCursor.toString());
            cursor.close();
        }
        close();
        return bookFromCursor;
    }

    private Cursor getBookCursor(long executeInsert) {
        String queryString = "_id = ?";
        String[] queryParameters = new String[]{String.valueOf(executeInsert)};

        return executeSelect(TableBook.TABLE_NAME, TableBook.getColumnsNames(), queryString, queryParameters, null);
    }

    //根据名字查询，是有可能查到重复的数据
    public synchronized List<Book> getBook(String name) {
        open();
        ArrayList<Book> list = new ArrayList<>();

        Cursor cursor = getBookCursor(name);
        if (cursor != null) {
            while (cursor.moveToNext()) {   //移动一条条的数据
                Book bookFromCursor = TableBook.getBookFromCursor(cursor);
                list.add(bookFromCursor);
            }
            cursor.close();
        }

        close();
        return list;
    }

    private Cursor getBookCursor(String name) {
        String queryString = "name = ?";
        String[] queryParameters = new String[]{String.valueOf(name)};
        return executeSelect(TableBook.TABLE_NAME, TableBook.getColumnsNames(), queryString, queryParameters, null);
    }

    public synchronized List<Book> getAllBooks() {
        open();
        ArrayList<Book> list = new ArrayList<>();

        Cursor cursor = getAllBooksCursor();
        if (cursor != null) {
            while (cursor.moveToNext()) {   //移动一条条的数据
                Book bookFromCursor = TableBook.getBookFromCursor(cursor);
                list.add(bookFromCursor);
            }
            cursor.close();
        }

        close();
        return list;
    }

    private Cursor getAllBooksCursor() {
        return executeSelect(TableBook.TABLE_NAME, null, null, null, null);
    }

    public synchronized boolean deleteBook(String name) {
        String queryString = "name = ?";
        String[] queryParameters = new String[]{String.valueOf(name)};

        return executeDelete(TableBook.TABLE_NAME, queryString, queryParameters);
    }

    public synchronized boolean removeAll() {
        return executeDelete(TableBook.TABLE_NAME, null, null);
    }


    /**
     * ****************************************业务线--end*********************************************
     */


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
     * 开启事物的优化
     *
     * @param list
     */
    public synchronized void executeTransactionInsert(ArrayList<Book> list) {
        long start = SystemClock.currentThreadTimeMillis();
        if (!mDatabase.isOpen()) open();
        mDatabase.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            mDatabase.insert(TableBook.TABLE_NAME, null, TableBook.createContentValues(book));
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        mDatabase.close();
        Log.e("wood", "insert traceaction use time " + (SystemClock.currentThreadTimeMillis() - start));
    }

    /**
     * 1000-63,2000-65,5000-125,10k-202,20k-330
     * 开启事务与SqliteStatement的优化
     *
     * @param list
     */
    public synchronized void executeTranStateInsert(ArrayList<Book> list) {
        long start = SystemClock.currentThreadTimeMillis();
        String sql = "insert into " + TableBook.TABLE_NAME
                + " (" + TableBook.COLUMN_NAME_AUTHOR + ","
                + TableBook.COLUMN_NAME_PAGES + ","
                + TableBook.COLUMN_NAME_PRICE + ","
                + TableBook.COLUMN_NAME_NAME + ")"
                + " values (?,?,?,?)";
        if (!mDatabase.isOpen()) open();
        SQLiteStatement sqLiteStatement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, book.getAuthor());
            sqLiteStatement.bindString(2, book.getPages() + "");
            sqLiteStatement.bindDouble(3, book.getPrice());
            sqLiteStatement.bindString(4, book.getName());
            sqLiteStatement.executeInsert();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
        mDatabase.close();
        Log.e("wood", "insert traceaction use time " + (SystemClock.currentThreadTimeMillis() - start));
    }
    /**
     * ************************************增删改查--end***************************************
     */
}
