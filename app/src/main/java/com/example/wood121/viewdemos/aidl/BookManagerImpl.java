package com.example.wood121.viewdemos.aidl;

import android.os.Binder;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by wood121 on 2018/7/13.
 */

public class BookManagerImpl extends Binder implements IBookManagerSelf {

    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.example.wood121.viewdemos.aidl.IBookManagerSelf interface,
     * generating a proxy if needed.
     */
    public static IBookManagerSelf asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IBookManagerSelf))) {
            return ((IBookManagerSelf) iin);
        }
        return new BookManagerImpl.Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<com.example.wood121.viewdemos.aidl.Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                com.example.wood121.viewdemos.aidl.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.example.wood121.viewdemos.aidl.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_registerListener: {
                data.enforceInterface(DESCRIPTOR);
                com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener _arg0;
                _arg0 = com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener.Stub.asInterface(data.readStrongBinder());
                this.registerListener(_arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_unregisterListener: {
                data.enforceInterface(DESCRIPTOR);
                com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener _arg0;
                _arg0 = com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener.Stub.asInterface(data.readStrongBinder());
                this.unregisterListener(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }

    @Override
    public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

    }

    private static class Proxy implements IBookManagerSelf {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public java.util.List<com.example.wood121.viewdemos.aidl.Book> getBookList() throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<com.example.wood121.viewdemos.aidl.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(BookManagerImpl.TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(com.example.wood121.viewdemos.aidl.Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(com.example.wood121.viewdemos.aidl.Book book) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(BookManagerImpl.TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public void registerListener(com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(BookManagerImpl.TRANSACTION_registerListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }

        @Override
        public void unregisterListener(com.example.wood121.viewdemos.aidl.IOnNewBookArrivedListener listener) throws android.os.RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                _data.writeStrongBinder((((listener != null)) ? (listener.asBinder()) : (null)));
                mRemote.transact(BookManagerImpl.TRANSACTION_unregisterListener, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }

}
