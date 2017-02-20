package com.kinstalk.android.model.utils;

import com.kinstalk.android.model.utils.delegate.SimpleRequestCallback;
import com.kinstalk.android.utils.LogUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;



/**
 * Created by siqing on 17/2/20.
 */

public class RequestUtils {
    public static String TAG = RequestUtils.class.getSimpleName();

    /**
     * 创建RxJava被观察者
     *
     * @param call
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> Observable<T> create(final Call<T> call) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) {
                e.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        LogUtils.logE(TAG, "===cancel==");
                        if (!call.isCanceled()) {
                            call.cancel();
                        }
                    }
                });
                LogUtils.logE(TAG, "subscribe");
                if (!e.isDisposed()) {
                    try {
                        Response<T> result = call.execute();
                        e.onNext(result.body());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        e.onError(e1);
                    }
                }
                call.cancel();
                e.onComplete();
            }
        });

    }

    /**
     * 请求
     *
     * @param observable
     * @param callback
     * @param <R>
     * @return
     */
    public static <R> Disposable excute(Observable<R> observable, final SimpleRequestCallback<R> callback) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<R>() {
                    @Override
                    public void onNext(R value) {
                        LogUtils.logE(TAG, "onResult");
                        callback.onResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.logE(TAG, "onError");
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.logE(TAG, "onComplete");
                        callback.onComplete();
                    }
                });
    }

    /**
     * @param call
     * @param callback
     * @param <R>
     * @return
     */
    public static <R> Disposable excute(Call<R> call, SimpleRequestCallback<R> callback) {

        return excute(create(call), callback);
    }
}
