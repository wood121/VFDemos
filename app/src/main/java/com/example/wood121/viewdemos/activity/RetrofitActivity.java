package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.bean.Reception;
import com.example.wood121.viewdemos.bean.Reception2;
import com.example.wood121.viewdemos.interfaces.MyHttpService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    Button btnGet;
    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    private void postMethod() {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        //准备好请求的MyHttpService，在此获取APIService并传入对象
        MyHttpService httpService = retrofit.create(MyHttpService.class);

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
        MyHttpService myHttpService = retrofit.create(MyHttpService.class);
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
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getMethod();
                break;
            case R.id.btn_post:
                postMethod();
                break;
        }
    }
}
