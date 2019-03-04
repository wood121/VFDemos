package com.example.wood121.viewdemos.sdk_thirdparty.login_share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;

/**
 * 第三方登录：微信、qq、微博
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public ArrayList<SnsPlatform> platforms = new ArrayList<>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN};
    private ImageView QQ, WeiXin, Sina;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initPlatforms();
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPageViewListener() {
        QQ = findViewById(R.id.iv_qq_login);
        WeiXin = findViewById(R.id.iv_weixin_login);
        Sina = findViewById(R.id.iv_sina_login);
        QQ.setOnClickListener(this);
        WeiXin.setOnClickListener(this);
        Sina.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_qq_login:
                UmEnter(0);
                break;
            case R.id.iv_weixin_login:
                UmEnter(2);
                break;
            case R.id.iv_sina_login:
                UmEnter(1);
                break;
        }
    }

    private void UmEnter(int pos) {
        final boolean isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(pos).mPlatform);
        if (isauth) {
            UMShareAPI.get(this).deleteOauth(this, platforms.get(pos).mPlatform, authListener);
        } else {
            UMShareAPI.get(this).doOauthVerify(this, platforms.get(pos).mPlatform, authListener);
        }
    }

    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }

    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("platform", platform);
            startActivity(intent);
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        UMShareAPI.get(this).release();
    }
}
