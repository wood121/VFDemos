package com.example.wood121.viewdemos.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by wood121 on 2017/12/6.
 */

public class ContactAdapter extends CursorAdapter {


    private final Context mContext;
    private final Cursor mCursor;
    /**
     * 联系人显示名称
     */
    private static final int PHONES_DISPLAY_NAME_INDEX = 1;

    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 2;

    /**
     * 头像ID
     */
    private static final int PHONES_PHOTO_ID_INDEX = 3;

    /**
     * 联系人的ID
     */
    private static final int PHONES_CONTACT_ID_INDEX = 4;


    public ContactAdapter(Context context, Cursor c) {
        super(context, c);
        this.mContext = context;
        this.mCursor = c;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
