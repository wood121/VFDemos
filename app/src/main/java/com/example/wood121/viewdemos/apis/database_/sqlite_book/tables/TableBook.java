package com.example.wood121.viewdemos.apis.database_.sqlite_book.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.wood121.viewdemos.apis.database_.sqlite_book.bean.Book;

/**
 * <p>描述：(表名、获取列名、对象转存储字段、字段转对象)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 15:03<br>
 * 版本： v2.0<br>
 */
public class TableBook implements BaseColumns {
    /**
     * "id integer primary key autoincrement,"
     * "author text,"
     * "price real,"
     * "pages integer,"
     * "name text)";
     */
    public static final String TABLE_NAME = "Book";

    public static final String COLUMN_NAME_AUTHOR = "author";
    public static final String COLUMN_NAME_PRICE = "price";
    public static final String COLUMN_NAME_PAGES = "pages";
    public static final String COLUMN_NAME_NAME = "name";

    public static String[] getColumnsNames() {
        String[] columns = {
                TableBook._ID,
                TableBook.COLUMN_NAME_AUTHOR,
                TableBook.COLUMN_NAME_PRICE,
                TableBook.COLUMN_NAME_PAGES,
                TableBook.COLUMN_NAME_NAME
        };
        return columns;
    }

    /**
     * 进行插入操作的时候需要
     *
     * @param book
     * @return
     */
    public static ContentValues createContentValues(Book book) {
        // _ID not included since it is Auto-incremental
        ContentValues values = new ContentValues();
        values.put(TableBook.COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(TableBook.COLUMN_NAME_PRICE, book.getPrice());
        values.put(TableBook.COLUMN_NAME_PAGES, book.getPages());
        values.put(TableBook.COLUMN_NAME_NAME, book.getName());
        return values;
    }

    /**
     * 查询动作结束的时候使用
     *
     * @param cursor
     * @return
     */
    public static Book getBookFromCursor(Cursor cursor) {
        Book book = new Book();

        String author = cursor.getString(cursor.getColumnIndexOrThrow(TableBook.COLUMN_NAME_AUTHOR));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(TableBook.COLUMN_NAME_PRICE));
        int pages = cursor.getInt(cursor.getColumnIndexOrThrow(TableBook.COLUMN_NAME_PAGES));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(TableBook.COLUMN_NAME_NAME));

        book.setAuthor(author);
        book.setName(name);
        book.setPages(pages);
        book.setPrice(price);
        return book;
    }
}
