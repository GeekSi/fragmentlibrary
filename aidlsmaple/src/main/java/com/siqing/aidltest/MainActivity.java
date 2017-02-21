package com.siqing.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kinstalk.android.demo.demotest.ITranslateAidlInterface;
import com.kinstalk.android.demo.demotest.TranslateCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "MainActivity";
    private EditText editText;
    private Button translateBtn;
    private ITranslateAidlInterface translateAidlInterface;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "====onServiceConnected====");
            translateAidlInterface = ITranslateAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "====onServiceDisconnected====");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.input_edit);
        translateBtn = (Button) findViewById(R.id.translate_btn);
        translateBtn.setOnClickListener(this);
        bind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind();
    }

    @Override
    public void onClick(View v) {
        if (translateAidlInterface != null) {
            String content = editText.getText().toString();
            try {
                translateAidlInterface.translate(content, translateCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private TranslateCallback.Stub translateCallback = new TranslateCallback.Stub() {
        @Override
        public void onTranslate(String chinese) throws RemoteException {
            Log.e(TAG, chinese);
        }
    };

    private void bind(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.kinstalk.android.demo.demotest", "com.kinstalk.android.demo.service.TranslateService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbind(){
        unbindService(serviceConnection);
    }
}
