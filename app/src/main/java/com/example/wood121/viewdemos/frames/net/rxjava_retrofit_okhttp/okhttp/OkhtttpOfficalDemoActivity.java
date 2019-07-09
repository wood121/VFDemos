package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.okhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.RecItemTVAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

/**
 * <p> get,post（string,stream,file,form,multipart）<p/>
 *
 * <p>
 * 1.Http报文
 * 报文首部（服务器端或客户端需处理的请求或响应的内容及属性）、空行(CR+LF)、报文主体(应被发送的数据)
 * 2.Http报文首部
 * http请求报文:>>>
 * *请求行、请求首部字段、通用首部字段、实体首部字段、其它
 * *请求行：方法、url、http版本
 * *请求首部字段：User-Agent,Accept    --Headers
 * *通用首部字段：Cache-Control,Date
 * *实体首部字段：Content-Type,Content-Length,     --RequestBody
 * http响应报文:>>>
 * *状态行、响应首部字段、通用首部字段、实体首部字段、其它
 * *状态行：状态码、http版本
 * *响应首部字段：Server,Vary
 * *通用首部字段：
 * *实体首部字段
 * </p>
 *
 * <p>
 * Request.Builder()：>>>
 * *url，header，method
 * *get|head|post|delete|put|patch等请求方法，请求方法里面传递RequestBody
 * *Headers：实际上就是管理了private final String[] namesAndValues;
 * <p/>
 * <p>
 * RequestBody：>>>
 * **RequestBody create(@Nullable final MediaType contentType, final byte[] content, final int offset, final int byteCount)
 * **具体实现：return new RequestBody() {
 *
 * @Nullable public MediaType contentType() {
 * return contentType;}
 * public long contentLength() {
 * return (long)byteCount;}
 * public void writeTo(BufferedSink sink) throws IOException {
 * sink.write(content, offset, byteCount);}};
 * FormBody:>>>
 * **private static final MediaType CONTENT_TYPE =MediaType.parse("application/x-www-form-urlencoded");
 * **重写contentType,contentLength,writeTo三个方法，
 * MultipartBody:>>>
 * **this.contentType = MediaType.parse(type + "; boundary=" + boundary.utf8());
 * <p/>
 *
 *
 * <p>
 * Response：>>>
 * *关键方法：int code,String message,Headers headers,ResponseBody body
 * </p>
 * <p>
 * ResponseBody:>>>
 * *关键方法contentTyp,contentLength,BufferedSource
 * </p>
 */
public class OkhtttpOfficalDemoActivity extends BaseActivity {

    private ArrayList<String> list;
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    @Override
    protected void initData(Bundle savedInstanceState) {
        list = new ArrayList<>();
        list.add("Synchronous Get");
        list.add("Asynchronous Get");
        list.add("Accessing Headers");
        list.add("Posting a String");
        list.add("Post Streaming");
        list.add("Posting a File");
        list.add("Posting form parameters");
        list.add("Posting a multipart request");
        list.add("Parse a JSON Response With Moshi");
        list.add("Response Caching");
        list.add("Canceling a Call");
        list.add("Timeouts");
        list.add("Per-call Configuration");
        list.add("Handling authentication");
    }

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_okhtttp_offical_demo;
    }

    @Override
    protected void initEvent() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecItemTVAdapter recAdapter = new RecItemTVAdapter();
        recAdapter.setData(list);
        recyclerView.setAdapter(recAdapter);

        recAdapter.setOnItemClickListener(new RecItemTVAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            onclick(postion);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void onclick(int postion) throws IOException {
        switch (postion) {
            case 0:
                synchronousGet();
                break;
            case 1:
                asynchronousGet();
                break;
            case 2:
                AccessingHeaders();
                break;
            case 3:
                PostingaString();
                break;
            case 4:
                PostStreaming();
                break;
            case 5:
                PostingaFile();
                break;
            case 6:
                Postingformparameters();
                break;
            case 7:
                Postingamultipartrequest();
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    private static final String IMGUR_CLIENT_ID = "...";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private void Postingamultipartrequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("iamge", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(multipartBody)
                .build();

        showResponse(client, request);
    }

    /**
     * post请求上传键值对
     *
     * @throws IOException
     */
    private void Postingformparameters() throws IOException {
        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        showResponse(client, request);
    }

    private void PostingaFile() throws IOException {
        OkHttpClient client = new OkHttpClient();

        File file = new File("README.md");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        showResponse(client, request);
    }

    private void PostStreaming() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        showResponse(client, request);
    }

    /**
     * @throws IOException 不要超过1M
     */
    private void PostingaString() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        showResponse(client, request);
    }

    private void showResponse(OkHttpClient client, Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + response);
            }

            Log.e("wood121", response.body().toString());
        }
    }

    /**
     * 添加请求头
     *
     * @throws IOException
     */
    private void AccessingHeaders() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + response);
            }

            Log.e("wood121", "Server: " + response.header("Server"));
            Log.e("wood121", "Date: " + response.header("Date"));
            Log.e("wood121", "Vary: " + response.headers("Vary"));
        }
    }

    /**
     * 异步get请求
     */
    private void asynchronousGet() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        Log.e("wood121", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                    Log.e("wood121", responseBody.string());
                }

                getFile(response);
            }
        });
    }

    private void getFile(Response response) {
        InputStream inputStream = response.body().byteStream();


    }

    /**
     * 同步get请求
     * 打印headers,body，这些都是Response中携带的数据
     * 注：可以查看response具体回调了什么数据
     */
    private void synchronousGet() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code" + response);
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                Log.e("wood121", responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            Log.e("wood121", response.body().string());
        }
    }
}
