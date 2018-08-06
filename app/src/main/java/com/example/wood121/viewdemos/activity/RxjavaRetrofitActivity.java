package com.example.wood121.viewdemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.wood121.viewdemos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 封装Rxjava+Retrofit
 */

public class RxjavaRetrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn_kwy)
    Button btnKwy;

//    public final PublishSubject<ActivityLifeCycleEvent> publishSubject = PublishSubject.create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_retrofit);
//        publishSubject.onNext(ActivityLifeCycleEvent.CREATE);
        ButterKnife.bind(this);

    }

    @Override
    protected void onPause() {
//        publishSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_kwy)
    public void onViewClicked() {

    }


}
