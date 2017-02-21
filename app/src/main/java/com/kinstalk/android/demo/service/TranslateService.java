package com.kinstalk.android.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kinstalk.android.demo.demotest.ITranslateAidlInterface;
import com.kinstalk.android.demo.demotest.TranslateCallback;

/**
 * Created by siqing on 17/2/21.
 */

public class TranslateService extends Service {
    public String TAG = TranslateService.class.getSimpleName();
    private HandlerThread handlerThread;
    private Handler handler;

    ITranslateAidlInterface.Stub binder = new ITranslateAidlInterface.Stub() {

        @Override
        public void translate(final String english, final TranslateCallback callback) throws RemoteException {
            Log.e(TAG, "=========translate========english===" + english);
            excute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        callback.onTranslate("原文：" + english + ",翻译之后：汉文");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "=========onBind===========");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "=========onCreate===========");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "=========onStartCommand===========");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "=========onDestroy===========");
        if (handlerThread != null) {
            handlerThread.quit();
            handlerThread = null;
            handler = null;
        }
    }

    /**
     * 异步执行操作
     * @param runnable
     */
    private void excute(Runnable runnable){
        if (handler == null) {
            getThread();
        }
        handler.post(runnable);
    }

    private void getThread(){
        if (handlerThread == null) {
            handlerThread = new HandlerThread("Translate Thread");
            handlerThread.start();
        }
        if (!handlerThread.isAlive()) {
            handlerThread.start();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        handler = new Handler(handlerThread.getLooper());
    }
}
