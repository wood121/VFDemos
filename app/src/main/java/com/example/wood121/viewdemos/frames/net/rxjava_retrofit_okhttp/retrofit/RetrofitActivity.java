package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.base.BaseActivity;
import com.example.wood121.viewdemos.frames.bean.Reception;
import com.example.wood121.viewdemos.frames.bean.Reception2;
import com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.RecItemTVAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends BaseActivity {
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

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
        return R.layout.activity_retrofit;
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
                        onclick(postion);
                    }
                }).start();
            }
        });
    }

    private void onclick(int postion) {
        switch (postion) {
            case 0:
                asGet();
                break;
        }
    }

    /**
     * HTTP 的Api，注解方式标明。
     * Retrofit对象，获取ApiService，获取Call
     * Call发起请求
     */
    private void asGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory()
//                .client()
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Repo>> call = retrofitService.listRepos("octcat");

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.e("wood121", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable e) {
                Log.e("wood121", e.toString());
            }
        });
    }

    private void postMethod() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                return null;
            }
        }).build();

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //准备好请求的MyHttpService，在此获取APIService并传入对象
        RetrofitService httpService = retrofit.create(RetrofitService.class);

        httpService.postCall("i love you")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Reception2>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Reception2 reception2) {
                        Log.e("url", "reception:" + reception2.toString());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getMethod() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建 网络请求接口 的实例
        RetrofitService myHttpService = retrofit.create(RetrofitService.class);

        Call<Reception> call = myHttpService.getCall();
        //发起请求、得到请求回调
        call.enqueue(new Callback<Reception>() {
            @Override
            public void onResponse(Call<Reception> call, Response<Reception> response) {
                Reception reception = response.body();

                Log.e("url", "response:" + response.toString());
                Log.e("url", "reception:" + reception.toString());
            }

            @Override
            public void onFailure(Call<Reception> call, Throwable t) {

            }
        });

//        Proxy.newProxyInstance()
    }


}
