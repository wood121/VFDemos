package com.example.wood121.viewdemos.net.http.Rx;

import com.example.wood121.viewdemos.net.http.ActivityLifeCycleEvent;
import com.example.wood121.viewdemos.net.http.HttpResult;
import com.example.wood121.viewdemos.net.http.exceptions.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by wood121 on 2018/7/17.
 * 对结果进行预处理
 */

public class RxHelper {


    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult(ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifeCycle) {

        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
                //添加的对网络请求，绑定生命周期



                return upstream.flatMap(new Function<HttpResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.getCode() == 0) {
                            //成功的数据传输出去，HttpResult分离数据
                            return createData(tHttpResult.getData());
                        } else {
                            //异常的数据传输出去,code的数值，会有msg进行说明。
                            return Observable.error(new ApiException(tHttpResult.getCode()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 处理成功对结果，将成功的data传输出去
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                //Rxjava2现在不需要try..catch操作了.
                e.onNext(data);
                e.onComplete();
            }
        });
    }
}
