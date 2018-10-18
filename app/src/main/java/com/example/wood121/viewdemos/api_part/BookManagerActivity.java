package com.example.wood121.viewdemos.api_part;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.api_part.aidl.Book;
import com.example.wood121.viewdemos.api_part.aidl.IBookManager;
import com.example.wood121.viewdemos.api_part.aidl.IOnNewBookArrivedListener;
import com.example.wood121.viewdemos.api_part.service.BookManagerService;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {


    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.e(TAG, "receive new book :" + msg.obj);
                    break;
            }
            return false;
        }
    });

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //客户端获取服务端binder对象
//            IBookManagerSelf bookManager = IBookManager.Stub.asInterface(service);
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager = bookManager;

                //客户端获取服务端书籍数据
                List<Book> bookList = bookManager.getBookList();
                Log.e(TAG, "query book list,list type:" + bookList.getClass().getCanonicalName());
                Log.e(TAG, "query book list:" + bookList.toString());

                //客户端向服务端添加一本书
                Book newBook = new Book(3, "Android开发艺术探索");
                bookManager.addBook(newBook);
                Log.e(TAG, "add book:" + newBook);
                List<Book> newList = bookManager.getBookList();
                Log.e(TAG, "query book list:" + newList.toString());

                //注册有新书就能告知的监听
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //如果服务端进程意外停止了导致Binder意外死亡，可以在这里重连远程服务
            mRemoteBookManager = null;
            Log.e(TAG, "binder died");
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        //绑定远程服务
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            Log.e(TAG, "unregister listener:" + mOnNewBookArrivedListener);
            try {
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


        unbindService(mConnection);
        super.onDestroy();
    }
}
