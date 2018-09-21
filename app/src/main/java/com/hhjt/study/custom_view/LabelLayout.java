package com.hhjt.study.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * https://blog.csdn.net/lfdfhl/article/details/51508727
 * Created by SCY on 2018/9/21 at 17:26.
 */

public class LabelLayout extends ViewGroup{

    public LabelLayout(Context context) {
        this(context,null);
    }

    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();

        //占用的
        int usedWidth=paddingLeft+paddingRight;
        int usedHeight=paddingBottom+paddingTop;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility()!=GONE){
                int childUsedWidth=0;
                int childUsedHeight=0;
                measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
                childUsedWidth += childAt.getMeasuredWidth();
                childUsedHeight += childAt.getMeasuredHeight();

                LayoutParams layoutParams = childAt.getLayoutParams();

                MarginLayoutParams params= (MarginLayoutParams) layoutParams;
                int leftMargin = params.leftMargin;

            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
