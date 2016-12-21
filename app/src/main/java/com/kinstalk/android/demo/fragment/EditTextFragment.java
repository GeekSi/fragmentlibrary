package com.kinstalk.android.demo.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.siqing.demotest.R;

/**
 * Created by siqing on 16/7/28.
 */
public class EditTextFragment extends BaseFragment {

    private EditText editText;

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        defaultDisplay.getMetrics(dm);
        return dm.heightPixels;
    }

    @Override
    protected View initView(ViewGroup container, Bundle savedInstanceState) {
        View view = mInflater.inflate(R.layout.fragment_edit, null);
        editText = (EditText) view.findViewById(R.id.content_edit);
        setConent();
        return view;
    }

    private void setConent() {
        final Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        final int margin = dip2px(10);
        d.setBounds(margin, margin, getScreenWidth(getActivity()) - margin, dip2px(200));
        ImageSpan imgSpan = new ImageSpan(d);
        final SpannableString ss = new SpannableString("<image>http://www.123.com/abc.png</image>");
        ss.setSpan(imgSpan, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.append(ss);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() == null) {
                    return;
                }
                SpannableStringBuilder ss = (SpannableStringBuilder) editText.getText();
                Drawable dd = getResources().getDrawable(R.drawable.abc);
                Bitmap bitmap = ((BitmapDrawable) dd).getBitmap();
                float ratio = (float) bitmap.getWidth() / bitmap.getHeight();
                ImageSpan imgSpan = new ImageSpan(dd);
                dd.setBounds(margin, margin, getScreenWidth(getActivity()) - 2 * margin, (int) ((getScreenWidth(getActivity()) - margin * 2) / ratio) + margin);
                ss.setSpan(imgSpan, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                editText.setText(ss);
            }
        }, 3000);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
