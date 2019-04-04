package com.example.wood121.viewdemos.views.widgets_self;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2019/4/4 15:29<br>
 * 版本： v2.0<br>
 */
public class TextWatcherListener implements TextWatcher {
    public TextWatcherListener() {
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void afterTextChanged(Editable s) {
        this.hasInputContent(s, s != null && s.length() != 0);
    }

    public void hasInputContent(Editable s, boolean hasInputContent) {
    }
}
