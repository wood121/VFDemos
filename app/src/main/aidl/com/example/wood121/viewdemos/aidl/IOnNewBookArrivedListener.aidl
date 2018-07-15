// IOnNewBookArrivedListener.aidl
package com.example.wood121.viewdemos.aidl;

// Declare any non-default types here with import statements

import com.example.wood121.viewdemos.aidl.Book;

interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book newBook);
}
