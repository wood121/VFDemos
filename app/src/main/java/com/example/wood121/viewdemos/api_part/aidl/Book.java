package com.example.wood121.viewdemos.api_part.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wood121 on 2018/7/12.
 */

public class Book implements Parcelable {

    public int bookId;
    public String bookName;

    public Book(int i, String mBookName) {
        this.bookId = i;
        this.bookName = mBookName;
    }

    public Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
