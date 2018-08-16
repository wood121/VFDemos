package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.net.http.api.MyApi;
import com.example.wood121.viewdemos.net.http.entity.FeedArticleListData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 封装Rxjava+Retrofit
 */

public class RxjavaRetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_kwy)
    Button btnKwy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_kwy)
    public void onViewClicked() {
        MyApi.getInstance().getFeedArticleList(3)
                .subscribe(new Consumer<FeedArticleListData>() {
                    @Override
                    public void accept(FeedArticleListData feedArticleListData) throws Exception {
                        
                    }
                });

    }

}
