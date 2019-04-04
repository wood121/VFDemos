package com.example.wood121.viewdemos.views.widgets_self.danmu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

/**
 * <p>描述：
 * 1.输入框约束：25字（不可输入表情符号）、换行、实时内容字数显示
 * 2.发送按钮的置灰、键盘发送的监听
 * 3.内容保存
 * </p>
 * 作者： wood121<br>
 * 日期： 2019/3/28 16:56<br>
 * 版本： v2.0<br>
 */
public class DanmuContentDialog extends Dialog implements View.OnClickListener {
    private static final int MAX_LENGTH = 25;
    private String mSaveDanmuContent;
    private EditText mEditText;
    private TextView mTv_send;
    private TextView mTvNumber;

    private Pattern mEmojiPattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    private TextWatcherListener mTextWatcherListener = new TextWatcherListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            Matcher emojiMatcher = mEmojiPattern.matcher(s);
            if (emojiMatcher.find()) {
                ToastUtil.showToast(getContext(), "请勿输入表情符号");
            }
            int length = s.length();
            if (length > 25) {
                ToastUtil.showToast(getContext(), "内容长度不可超过25");
            } else {
                mTvNumber.setText((MAX_LENGTH - length) + "");
            }
        }

        @Override
        public void hasInputContent(Editable s, boolean hasInputContent) {
            super.hasInputContent(s, hasInputContent);
            if (hasInputContent) {
                mTv_send.setBackgroundResource(R.drawable.bg_danmu_send_selector);
            } else {
                mTv_send.setBackgroundResource(R.drawable.bg_danmu_send_gray);
            }
        }
    };

    public DanmuContentDialog(@NonNull Context context, String saveDanmuContent) {
        super(context, R.style.DanmuContent_Dialog);
        this.mSaveDanmuContent = saveDanmuContent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_danmu_content);
        Window window = getWindow();
        //弹框外部去掉颜色
        window.setDimAmount(0);
        //保证宽度
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    private void initView() {
        mEditText = findViewById(R.id.edit_text);
        mTvNumber = findViewById(R.id.tv_number);
        mTv_send = findViewById(R.id.send);
    }

    private void initData() {
        if (!TextUtils.isEmpty(mSaveDanmuContent)) {
            mEditText.setText(mSaveDanmuContent);
            mEditText.setSelection(mSaveDanmuContent.length());
        }
    }

    private void initEvent() {
        openSoftInput();
        mEditText.addTextChangedListener(mTextWatcherListener);
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEND:
                    sendDanmu();
                    return true;
            }
            return false;
        });
        mTv_send.setOnClickListener(this);
    }

    public void openSoftInput() {
        //可获取焦点
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        //自动获取焦点
        mEditText.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                sendDanmu();
                break;
        }
    }

    /**
     * 校验标签、发送弹幕、关闭弹框
     */
    private void sendDanmu() {
        String trim = mEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            Matcher emojiMatcher = mEmojiPattern.matcher(trim);
            if (emojiMatcher.find()) {
                ToastUtil.showToast(getContext(), "请勿输入表情符号");
                return;
            }
            mOnSendListener.sendClick(trim);
            Log.e("wood", "sendDanmu:" + trim);
            close();
        } else {
            ToastUtil.showToast(getContext(), "请输入弹幕内容");
        }
    }

    private void close() {
        mEditText.setText("");
        dismiss();
    }

    private onSendListener mOnSendListener;

    public interface onSendListener {
        void sendClick(String trim);
    }

    public void setOnSendListener(onSendListener listener) {
        this.mOnSendListener = listener;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        //点击弹窗外部区域
        if (isOutOfBounds(getContext(), event)) {
            onTouchOutside();
        }
        return super.onTouchEvent(event);
    }

    private void onTouchOutside() {
        String trim = mEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            mSaveDanmuContentListener.saveDanmuContent(trim);
            Log.e("wood", "saveDanmu:" + trim);
        }
    }

    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();//相对弹窗左上角的x坐标
        final int y = (int) event.getY();//相对弹窗左上角的y坐标
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();//最小识别距离
        final View decorView = getWindow().getDecorView();//弹窗的根View
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }

    private onSaveDanmuContentListener mSaveDanmuContentListener;

    public interface onSaveDanmuContentListener {
        void saveDanmuContent(String trim);
    }

    public void setOnSaveDanmuContentListener(onSaveDanmuContentListener listener) {
        this.mSaveDanmuContentListener = listener;
    }
}
