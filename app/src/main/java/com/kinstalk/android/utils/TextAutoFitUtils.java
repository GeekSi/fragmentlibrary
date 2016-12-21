package com.kinstalk.android.utils;

import android.content.Context;
import android.text.BoringLayout;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 根据TextView宽高计算字号
 * Created by siqing on 16/1/20.
 */
public class TextAutoFitUtils {


    /**
     * @param context     {@link Context}
     * @param text        内容
     * @param width       控件宽度
     * @param height      控件高度
     * @param textSize    计算起始字号一般传{@link TextView#getTextSize()}
     * @param minTextSize 最小字号
     * @return 返回计算字号结果
     */
    public static float computeTextSize(Context context, CharSequence text, int width, int height, float textSize, float minTextSize) {
        return computeTextSize(context, text, width, height, textSize, minTextSize, null);
    }

    private static float computeTextSize(Context context, CharSequence text, int width, int height, float textSize, float minTextSize, TYPE type) {
        int computeHeight = computeHeight(text, width, textSize);
        if (computeHeight == height) {
            return textSize;
        } else if (computeHeight < height) {//计算高度小于真正高度
            if (type == TYPE.LAST_SMALL) {//上次动作减小size但是调整之后的size小于需要高度直接返回textSize
                return textSize;
            }
            //textSize 设置小了需要 增大
            textSize = textSize + sp2px(context, 1);
            return computeTextSize(context, text, width, height, textSize, minTextSize, TYPE.LAST_BIG);
        } else {//计算高度大于真正高度
            if (type == TYPE.LAST_BIG) {//上次是增大size但是高度超过了调整到之前的size
                textSize = textSize - sp2px(context, 1);
                int sureHeight = computeHeight(text, width, textSize);
                if (sureHeight < height) {
                    return textSize - sp2px(context, 1);
                }
            }
            if (textSize == minTextSize) {
                return textSize;
            }
            //textSize 设置大了 需要减小
            textSize = textSize - sp2px(context, 1);
            return computeTextSize(context, text, width, height, textSize, minTextSize, TYPE.LAST_SMALL);
        }
    }

    /**
     * 计算高度
     *
     * @param text     内容
     * @param width    宽
     * @param textSize 计算字号
     * @return 返回高(px)
     */
    public static int computeHeight(CharSequence text, int width, float textSize) {
        TextPaint tp = new TextPaint();
        tp.setTextSize(textSize);
        DynamicLayout dynamicLayout = new DynamicLayout(text, tp, width, Layout.Alignment.ALIGN_CENTER, 1.0f, 1.0f, false);
//        BoringLayout dynamicLayout = new BoringLayout(text, tp, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, new BoringLayout.Metrics(), false);
//        StaticLayout dynamicLayout = new StaticLayout(text, tp, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 1.0f, true);
        return dynamicLayout.getHeight();
    }

    /**
     * sp转px
     *
     * @param context {@link Context}
     * @param sp      sp值
     * @return 返回px
     */
    public static float sp2px(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context, float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * 内部用，代表上一次缩放动作
     */
    public enum TYPE {
        LAST_BIG, LAST_SMALL
    }
}
