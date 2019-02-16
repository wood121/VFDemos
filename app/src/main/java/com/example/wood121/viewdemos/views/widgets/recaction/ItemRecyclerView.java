package com.example.wood121.viewdemos.views.widgets.recaction;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/24 11:09<br>
 * 版本： v2.0<br>
 */
public class ItemRecyclerView extends RecyclerView {
    public ItemRecyclerView(Context context) {
        super(context);
    }

    public ItemRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                requestDisallowInterceptTouchEvent(false);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//        return super.onTouchEvent(e);
//    }
}
