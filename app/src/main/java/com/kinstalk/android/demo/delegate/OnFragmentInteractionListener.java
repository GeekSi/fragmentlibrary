package com.kinstalk.android.demo.delegate;

import android.support.v4.app.Fragment;

public interface OnFragmentInteractionListener {
    void onFragmentInteraction(String id);

    void onFragmentClose(Fragment fragment);
}