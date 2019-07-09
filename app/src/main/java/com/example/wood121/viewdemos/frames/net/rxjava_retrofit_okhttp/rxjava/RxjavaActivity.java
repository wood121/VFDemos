package com.example.wood121.viewdemos.frames.net.rxjava_retrofit_okhttp.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wood121.viewdemos.R;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Rxjva-demo
 */
public class RxjavaActivity extends AppCompatActivity {
    @BindView(R.id.btn_init)
    Button btnInit;
    @BindView(R.id.btn_printarray)
    Button btnPrintarray;
    @BindView(R.id.btn_getpictures)
    Button btnGetpictures;
    @BindView(R.id.btn_complete)
    Button btnComplete;
    @BindView(R.id.btn_Maybe)
    Button btnMaybe;
    @BindView(R.id.btn_Rxbus)
    Button btnRxbus;
    @BindView(R.id.btn_threads)
    Button btnThreads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    /**
     * lamada表达式
     */
    private void demo1() {
        Observable.just("string").subscribe();
    }

    @OnClick({R.id.btn_init, R.id.btn_printarray, R.id.btn_getpictures, R.id.btn_complete, R.id.btn_Maybe, R.id.btn_Rxbus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_init:
                flowableRxjava();
                break;
            case R.id.btn_printarray:
                observableRxjava();
                break;
            case R.id.btn_getpictures:
                singleRxjava();
                break;
            case R.id.btn_complete:
                completeRxjava();
                break;
            case R.id.btn_Maybe:
                maybeRxjava();
                break;
            case R.id.btn_Rxbus:
                rxbusRxjava();
                break;
            case R.id.btn_threads:
                threadsRxjava();
                break;
        }
    }

    /**
     * 线程切换
     */
    private void threadsRxjava() {

    }

    private void rxbusRxjava() {

    }

    /**
     * 带有背压的概念
     */
    private void flowableRxjava() {
        //创建观察者
        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        //被观察者
        Flowable<String> flowable = Flowable.create(e -> {
            //订阅观察者时候的操作


        }, BackpressureStrategy.BUFFER);

        //被观察者可以订阅多个观察者
        flowable.subscribe(subscriber);

        /**
         * *******************************Actions************************************
         * subscribe有多个重构方法，4个Consumer的，一个订阅FlowableSubscriber的,一个订阅Subscriber的
         * consumer有四个重载的方法：subscribe(onNext,onError,onComplete,onSubscribe)
         */
        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //相当于onNext
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //相当于onError
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //相当于onComplete
            }
        }, new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                //相当于onSubscribe
            }
        });

    }

    /**
     * 1.个体对象创建
     * 2.链式调用
     * 3.操作符：just、
     * 4.线程切换
     */
    @SuppressLint("CheckResult")
    private void observableRxjava() {
        /**
         分离开，一般调用操作
         */
        //被观察者
//        Observable<String> observable = Observable.create(e -> {
//            e.onNext("test1");
//            e.onNext("test2");
//            e.onComplete();
//            e.onError(new Throwable("hhahha"));
//        });
//        //观察者
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                //订阅时候的操作，无需request
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("wood121", "普通调用:" + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//        //subscribe有5个重构方法,Observer形式、另外就是Consumer相关的4个重构方法（实质是一个）
//        observable.subscribe(observer);

        /**
         * 链式调用
         */
        Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("test1");
            e.onNext("test2");
            e.onComplete();
        }).subscribe(s -> Log.e("wood121", "链式调用:" + s), throwable -> {

        }, () -> {

        }, disposable -> {

        });

        /**
         * * * * * * * * * 创建操作符* * * * * * * * * * * * * * * * * * * * * * * * *
         */
//        Observable.just("j1", "j2").subscribe(s -> Log.e("wood121", "just操作符:" + s));
//        //fromArray，里面方的空的时候，调用的empty
//        Observable.fromArray(1, 2, 3, 4, 5).subscribe(integer -> {
//            Log.e("wood121", "fromArray操作符:" + integer);
//        });

