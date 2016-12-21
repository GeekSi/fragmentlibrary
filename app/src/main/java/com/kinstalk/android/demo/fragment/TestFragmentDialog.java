package com.kinstalk.android.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siqing.demotest.R;


/**
 * Created by siqing on 16/3/28.
 */
public class TestFragmentDialog extends DialogFragment {

    public static TestFragmentDialog newInstance(FragmentManager fm) {
        TestFragmentDialog dialog = new TestFragmentDialog();
        dialog.show(fm, TextComputeFragment.class.getSimpleName());
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog_input, null);
        return rootView;
    }
}
