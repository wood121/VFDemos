// IOnNewBookArrivedListener.aidl
package com.example.wood121.viewdemos.api_part.aidl;

// Declare any non-default types here with import statements

import com.example.wood121.viewdemos.api_part.aidl.Book;

interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book newBook);
}
