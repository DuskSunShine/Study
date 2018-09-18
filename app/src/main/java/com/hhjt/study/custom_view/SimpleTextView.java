package com.hhjt.study.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hhjt.study.R;

/**
 * Created by SCY on 2018/9/18 at 17:35.
 */

public class SimpleTextView extends View{


    private Paint mPaint;

    private int textSize;

    private int textColor;

    private int backgroundColor;

    private String text;

    private Rect mRect;

    private int height;

    private int width;

    public SimpleTextView(Context context) {
        this(context,null);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleTextView);

        textColor=typedArray.getColor(R.styleable.SimpleTextView_textColor, Color.BLACK);
        backgroundColor=typedArray.getColor(R.styleable.SimpleTextView_backgroundColor, Color.WHITE);
        text=typedArray.getString(R.styleable.SimpleTextView_text);
        textSize= (int) typedArray.getDimension(R.styleable.SimpleTextView_textSize,14);

        init();
        typedArray.recycle();

    }

    private void init(){
        mPaint=new Paint();
        mPaint.setTextSize(textSize);
        mPaint.setAntiAlias(true);
        mRect=new Rect();
        mPaint.getTextBounds(text,0,text.length(),mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(textColor);
        canvas.drawText(text,0,);
    }
}
