// ITranslateAidlInterface.aidl
package com.kinstalk.android.demo.demotest;
import com.kinstalk.android.demo.demotest.TranslateCallback;

// Declare any non-default types here with import statements

interface ITranslateAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void translate(String english, TranslateCallback callback);
}
