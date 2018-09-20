package com.hhjt.study.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.hhjt.study.R;

/**
 * Created by SCY on 2018/9/18 at 17:35.
 */

public class SimpleTextView extends View{


    private Paint mPaint;

    private int textSize;

    private int textColor;

    private int backgroundColor;

    private String text="";

    private Rect mRect;

    private int height;

    private int width;
    private GestureDetector gestureDetector;

    public SimpleTextView(Context context) {
        this(context,null);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleTextView);

        textColor=typedArray.getColor(R.styleable.SimpleTextView_simpleTextColor, Color.BLACK);
        backgroundColor=typedArray.getColor(R.styleable.SimpleTextView_backgroundColor, Color.WHITE);
        text=typedArray.getString(R.styleable.SimpleTextView_simpleText);
        textSize=  typedArray.getDimensionPixelSize(R.styleable.SimpleTextView_simpleTextSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
        init();
        typedArray.recycle();
        gestureDetector = new GestureDetector(context, new GestureImpl());
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
       int height=MeasureSpec.getSize(heightMeasureSpec);
       int width=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode==MeasureSpec.EXACTLY){
            this.width=width;
        }else {//warp_content没有设置一个具体的值，就初始化一个值
            this.width= mRect.width();
        }
        if (heightMode==MeasureSpec.EXACTLY){
            this.height=height;
        }else {//warp_content
            this.height= mRect.height()+mRect.bottom;
        }
        setMeasuredDimension(this.width,this.height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(textColor);
        canvas.drawText(text,width/2-mRect.width()/2,mRect.height(),mPaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }
}
