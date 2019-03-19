package com.example.wood121.viewdemos.apis.bitmaps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.wood121.viewdemos.util.AppConstant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.core.content.FileProvider;

public class BitmapUtil {

    //创建图片存储file
    public static File createImgFile(String providerPath) {
        // 要保存的文件名
        String time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
        String fileName = "photo_" + time;
        // 创建一个文件夹
        String path = Environment.getExternalStorageDirectory() + providerPath;
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 要保存的图片文件
        return new File(dirFile, fileName + ".jpeg");
    }

    /**
     * 7.0及以上使用的uri是contentProvider content://com.rain.takephotodemo.FileProvider/images/photo_20180824173621.jpg
     * 6.0使用的uri为file:///storage/emulated/0/take_photo/photo_20180824171132.jpg
     *
     * @param context
     * @param file
     * @return 从file中获取uri
     */
    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(
                    context.getApplicationContext(),
                    "com.example.wood121.viewdemos.fileprovider",
                    file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }


    /**
     * @param activity            打开裁剪页面的环境
     * @param uri                 传入的图片的位置
     * @param cropPhotoStorageUri 裁剪处理后图片存储位置  --
     * @param savePath            裁剪后图片的保存路径，如果不想保存的话传null或者“”
     * @param aspectX             裁剪时X轴的比例
     * @param aspectY             裁剪时Y轴的比例
     * @param outX                裁剪图片输出的X轴的长度
     *                            <p>
     *                            如果savePath为null或者“”，在onActivityResult中接收系统默认返回的是裁剪的缩略图，使用data.getExtras().get("data");接收。
     *                            该方法以startActivityForResult的方式启动系统的裁剪工具，requestCode为GET_CROP_IMAGE，（值自己设置）
     *                            <p>
     *                            使用savePath可能好一点，拍照和相册的cropPhotoStorageUri是不一样的
     */
    public static void cropPhoto(Activity activity, Uri uri, Uri cropPhotoStorageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //打开系统自带的裁剪图片的intent
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);

        // 设置裁剪区域的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // 设置裁剪区域的宽度和高度
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);

        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // 图片输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 若为false则表示不返回数据
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropPhotoStorageUri);  //这里存进去的是Uri.fromFile(saveFile)
        Toast.makeText(activity, "剪裁图片", Toast.LENGTH_SHORT).show();

        // 以广播方式刷新系统相册，以便能够在相册中找到刚刚所拍摄和裁剪的照片
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(uri);
        activity.sendBroadcast(intentBc);

        //重要的一步，使用grantUriPermission来给对应的包提升读写指定uri的临时权限。否则即使调用成功，也会保存裁剪照片失败。
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivityForResult(intent, AppConstant.RESULT_CROP_IMAGE); //设置裁剪参数显示图片至ImageVie
    }

    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     *
     * @param videoPath 视频的路径
     * @param width     指定输出视频缩略图的宽度
     * @param height    指定输出视频缩略图的高度度
     * @param kind      参照MediaStore.Images(Video).Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *                  其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind); //調用ThumbnailUtils類的靜態方法createVideoThumbnail獲取視頻的截圖；
        if (bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);//調用ThumbnailUtils類的靜態方法extractThumbnail將原圖片（即上方截取的圖片）轉化為指定大小；
        }
        return bitmap;
    }

}
