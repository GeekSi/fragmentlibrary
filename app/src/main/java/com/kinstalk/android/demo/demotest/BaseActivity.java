package com.kinstalk.android.demo.demotest;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by siqing on 17/2/20.
 */

public class BaseActivity extends AppCompatActivity {

    protected final CompositeDisposable disposables = new CompositeDisposable();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
