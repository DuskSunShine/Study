package com.hhjt.study.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://blog.csdn.net/lfdfhl/article/details/51508727
 * Created by SCY on 2018/9/21 at 17:26.
 */

public class LabelLayout extends ViewGroup {
    private int verticalSpacing = 20;
    private int labId=0;
    public LabelLayout(Context context) {
        this(context, null);
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
        int usedWidth = paddingLeft + paddingRight;
        int usedHeight = paddingBottom + paddingTop;

        int childMaxHeightOfThisLine = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != GONE) {
                int childUsedWidth = 0;
                int childUsedHeight = 0;
                //测量子view
                measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                childUsedWidth += childAt.getMeasuredWidth();
                childUsedHeight += childAt.getMeasuredHeight();

                LayoutParams layoutParams = childAt.getLayoutParams();

                MarginLayoutParams params = (MarginLayoutParams) layoutParams;
                //计算子view需要占用的宽高
                childUsedWidth += params.leftMargin + params.rightMargin;
                childUsedHeight += params.bottomMargin + params.topMargin;
                //是否换行
                if (usedWidth + childUsedWidth < widthSize) {
                    usedWidth += childUsedWidth;
                    if (childUsedHeight > childMaxHeightOfThisLine) {
                        childMaxHeightOfThisLine = childUsedHeight;
                    }
                } else {
                    usedHeight += childMaxHeightOfThisLine + verticalSpacing;
                    usedWidth = paddingLeft + paddingRight + childUsedWidth;
                    childMaxHeightOfThisLine = childUsedHeight;
                }

            }
        }
        usedHeight += childMaxHeightOfThisLine;
        setMeasuredDimension(widthSize, usedHeight);
    }

    //测量完成后，摆放子view。核心是确定每个子view的left,top,right,bottom
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childStartLayoutX = getPaddingLeft();
        int childStartLayoutY = getPaddingTop();
        int usedWidth = getPaddingLeft() + getPaddingRight();
        int childMaxHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childNeededWidth, childNeedHeight;
                int left, top, right, bottom;

                int childMeasuredWidth = child.getMeasuredWidth();
                int childMeasuredHeight = child.getMeasuredHeight();

                LayoutParams childLayoutParams = child.getLayoutParams();
                MarginLayoutParams  marginLayoutParams = (MarginLayoutParams) childLayoutParams;
                int childLeftMargin = marginLayoutParams.leftMargin;
                int childTopMargin = marginLayoutParams.topMargin;
                int childRightMargin = marginLayoutParams.rightMargin;
                int childBottomMargin = marginLayoutParams.bottomMargin;
                childNeededWidth = childLeftMargin + childRightMargin + childMeasuredWidth;
                childNeedHeight = childTopMargin + childBottomMargin + childMeasuredHeight;

                if (usedWidth + childNeededWidth <= r - l) {
                    if (childNeedHeight > childMaxHeight) {
                        childMaxHeight = childNeedHeight;
                    }
                    left = childStartLayoutX + childLeftMargin;
                    top = childStartLayoutY + childTopMargin;
                    right = left + childMeasuredWidth;
                    bottom = top + childMeasuredHeight;
                    usedWidth += childNeededWidth;
                    childStartLayoutX += childNeededWidth;
                } else {
                    childStartLayoutY += childMaxHeight + verticalSpacing;
                    childStartLayoutX = getPaddingLeft();
                    usedWidth = getPaddingLeft() + getPaddingRight();
                    left = childStartLayoutX + childLeftMargin;
                    top = childStartLayoutY + childTopMargin;
                    right = left + childMeasuredWidth;
                    bottom = top + childMeasuredHeight;
                    usedWidth += childNeededWidth;
                    childStartLayoutX += childNeededWidth;
                    childMaxHeight = childNeedHeight;
                }
                child.layout(left, top, right, bottom);
            }
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    public void showLab(ArrayList<String> charSequences){
        TextView textView;
        for (int i = 0; i < charSequences.size(); i++) {
             textView= new TextView(getContext());
             textView.setId(new AtomicInteger(++labId).get());
             textView.setText(charSequences.get(i));
            addView(textView,new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        }

    }
}
