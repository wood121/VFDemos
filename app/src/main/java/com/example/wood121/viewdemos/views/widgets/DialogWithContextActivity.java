package com.example.wood121.viewdemos.views.widgets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.wood121.viewdemos.UserApp;
import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * toast,dialog, pop需要传入的context类型
 */
public class DialogWithContextActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        mBtn_toast = findViewById(R.id.btn_toast);
        mBtn_toast.setOnClickListener(this);
        findViewById(R.id.btn_dialog).setOnClickListener(this);
        findViewById(R.id.btn_dialog_app).setOnClickListener(this);
        findViewById(R.id.btn_pop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toast:
                ToastUtil.showToast(UserApp.getAppContext(), "hahaha");
                break;
            case R.id.btn_dialog:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("dialog")
                        .setMessage("dialog")
                        .create();
                dialog.show();
                break;
            case R.id.btn_dialog_app:
                AlertDialog dialog_app = new AlertDialog.Builder(UserApp.getAppContext())
                        .setTitle("dialog_app")
                        .setMessage("dialog_app")
                        .create();
                dialog_app.show();
                break;
            case R.id.btn_pop:
                PopupWindow popupWindow = new PopupWindow();
                popupWindow.showAsDropDown(mBtn_toast);
                break;
        }
    }
}
