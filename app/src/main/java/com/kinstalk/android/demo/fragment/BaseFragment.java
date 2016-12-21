package com.kinstalk.android.demo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kinstalk.android.demo.delegate.OnFragmentInteractionListener;
import com.kinstalk.android.demo.widget.SlideWidgetLayout;

/**
 * Fragment基础类
 * Created by siqing on 16/1/20.
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected ViewGroup mRootView;
    protected OnFragmentInteractionListener mActivityListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof OnFragmentInteractionListener) {
            mActivityListener = (OnFragmentInteractionListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        initData(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView ");
        this.mInflater = inflater;
        View fragmentView = initView(container, savedInstanceState);
        if (isSlideAble()) {
            mRootView = new SlideWidgetLayout(container.getContext());
            mRootView.addView(fragmentView);
            initActions(fragmentView);
        } else {
            mRootView = (ViewGroup) fragmentView;
            initActions(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView ");
        mRootView = null;
        super.onDestroyView();
    }

    protected void initData(Bundle savedInstanceState) {
    }

    protected abstract View initView(ViewGroup container, Bundle savedInstanceState);

    protected boolean isSlideAble() {
        return true;
    }

    protected void initActions(View rootView) {
        if (mRootView instanceof SlideWidgetLayout) {
            ((SlideWidgetLayout) mRootView).setSlideListener(new SlideWidgetLayout.SlideWidgetListener() {
                @Override
                public void onSlideClose(View slideView) {
                    if (mActivityListener != null) {
                        mActivityListener.onFragmentClose(BaseFragment.this);
                    }
                }
            });
        }
    }

    ;

}
