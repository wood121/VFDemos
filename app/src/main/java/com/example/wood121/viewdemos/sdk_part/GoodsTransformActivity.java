package com.example.wood121.viewdemos.sdk_part;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.QRCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 二维码生成
 */
public class GoodsTransformActivity extends AppCompatActivity {


    @BindView(R.id.qrcode_et)
    EditText qrcodeEt;
    @BindView(R.id.qrcode_bt)
    Button qrcodeBt;
    @BindView(R.id.qrcode_iv)
    ImageView qrcodeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_transform);
        ButterKnife.bind(this);

        ViewPager viewPager = new ViewPager(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.qrcode_bt)
    public void onViewClicked() {
        Bitmap bitmap = QRCode.createQRCodeWithLogo(qrcodeEt.getText().toString(), 500,
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        qrcodeIv.setImageBitmap(bitmap);
    }
}
