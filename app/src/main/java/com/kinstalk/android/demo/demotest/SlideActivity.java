package com.kinstalk.android.demo.demotest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.kinstalk.android.demo.fragment.InputFragment;
import com.kinstalk.android.demo.fragment.SlideFragment;
import com.kinstalk.android.demo.widget.SlideWidgetLayout;
import com.siqing.demotest.R;

/**
 * Created by siqing on 16/1/25.
 */
public class SlideActivity extends FragmentActivity {
    public String TAG = SlideActivity.class.getSimpleName();
    private FrameLayout containerBox;
    private SlideFragment slideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
        containerBox = (FrameLayout) findViewById(R.id.container_layout);
        slideFragment = new SlideFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, slideFragment).commitAllowingStateLoss();
        InputFragment inputFragment = new InputFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.input_layout, inputFragment).commit();
    }

    @Override
    public void setContentView(int layoutResID) {
        View root = LayoutInflater.from(this).inflate(layoutResID, null);
        SlideWidgetLayout slideWidgetLayout = new SlideWidgetLayout(this);
        slideWidgetLayout.addView(root);
        slideWidgetLayout.setSlideListener(new SlideWidgetLayout.SlideWidgetListener() {
            @Override
            public void onSlideClose(View slideView) {
                finish();
            }
        });
        setContentView(slideWidgetLayout);
    }
}
