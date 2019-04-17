package com.example.wood121.viewdemos.views.widgets_self.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>描述：
 * 仿qq红点删除操作
 * </p>
 * 作者： wood121<br>
 * 日期： 2019/4/11 17:29<br>
 * 版本： v2.0<br>
 */
public class RedPointView extends FrameLayout {
    public RedPointView(@NonNull Context context) {
        super(context);
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
