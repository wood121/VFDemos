package com.example.wood121.viewdemos.apis.data_storage;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Environment.DIRECTORY_ALARMS;
import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MOVIES;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.DIRECTORY_NOTIFICATIONS;
import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.DIRECTORY_PODCASTS;
import static android.os.Environment.DIRECTORY_RINGTONES;

/**
 * 1.内部存储空间中的应用私有目录
 * getFilesDir(),getCacheDir()
 * 2.外部存储空间中的应用私有目录
 * getExternalFilesDir(),getExternalCacheDir()
 * 3.
 */
public class FileStorageActivity extends AppCompatActivity {

    @BindView(R.id.btn_get_app_ways)
    Button btnGetAppWays;
    @BindView(R.id.btn_get_external_ways)
    Button btnGetExternalWays;
    @BindView(R.id.btn_input)
    Button btnInput;
    @BindView(R.id.btn_output)
    Button btnOutput;
    @BindView(R.id.btn_input_storage)
    Button btn_input_storage;
    @BindView(R.id.btn_output_storage)
    Button btn_output_storage;

    @BindView(R.id.tv_show)
    TextView tv_show;
    @BindView(R.id.et_input)
    EditText et_input;
    private String inputString;
    private String file_data;
    private String state;
    private File file_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);
        ButterKnife.bind(this);

        file_data = "input";
        state = Environment.getExternalStorageState();

        //内部扩展 缓存目录
        String absolutePath = getExternalCacheDir().getAbsolutePath();
        File dir = new File(absolutePath + "/lzl");
        if (!dir.exists()) {
            dir.mkdir();
        }
        file_storage = new File(dir, "wood.txt");
        //可以在小米手机文件管理--搜索com.example.wood121--cache--lzl--wood.txt
        Log.e("url", "file_storage: " + file_storage.toString());
    }

    @OnClick({R.id.btn_get_app_ways, R.id.btn_get_external_ways, R.id.btn_input, R.id.btn_output, R.id.btn_output_storage, R.id.btn_input_storage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_app_ways:
                getInputWays();
                break;
            case R.id.btn_get_external_ways:
                getEnviromentStorage();
                break;
            case R.id.btn_input:
                input();
                break;
            case R.id.btn_output:
                outPut();
                break;
            case R.id.btn_input_storage:
                inputStorage();
                break;
            case R.id.btn_output_storage:
                outputStorage();
                break;
        }
    }

    private void outputStorage() {
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            try {
                FileInputStream fis = new FileInputStream(file_storage);
                int len;
                byte[] buf = new byte[1024];
                StringBuffer sb = new StringBuffer();
                while ((len = fis.read(buf)) != -1) {
                    sb.append(new String(buf, 0, len));
                }
                tv_show.setText(sb.toString());
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inputStorage() {
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            inputString = et_input.getText().toString();
            if (!TextUtils.isEmpty(inputString)) {
                try {
                    FileOutputStream fos = new FileOutputStream(file_storage);
                    fos.write(inputString.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ToastUtil.showToast(this, "存入storage成功");
            }
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {

        } else {

        }
    }

    private void outPut() {
        try {
            FileInputStream fis = openFileInput(file_data);
            int len;
            byte[] buf = new byte[2014];
            StringBuffer sb = new StringBuffer();
            while ((len = fis.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            tv_show.setText("default: " + sb.toString());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void input() {
        inputString = et_input.getText().toString();
        if (!TextUtils.isEmpty(inputString)) {
            try {
                FileOutputStream fos = openFileOutput(file_data, Context.MODE_PRIVATE);
                fos.write(inputString.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ToastUtil.showToast(this, "存储成功");
        }
    }

    /**
     * app中常见的存储路径
     * 使用应用私有目录保存应用相关数据，使用公共目录保存应用无关数据（共享数据）。
     */
    private void getInputWays() {
        //内部存储空间中的应用私有目录
        String fileDir = getFilesDir().toString();
        Log.e("url", "fileDir: " + fileDir);
    
        String cacheDir = getCacheDir().toString();
        Log.e("url", "cacheDir: " + cacheDir);

        //外部存储空间中的应用私有目录
        String externalFileDir = getExternalFilesDir(null).toString();
        Log.e("url", "externalFileDir: " + externalFileDir);

        String externaleCacheDir = getExternalCacheDir().toString();
        Log.e("url", "externaleCacheDir: " + externaleCacheDir);

        //该程序的安装包路径
        String packageResourcePath = getPackageResourcePath();
        Log.e("url", "packageResourcePath: " + packageResourcePath);

        //外部存储空间中的公共目录
        String directory_alarms = Environment.getExternalStoragePublicDirectory(DIRECTORY_ALARMS).getAbsolutePath();
        Log.e("url", "directory_alarms : " + directory_alarms);

        String directory_dcim = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).getAbsolutePath();
        Log.e("url", "directory_dcim : " + directory_dcim);

        String directory_downloads = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getAbsolutePath();
        Log.e("url", "directory_downloads: " + directory_downloads);

        String directory_movies = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).getAbsolutePath();
        Log.e("url", "directory_movies: " + directory_movies);

        String directory_music = Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).getAbsolutePath();
        Log.e("url", "directory_music: " + directory_music);

        String directory_notifications = Environment.getExternalStoragePublicDirectory(DIRECTORY_NOTIFICATIONS).getAbsolutePath();
        Log.e("url", "directory_notifications: " + directory_notifications);

        String directory_pictures = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath();
        Log.e("url", "directory_pictures: " + directory_pictures);

        String directory_podcasts = Environment.getExternalStoragePublicDirectory(DIRECTORY_PODCASTS).getAbsolutePath();
        Log.e("url", "directory_podcasts: " + directory_podcasts);

        String directory_ringtones = Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES).getAbsolutePath();
        Log.e("url", "directory_ringtones: " + directory_ringtones);

        //外部存储空间中的其它目录
        String getExternalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        File music = new File(getExternalStorageDirectory, "music");
        if (!music.exists()) {
            Log.e("url", "不存在");
        } else {
            Log.e("url", "存在");
        }
        Log.e("url", "getExternalStorageDirectory: " + getExternalStorageDirectory);
    }

    /**
     * 外部存储空间
     */
    private void getEnviromentStorage() {
        String rootDir = Environment.getRootDirectory().toString();
        Log.e("url", "rootDir: " + rootDir);

        String dataDir = Environment.getDataDirectory().toString();
        Log.e("url", "dataDir: " + dataDir);

        String downLoadDir = Environment.getDownloadCacheDirectory().toString();
        Log.e("url", "downLoadDir: " + downLoadDir);

        String externaleStorageDir = Environment.getExternalStorageDirectory().toString();
        Log.e("url", "externaleStorageDir: " + externaleStorageDir);

        String getExternalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).toString();
        Log.e("url", "getExternalStoragePublicDirectory: " + getExternalStoragePublicDirectory);

        String externalStorageState = Environment.getExternalStorageState();
        Log.e("url", "externalStorageState: " + externalStorageState);

        boolean emulated = Environment.isExternalStorageEmulated();
        Log.e("url", "emulated: " + emulated);

        boolean externalStorageRemovable = Environment.isExternalStorageRemovable();
        Log.e("url", "externalStorageRemovable: " + externalStorageRemovable);
    }
}
