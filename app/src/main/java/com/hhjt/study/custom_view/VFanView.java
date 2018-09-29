package com.hhjt.study.custom_view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hhjt.study.R;


/**三角电风扇view
 * Created by SCY on 2018/9/29 at 14:59.
 */
public class VFanView extends View {

    private Paint mPaint;
    private int color;
    private int mWidth;
    private int mHeight;
    private int VFanLeafLength;//扇叶长度
    private Path mPath;
    private int duration;

    public VFanView(Context context) {
        this(context,null);
    }

    public VFanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VFanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VFanView);
        color=typedArray.getColor(R.styleable.VFanView_color,Color.parseColor("#6495ED"));
        VFanLeafLength= (int) typedArray.getDimension(R.styleable.VFanView_vFanLeafLength,200);
        duration=typedArray.getInteger(R.styleable.VFanView_duration,1500);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(color);
        mPath=new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight=measureSize(heightMeasureSpec);
        mWidth=measureSize(widthMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        for (int i = 0; i < 5; i++) {
            mPath.lineTo(VFanLeafLength,0);
            mPath.lineTo(VFanLeafLength,VFanLeafLength);
            mPath.close();
            canvas.drawPath(mPath,mPaint);
            canvas.rotate(i*90);
        }
    }

    /**
     * 测量宽高
     *
     * @param measureSpec
     * @return view的测量值
     */
    private int measureSize(int measureSpec) {
        int result;
        int measureSize = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {        //确定大小,希望是measureSize
            result = measureSize;
        } else {
            result = (int) (VFanLeafLength/Math.sin(45.0));
            if (mode == MeasureSpec.AT_MOST) {//最大大小,希望是measureSize
                result = Math.min(result, measureSize);
            }
        }
        return result;
    }


    public void start() {
        animate().rotation(360).setDuration(duration)
                .setInterpolator(new LinearInterpolator())
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        animation.setRepeatCount(ValueAnimator.INFINITE);
                    }
                }).start();
    }
}
