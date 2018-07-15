package com.example.wood121.viewdemos.aidl;

import android.os.IBinder;
import android.os.IInterface;

import java.util.List;

/**
 * Created by wood121 on 2018/7/13.
 */

public interface IBookManagerSelf extends IInterface {

    static final java.lang.String DESCRIPTOR = "com.example.wood121.viewdemos.aidl.IBookManagerSelf";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;
    static final int TRANSACTION_registerListener = (IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_unregisterListener = (IBinder.FIRST_CALL_TRANSACTION + 3);

    public List<Book> getBookList() throws android.os.RemoteException;

    public void addBook(Book book) throws android.os.RemoteException;

    public void registerListener(IOnNewBookArrivedListener listener) throws android.os.RemoteException;

    public void unregisterListener(IOnNewBookArrivedListener listener) throws android.os.RemoteException;

}


