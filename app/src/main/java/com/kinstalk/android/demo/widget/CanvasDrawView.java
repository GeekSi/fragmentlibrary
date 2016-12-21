package com.kinstalk.android.demo.widget;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by siqing on 16/4/7.
 */
public class CanvasDrawView extends LinearLayout {

    public CanvasDrawView(Context context) {
        super(context);

        setOrientation(VERTICAL);

        TextView nameText = new TextView(getContext());
        nameText.setText("你妹的");

        LayoutParams lps = new LayoutParams(200, 200);
        nameText.setLayoutParams(lps);

        ImageView iconImg = new ImageView(getContext());
        iconImg.setBackgroundColor(Color.BLUE);
        lps = new LayoutParams(200, 200);
        nameText.setLayoutParams(lps);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200, 400);
    }


}
