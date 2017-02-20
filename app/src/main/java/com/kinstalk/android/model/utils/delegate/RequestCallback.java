package com.kinstalk.android.model.utils.delegate;

public interface RequestCallback<R> {
    void onResult(R result);

    void onError(Throwable e);

    void onComplete();
}