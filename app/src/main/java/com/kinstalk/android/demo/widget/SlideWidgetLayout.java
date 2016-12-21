package com.kinstalk.android.demo.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by siqing on 16/1/20.
 */
public class SlideWidgetLayout extends FrameLayout {
    public String TAG = "SlideWidgetLayout";
    private View dragView = null;
    private int startLeft = 0;
    private float mElevation = 50;
    private SlideWidgetListener slideListener;

    private Paint mPaint;

    private Matrix mCurrentMatrix;

    ViewDragHelper dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            boolean isDragAble = child == dragView && dragHelper.isEdgeTouched(ViewDragHelper.EDGE_LEFT);
            if (isDragAble) {
                startLeft = child.getLeft();
                if (!isNeedShader()) {
                    showShader(child);
                }
            }
            return isDragAble;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            boolean needInvalidate = false;
            if (xvel > 0) {
                needInvalidate = dragHelper.settleCapturedViewAt(dragView.getWidth(), 0);
            } else {
                needInvalidate = dragHelper.settleCapturedViewAt(0, 0);
            }
            if (needInvalidate) {
                ViewCompat.postInvalidateOnAnimation(SlideWidgetLayout.this);
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int result = left < 0 ? 0 : left;
            return result;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
//            currentLeft = left;
            ViewCompat.postInvalidateOnAnimation(SlideWidgetLayout.this);
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (state == ViewDragHelper.STATE_IDLE) {
                if (slideListener != null && dragView.getLeft() != startLeft) {
                    slideListener.onSlideClose(SlideWidgetLayout.this);
                }
            }
        }
    });
    ;

    public SlideWidgetLayout(Context context) {
        this(context, null);
    }

    public SlideWidgetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setSlideListener(SlideWidgetListener slideListener) {
        this.slideListener = slideListener;
    }

    private void init(Context context) {
        mElevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, getContext().getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Shader mShader = new LinearGradient(0, 0, mElevation, 0, new int[]{Color.TRANSPARENT, 0x0a000000, 0x20000000}, null, Shader.TileMode.CLAMP);
        mCurrentMatrix = new Matrix();
        mShader.setLocalMatrix(mCurrentMatrix);
        mPaint.setShader(mShader);
        if (isNeedShader()) {
            setWillNotDraw(false);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        inflateChild();
        boolean onIntercept = dragHelper.shouldInterceptTouchEvent(ev);
        if (dragHelper.isEdgeTouched(ViewDragHelper.EDGE_LEFT)) {
            onIntercept = true;
        }
        return onIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isNeedShader() || dragView == null) {
            return;
        }
        if (!(dragHelper.getViewDragState() == ViewDragHelper.STATE_DRAGGING || dragHelper.getViewDragState() == ViewDragHelper.STATE_SETTLING)) {
            return;
        }
        float left = dragView.getLeft() - mElevation;
        mCurrentMatrix.setTranslate(left, 0);
        mPaint.getShader().setLocalMatrix(mCurrentMatrix);
        canvas.drawRect(left, 0, dragView.getLeft(), dragView.getHeight(), mPaint);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showShader(View view) {
        view.setElevation(mElevation);
    }

    private void inflateChild() {
        if (dragView != null) {
            return;
        }
        if (getChildCount() > 0) {
            dragView = getChildAt(0);
        }
    }

    private boolean isNeedShader() {
//        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
        return true;
    }

    public interface SlideWidgetListener {
        void onSlideClose(View slideView);
    }
}
