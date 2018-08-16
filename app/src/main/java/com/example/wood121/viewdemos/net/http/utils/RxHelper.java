package com.example.wood121.viewdemos.net.http.utils;

import com.example.wood121.viewdemos.net.http.entity.ApiResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wood121
 * @desc
 * @time:2018/8/12
 */
public class RxHelper {

    /**
     * 线程的切换
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<ApiResult<T>, T> handleResult() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ApiResult<T>> upstream) {
                return upstream.flatMap(new Function<ApiResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ApiResult<T> tApiResult) throws Exception {
                        if (tApiResult.getErrorCode() == 0) {
                            return createData(tApiResult.getData());
                        } else {

                        }
                        return null;
                    }
                });
            }
        };
    }

    private static <T> ObservableSource<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                e.onNext(data);
                e.onComplete();
            }
        });
    }
}
