package com.example.wood121.viewdemos.apis.bitmaps;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.util.AppConstant;

import java.io.File;

import androidx.annotation.RequiresApi;

/**
 * 1.打开系统相机拍照、裁剪，图片处理技术
 * 1）打开相机intent、设置存储file、获取存储的uri，是否设置uri影响拍照返回的data
 * 2）对图片进行裁剪
 * 3）为防止OOM压缩图片，图片被旋转的问题
 * <p>
 * 2.打开系统相册？
 */
public class TakePhotoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mIvIcon;
    private Uri mImgUri; // 拍照时返回的uri
    private Uri mCutUri;// 图片裁剪时返回的uri

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void initEvent() {
        mIvIcon = findViewById(R.id.iv_icon);
        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_gallery).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                goToCamera();
                break;
            case R.id.btn_gallery:
                gotoGallery();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void goToCamera() {
        //要准备打开系统相机
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置存储地址
        File imgFile = BitmapUtil.createImgFile("/DCIM/");
        Log.e("wood", imgFile.getAbsolutePath());
        mImgUri = BitmapUtil.getUriForFile(this, imgFile);
        Log.e("wood", mImgUri.toString());
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImgUri);
        //添加相关权限
        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        //打开拍照页面
        startActivityForResult(cameraIntent, AppConstant.RESULT_CODE_CAMERA);
    }

    private void gotoGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, AppConstant.RESULT_CODE_GALLERY);
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data        如果指定了存储的路径的uri，那么返回的data就为null；
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppConstant.RESULT_CODE_CAMERA:    //拍照并进行裁剪
//                    mIvIcon.setImageURI(mImgUri);
                    BitmapUtil.cropPhoto(this, mImgUri, cropPhotoStorageUri(mImgUri, true));
                    break;
                case AppConstant.RESULT_CODE_GALLERY:   //
                    Uri dataUri = data.getData();
                    Log.e("wood", "onActivityResult: imgUri:RESULT_CODE_GALLERY:" + dataUri.toString());
                    //content://com.miui.gallery.open/raw/%2Fstorage%2Femulated%2F0%2FDCIM%2FCamera%2FIMG_20190214_122030.jpg
                    BitmapUtil.cropPhoto(this, dataUri, cropPhotoStorageUri(dataUri, false));
                    break;
                case AppConstant.RESULT_CROP_IMAGE:
                    Log.e("wood", "onActivityResult: imgUri:REQUEST_CROP:" + mCutUri.toString());
                    mIvIcon.setImageURI(mCutUri);
                    break;
            }
        }
    }

    private Uri cropPhotoStorageUri(Uri imgUri, boolean fromCapture) {
        // 指定裁剪完成以后的图片所保存的位置,pic info显示有延时
        if (fromCapture) {
            // 如果是使用拍照，那么原先的uri和最终目标的uri一致
            mCutUri = imgUri;
        } else { // 从相册中选择，那么裁剪的图片保存在take_photo中
//            File cutFile = BitmapUtil.createImgFile("/Pictures/");
//            mCutUri = BitmapUtil.getUriForFile(this, cutFile);

            //从系统相册选择的需要这样处理，否则无法保存
            File cutFile = BitmapUtil.createImgFile("/Pictures/");
            mCutUri = Uri.parse("file://" + cutFile.getAbsolutePath());
        }
        return mCutUri;
    }
}