//        Observable.interval(2, 1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.e("wood121", String.valueOf(aLong));
//            }
//        });


        /**
         * * * * * * * * * 过滤操作符* * * * * * * * * * * * * * * * * * * * * * * * *
         */
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list.add(i);
//        }
//        Observable.fromIterable(list)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer % 2 == 0;
//                    }
//                }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e("wood121", String.valueOf(integer));
//            }
//        });
//
//        Observable.just(1, 2, 3, 4, 5)
//                .takeLast(3)
//                .subscribe(integer -> Log.e("wood121", "过滤操作服take:" + integer));
//

        /**
         * * * * * * * * * 合并聚合操作符* * * * * * * * * * * * * * * * * * * * * * * * *
         */
//        Observable.just(4, 5, 6)
//                .startWithArray(1, 2, 3)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.e("wood121", String.valueOf(integer));
//                    }
//                });

//        Observable.zip(Observable.just(1, 2, 3),
//                Observable.just(4, 5),
//                (int1, int2) -> int1 + int2).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.e("wood121", String.valueOf(integer));
//            }
//        });

        /**
         * * * * * * * * * 条件操作符* * * * * * * * * * * * * * * * * * * * * * * * *
         */
//        Observable.just(1, 2, 3)
//                .all(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer >= 0;
//                    }
//                }).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                Log.e("wood121", aBoolean.toString());
//            }
//        });


        /**
         * * * * * * * * * 变换操作符* * * * * * * * * * * * * * * * * * * * * * * * *
         */
//        Observable.just(1, 2, 3)
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return "int:" + integer;
//                    }
//                }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.e("wood121", s);
//            }
//        });

        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1, "哈哈", new Student.Course("语文")));
        list.add(new Student(2, "呵呵", new Student.Course("数学")));
        list.add(new Student(3, "嘿嘿", new Student.Course("英语")));
//        Observable.fromIterable(list)
//                .flatMap((Function<Student, ObservableSource<Student.Course>>) student -> Observable.just(student.getCourse())).subscribe(course -> {
//            Log.e("wood121", course.getName());
//        });
        Observable.fromIterable(list)
                .flatMap(new Function<Student, ObservableSource<Student.Course>>() {
                    @Override
                    public ObservableSource<Student.Course> apply(Student student) throws Exception {
                        return Observable.just(student.getCourse());
                    }
                }).subscribe(new Consumer<Student.Course>() {
            @Override
            public void accept(Student.Course course) throws Exception {
                Log.e("wood121", course.getName());
            }
        });
    }


    /**
     * 如果你使用一个单一连续事件流，即只有一个onNext事件，接着就触发onComplete或者onError，这样你可以使用Single。
     */
    private void singleRxjava() {
        //被观察者
        Single<String> single = Single.create(e -> {

        });

        //订阅观察者
        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        //也可以订阅单一的
        single.subscribe((s, throwable) -> {

        });

        //single可以转换
        single.toObservable();
        single.toFlowable();
    }

    /**
     * 如果你的观察者连onNext事件都不关心，你可以使用Completable，他只有onComplete和onError两个事件：
     */
    private void completeRxjava() {

        //被观察者
        Completable.create(e -> {

        }).subscribe(new CompletableObserver() {    //观察者
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        //subscribeOn这是什么操作？
        Completable.create(e -> {

        }).subscribeOn(new Scheduler() {
            @Override
            public Worker createWorker() {
                return null;
            }
        });


    }

    /**
     * 如果你有一个需求是可能发送一个数据或者不会发送任何数据，这时候你就需要Maybe，它类似于Single和Completable的混合体。
     */
    private void maybeRxjava() {
        Maybe<String> maybe = Maybe.create(e -> {

        });

        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //无数据发送时候的onComplete事件

            }
        });

    }

}
