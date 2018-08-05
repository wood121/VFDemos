package com.example.wood121.viewdemos.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.net.Wood121LoggerInceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Okhttp3Activity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.btn_postform)
    Button btnPostform;
    @BindView(R.id.btn_postString)
    Button btnPostString;
    @BindView(R.id.btn_postFile)
    Button btnPostFile;
    @BindView(R.id.btn_getFile)
    Button btnGetFile;
    @BindView(R.id.btn_postMultpart)
    Button btnPostMultpart;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp3);
        ButterKnife.bind(this);

//        okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.addInterceptor(new Wood121LoggerInceptor()).build();
    }

    @OnClick({R.id.btn_get, R.id.btn_postform, R.id.btn_postString, R.id.btn_postFile, R.id.btn_getFile, R.id.btn_postMultpart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getAsy();
                break;
            case R.id.btn_postform:
                postAsy();
                break;
            case R.id.btn_postString:
                postString();
                break;
            case R.id.btn_postFile:
                postFile();
                break;
            case R.id.btn_getFile:
                getFile();
                break;
            case R.id.btn_postMultpart:
                postMultipart();
                break;
        }
    }

    /**
     * 异步POST请求上传Multipart文件
     */
    private void postMultipart() {
        //上传的图片
        File file = new File(Environment.getExternalStorageDirectory(), "zhuangqilu.png");

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "hahha")
                .addFormDataPart("age", "hehh")
                .addFormDataPart("imge", "zhangliu.png", RequestBody.create(MediaType.parse("imge/png"), file))
                .build();

        Request request = new Request.Builder().url("url").post(multipartBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 异步GET请求下载文件
     */
    private void getFile() {
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("https://www.baidu.com/img/bd_logo1.png").get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //以流的形式读取
                InputStream is = response.body().byteStream();
                //设置下载图片存储路径和名称
                int len = 0;
                File file = new File(Environment.getExternalStorageDirectory(), "baidu.png");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[128];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    Log.e("wood121", "onResponse: " + len);
                }
                fos.flush();
                fos.close();
                is.close();

                //或者直接设置到imageView中
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //子线程刷新UI

                    }
                });
            }
        });

    }

    /**
     * 异步POST请求上传文件
     */
    private void postFile() {
        MediaType mediaType = MediaType.parse("application/octet-stream");
        //上传的图片
        File file = new File(Environment.getExternalStorageDirectory(), "zhuangqilu.png");
        //2.通过RequestBody.create 创建requestBody对象,application/octet-stream 表示文件是任意二进制数据流
        RequestBody requestBody = RequestBody.create(mediaType, file);
        //3.创建Request对象，设置URL地址，将RequestBody作为post方法的参数传入
        Request request = new Request.Builder().url("url").post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 异步POST请求提交字符串
     */
    private void postString() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        //字符串
        String value = "{username:admin;password:admin}";
        RequestBody requestBody = RequestBody.create(mediaType, value);
        Request request = new Request.Builder().url("url").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * post请求提交键值对
     */
    private void postAsy() {
        FormBody formBody = new FormBody.Builder().add("name", "zhangqilu").add("age", "12").build();
        Request request = new Request.Builder().url("url").post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("wood121", response.body().toString());
            }
        });
    }

    /**
     * 异步Get请求
     */
    private void getAsy() {
        Request request = new Request.Builder().url("http://www.baidu.com").method("GET", null).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("wood121", response.body().toString());

                /**
                 * Response返回数值的情况
                 */
                final String string = response.body().string();
                byte[] bytes = response.body().bytes();
                //说明了支持了大文件下载
                InputStream inputStream = response.body().byteStream();

                /**
                 * 注意：这个执行的线程不是ui线程，如果要操作控件，需要使用handler
                 */
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnGet.setText("btn传回的数据：" + string);
                    }
                });
            }
        });
    }
}
