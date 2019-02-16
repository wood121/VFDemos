package com.example.wood121.viewdemos.apis.database_.sqlite_book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wood121.viewdemos.apis.database_.sqlite_book.tables.TableBook;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 14:49<br>
 * 版本： v2.0<br>
 */
public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "book1.db";

    private static final String TYPE_BLOB = " blob";
    private static final String TYPE_INTEGER = " integer";
    private static final String TYPE_TEXT = " text";
    private static final String TYPE_REAL = " real";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_BOOK =
            "create table " + TableBook.TABLE_NAME + "(" +
                    TableBook._ID + " integer primary key autoincrement," +
                    TableBook.COLUMN_NAME_AUTHOR + TYPE_TEXT + COMMA_SEP +
                    TableBook.COLUMN_NAME_PRICE + TYPE_INTEGER + COMMA_SEP +
                    TableBook.COLUMN_NAME_PAGES + TYPE_REAL + COMMA_SEP +
                    TableBook.COLUMN_NAME_NAME + TYPE_TEXT + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        Log.e("wood", "DBHelper===Super");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Log.e("wood", "DBHelper===onCreate：" + CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);   //重新创建表格
    }
}
