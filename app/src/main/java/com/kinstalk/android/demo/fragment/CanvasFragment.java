package com.kinstalk.android.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.kinstalk.android.demo.widget.CanvasView;

/**
 * Created by siqing on 16/4/7.
 */
public class CanvasFragment extends BaseFragment {

    private CanvasView canvasView;

    @Override
    protected View initView(ViewGroup container, Bundle savedInstanceState) {
        canvasView = new CanvasView(getContext());
        return canvasView;
    }

}
