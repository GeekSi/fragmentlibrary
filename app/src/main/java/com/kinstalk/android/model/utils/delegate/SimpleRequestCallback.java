package com.kinstalk.android.model.utils.delegate;

import android.util.Log;

public abstract class SimpleRequestCallback<R> implements RequestCallback<R> {
    public String TAG = SimpleRequestCallback.class.getSimpleName();

    @Override
    public void onResult(R result) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void onComplete() {

    }
}