package com.kinstalk.android.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.kinstalk.android.utils.LogUtils;

/**
 * Created by siqing on 16/4/7.
 */
public class CanvasView extends ViewGroup {


    private CanvasDrawView canvasDrawView;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackgroundColor(Color.argb(100, 100, 100, 100));
        setWillNotDraw(false);

        canvasDrawView = new CanvasDrawView(getContext());
        canvasDrawView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        canvasDrawView.setBackgroundColor(Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.E(getClass().getSimpleName(), "===onDraw====");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        LogUtils.E(getClass().getSimpleName(), "===dispatchDraw====");
        drawChild(canvas, canvasDrawView, 1000);
//        Drawable d = getResources().getDrawable(R.drawable.abc);
//        d.setBounds(100, 100, 300, 300);
//        d.draw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
