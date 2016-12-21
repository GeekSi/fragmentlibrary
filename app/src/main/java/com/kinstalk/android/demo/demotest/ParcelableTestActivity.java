package com.kinstalk.android.demo.demotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.kinstalk.android.model.JyGroupAlbumPhoto;
import com.kinstalk.android.model.JyGroupAlbumPhoto2;

/**
 * Created by siqing on 16/3/18.
 */
public class ParcelableTestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long start = System.currentTimeMillis();
        JyGroupAlbumPhoto jyGroupAlbumPhoto = getIntent().getParcelableExtra("a");
        long start2 = System.currentTimeMillis();
        long duration = start2 - start;
        Log.e(getClass().getSimpleName(), "====" + duration);
        JyGroupAlbumPhoto2 jyGroupAlbumPhoto2 = (JyGroupAlbumPhoto2) getIntent().getSerializableExtra("b");
        Log.e(getClass().getSimpleName(), "====" + (System.currentTimeMillis() - start2));

    }
}
