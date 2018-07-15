package com.example.wood121.viewdemos.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;

import com.example.wood121.viewdemos.aidl.Book;
import com.example.wood121.viewdemos.aidl.IBookManager;
import com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    //需要进行处理的对象
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    //为了能在多进程情况下Activity退出时解除注册，RemoteCallbackList是系统专门提供的用于删除跨进程listener的接口
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {

        /**
         * 书籍的书籍
         * @return
         * @throws RemoteException
         */
        @Override
        public List<Book> getBookList() throws RemoteException {
            //假设服务端的是耗时操作，则需要客户端在请求的时候使用子线程访问
            SystemClock.sleep(3000);
            return mBookList;
        }

        /**
         * 添加书籍
         * @param book
         * @throws RemoteException
         */
        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        /**
         * 添加监听，新书到了
         * @param listener
         * @throws RemoteException
         */
        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener);
//            } else {
//                Log.e(TAG, "already exits");
//            }
//            Log.e(TAG, "registerListener,size:" + mListenerList.size());

            mListenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener);
//                Log.e(TAG, "unregister listener ok");
//            } else {
//                Log.e(TAG, "not found");
//            }
//            Log.e(TAG, "unRegisterListener,size:" + mListenerList.size());

            mListenerList.unregister(listener);
        }
    };

    public BookManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceWorker()).start();
    }

    /**
     * 满足需求：用户不想时不时去查询图书列表，要起图书馆"有新书时能不能把书的信息告诉用户"
     *
     * @param book
     * @throws RemoteException
     */
    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
//        Log.e(TAG, "onNewBookArrived,notify listeners:" + mListenerList.size());
//
//        for (int i = 0; i < mListenerList.size(); i++) {
//            IOnNewBookArrivedListener listener = mListenerList.get(i);
//            Log.e(TAG, "onNewBookArrived,notify listeners:" + listener);
//            listener.onNewBookArrived(book);
//        }

        int N = mListenerList.beginBroadcast();
        for (int j = 0; j < N; j++) {
            IOnNewBookArrivedListener broadcastItem = mListenerList.getBroadcastItem(j);
            if (broadcastItem != null) {
                broadcastItem.onNewBookArrived(book);
            }
        }
        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book book = new Book(bookId, "newBook#" + bookId);
                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed.set(true);
        super.onDestroy();
    }
}
