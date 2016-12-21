package com.kinstalk.android.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.siqing.demotest.R;


/**
 * Created by siqing on 16/1/20.
 */
public class SlideFragment extends BaseFragment {

    @Override
    protected View initView(ViewGroup container, Bundle savedInstanceState) {
        View view = mInflater.inflate(R.layout.fragment_slide, null);
        return view;
    }

    @Override
    protected void initActions(View rootView) {
        super.initActions(rootView);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
