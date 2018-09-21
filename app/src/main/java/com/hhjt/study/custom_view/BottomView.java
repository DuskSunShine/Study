package com.hhjt.study.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SCY on 2018/9/19 at 17:47.
 */

public class BottomView extends ViewGroup{

    public BottomView(Context context) {
        super(context);
    }

    public BottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        View childAt=null;
        if (childCount>0) {
            for (int i = 0; i < childCount; i++) {
                childAt = getChildAt(i);
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        View childAt=null;
        if (childCount>0) {
            for (int i = 0; i < childCount; i++) {
                childAt = getChildAt(i);
               childAt.layout(0,0,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
