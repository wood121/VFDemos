// IBookManager.aidl
package com.example.wood121.viewdemos.aidl;

// Declare any non-default types here with import statements

import com.example.wood121.viewdemos.aidl.Book;
import com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
