package com.example.wood121.viewdemos.apis.data_storage;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileStorageActivity extends AppCompatActivity {

    @BindView(R.id.btn_get_ways)
    Button btnGetWays;
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
        File externalCacheDir = getExternalCacheDir();
        File dir = new File(externalCacheDir.toString() + "/lzl");
        if (!dir.exists()) {
            dir.mkdir();
        }

        file_storage = new File(dir, "wood.txt");
        Log.e("url", "file_storage: " + file_storage.toString());
        //内部扩展 文件目录
//        file_storage = new File(getExternalFilesDir(null), "wood.txt");

        //外部 公共存储目录
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        file_storage = new File(externalStorageDirectory, "lzllmh.txt");

//        File publicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//        file_storage = new File(publicDirectory, "lzllmh.txt");
    }

    @OnClick({R.id.btn_get_ways, R.id.btn_input, R.id.btn_output, R.id.btn_output_storage, R.id.btn_input_storage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_ways:
                getInputWays();
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

    private void getInputWays() {
        /**
         * 内部存储路径
         */
        //应用程序私有存储空间
        String fileDir = getFilesDir().toString();
        Log.e("url", "fileDir: " + fileDir);

        String cacheDir = getCacheDir().toString();
        Log.e("url", "cacheDir: " + cacheDir);

        //应用程序扩展存储空间
        String externalFileDir = getExternalFilesDir(null).toString();
        Log.e("url", "externalFileDir: " + externalFileDir);

        String externaleCacheDir = getExternalCacheDir().toString();
        Log.e("url", "externaleCacheDir: " + externaleCacheDir);

        //该程序的安装包路径
        String packageResourcePath = getPackageResourcePath();
        Log.e("url", "packageResourcePath: " + packageResourcePath);

        //默认数据库路径
//        getDatabasePath()

        /**
         * 外部存储数据
         */
        String rootDir = Environment.getRootDirectory().toString();
        Log.e("url", "rootDir: " + rootDir);

        String dataDir = Environment.getDataDirectory().toString();
        Log.e("url", "dataDir: " + dataDir);

        String downLoadDir = Environment.getDownloadCacheDirectory().toString();
        Log.e("url", "downLoadDir: " + downLoadDir);

        String externaleStorageDir = Environment.getExternalStorageDirectory().toString();
        Log.e("url", "externaleStorageDir: " + externaleStorageDir);

        String getExternalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        Log.e("url", "getExternalStoragePublicDirectory: " + getExternalStoragePublicDirectory);

        String externalStorageState = Environment.getExternalStorageState().toString();
        Log.e("url", "externalStorageState: " + externalStorageState);

        boolean emulated = Environment.isExternalStorageEmulated();
        Log.e("url", "emulated: " + emulated);

        boolean externalStorageRemovable = Environment.isExternalStorageRemovable();
        Log.e("url", "externalStorageRemovable: " + externalStorageRemovable);
    }
}
