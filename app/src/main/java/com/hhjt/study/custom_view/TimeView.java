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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Paint.Style.STROKE;

/**
 * Created by SCY on 2018/9/5 at 17:31.
 */

public class TimeView extends View{
    private Paint paint;//表盘画笔
    private Paint scalePaint;//刻度线画笔
    private Paint indicatorPaint;//指针画笔
    private Paint textPaint;//文字画笔
    private int height;
    private int width;
    private int cx;//圆心x坐标
    private int cy;//圆心y坐标
    private int clockRadius;//表盘圆的圆点
    private String text;
    private float degree;
    private float dy;//坐标移动的距离
    private int distanceOfIndicatorAndText=50;//文字和表盘刻度距离
    private TimerTask timerTask;
    private Timer timer=new Timer();
    private float mSecondDegree;//秒针每秒移动角度
    private float mMinDegree;//分针每分移动角度
    private float mHourDegree;//时针每小时移动角度
    private String littleText;//小表盘文字

    private static final String YEAR="year";
    private static final String MONTH="month";
    private static final String DAY="day";
    private static final String H="h";
    private static final String M="m";
    private static final String S="s";

    /*********************xml属性********************************/
    //表盘颜色
    private int clockDialColor;
    //表盘填充模式
    private int clockDialFillType;
    //表盘画笔粗细
    private float clockDialStrokeWidth;
    //刻度线颜色
    private int scaleColor;
    //刻度线粗细
    private int scaleStrokeWidth;
    //表盘圆点大小
    private int centerPointRadius;
    //文字大小
    private float textSize;
    private float littleTextSize;
    //文字颜色
    private int textColor;
    //小表盘文字颜色
    private int littleTextColor;
    //秒针颜色
    private int secondIndicatorColor;
    //秒针粗细
    private int secondIndicatorWidth;
    //秒针长度
    //private int secondIndicatorLength=dp2px()
    //分针颜色
    private int minIndicatorColor;
    //分针粗细
    private int minIndicatorWidth;
    //时针颜色
    private int hourIndicatorColor;
    //时针粗细
    private int hourIndicatorWidth;
    //距离父控件适当设置padding
    private int paddingOffset;
    //时针高度
    private int hScaleHeight;
    //分针高度
    private int mScaleHeight;
    //秒针高度
    private int sScaleHeight;

    private int hour;
    private int min;
    private int second;

    private boolean isSystemTime=true;//是否采用系统时间
    /***********************************************************/

    public TimeView(Context context) {
        this(context,null,0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        clockDialColor=typedArray.getColor(R.styleable.TimeView_clockDialColor,Color.BLACK);
        clockDialFillType= typedArray.getInt(R.styleable.TimeView_clockDialFillType,0);
        clockDialStrokeWidth=typedArray.getDimension(R.styleable.TimeView_clockDialStrokeWidth,10);
        scaleColor=typedArray.getColor(R.styleable.TimeView_scaleColor,Color.BLACK);
        scaleStrokeWidth= (int) typedArray.getDimension(R.styleable.TimeView_scaleStrokeWidth,2);
        centerPointRadius= (int) typedArray.getDimension(R.styleable.TimeView_centerPointRadius,15);
        textSize=typedArray.getDimension(R.styleable.TimeView_textSize,14);
        littleTextSize=typedArray.getDimension(R.styleable.TimeView_textSize,14);
        textColor= typedArray.getColor(R.styleable.TimeView_textColor,Color.BLACK);
        secondIndicatorColor=typedArray.getColor(R.styleable.TimeView_secondIndicatorColor,Color.RED);
        secondIndicatorWidth= (int) typedArray.getDimension(R.styleable.TimeView_secondIndicatorWidth,2);
        minIndicatorColor=typedArray.getColor(R.styleable.TimeView_minIndicatorColor,Color.BLACK);
        minIndicatorWidth= (int) typedArray.getDimension(R.styleable.TimeView_minIndicatorWidth,2);
        hourIndicatorColor=typedArray.getColor(R.styleable.TimeView_hourIndicatorColor,Color.BLACK);
        hourIndicatorWidth= (int) typedArray.getDimension(R.styleable.TimeView_hourIndicatorWidth,7);
        paddingOffset= (int) typedArray.getDimension(R.styleable.TimeView_paddingOffset,100);
        hScaleHeight= (int) typedArray.getDimension(R.styleable.TimeView_hScaleHeight,50);
        mScaleHeight= (int) typedArray.getDimension(R.styleable.TimeView_mScaleHeight,30);
        sScaleHeight= (int) typedArray.getDimension(R.styleable.TimeView_sScaleHeight,20);
        littleTextColor= typedArray.getColor(R.styleable.TimeView_littleTextColor,Color.BLACK);
        hour= typedArray.getInt(R.styleable.TimeView_hour,0);
        min= typedArray.getInt(R.styleable.TimeView_min,0);
        second= typedArray.getInt(R.styleable.TimeView_second,0);
        isSystemTime=typedArray.getBoolean(R.styleable.TimeView_systemTime,true);
        typedArray.recycle();

        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        if (clockDialFillType==0) {
            paint.setStyle(STROKE);
        }else {
            paint.setStyle(Paint.Style.FILL);
        }
        paint.setColor(clockDialColor);
        paint.setStrokeWidth(clockDialStrokeWidth);
        //刻度线
        scalePaint=new Paint();
        scalePaint.setAntiAlias(true);
        scalePaint.setStyle(STROKE);
        scalePaint.setStrokeCap(Paint.Cap.ROUND);
        scalePaint.setColor(scaleColor);
        scalePaint.setStrokeWidth(centerPointRadius);

        //文字
        textPaint=new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeCap(Paint.Cap.ROUND);
        textPaint.setStrokeWidth(4);
        //指针画笔
        indicatorPaint=new Paint();
        indicatorPaint.setAntiAlias(true);
        indicatorPaint.setStyle(Paint.Style.FILL);
        indicatorPaint.setStrokeCap(Paint.Cap.ROUND);

        littleText=getYMDTime()+geWeek();
        //************初始化当前时间,设置指针位置*****************
        setDefaultTime();
        //******************************************************

        timerTask=new TimerTask() {
            @Override
            public void run() {
                //到达360°就重置,
                if (mSecondDegree == 360) {
                    mSecondDegree = 0;
                }
                if (mMinDegree == 360) {
                    mMinDegree = 0;
                }
                if (mHourDegree == 360) {
                    mHourDegree = 0;
                }
                mSecondDegree =  mSecondDegree + 6.0f;//秒针每秒旋转角度
                mMinDegree = mMinDegree + 0.1f;//分针
                mHourDegree = mHourDegree + 1.0f/120.0f;//时针
                littleText=getYMDTime()+geWeek();
                postInvalidate();
            }
        };

        start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       width=MeasureSpec.getSize(widthMeasureSpec);
       height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
        clockRadius=Math.min(width,height)/2 - paddingOffset;
        cx=width/2;
        cy=height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制表盘
        canvas.drawCircle(cx,cy,clockRadius,paint);
        //绘制中心点
        canvas.drawPoint(cx,cy,scalePaint);
        //将坐标系移动到表盘圆心
        canvas.translate(cx,cy);
        //绘制刻度线
        scalePaint.setStrokeWidth(scaleStrokeWidth);
        for (int i = 0; i < 360; i++) {

            if (i%30==0){//小时刻度
                canvas.drawLine(clockRadius-hScaleHeight,0,clockRadius,0,scalePaint);
            }else if (i%6==0){//分刻度
                canvas.drawLine(clockRadius-mScaleHeight,0,clockRadius,0,scalePaint);
            }else {
                canvas.drawLine(clockRadius - sScaleHeight, 0, clockRadius, 0, scalePaint);
            }
            canvas.rotate(1);
        }
        canvas.save();
        //绘制文字
        for (int i = 0; i < 12; i++) {
            if (i==0){
                drawNumber(canvas, 12);
            }else {
                drawNumber(canvas,i);
            }
        }
        canvas.restore();
        //小表盘文字
        drawYMD(canvas,littleText);
        //绘制指针
        drawIndicator(canvas);

    }

    private void drawNumber(Canvas canvas, Integer i) {
        text=Integer.toString(i);
        degree=i*30;
        Rect textBound = new Rect();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.getTextBounds(text, 0, text.length(), textBound);
        dy = clockRadius - distanceOfIndicatorAndText-hScaleHeight;
        canvas.rotate(degree);
        canvas.translate(0,-dy);
        canvas.rotate(-degree);
        canvas.drawText(text, -textBound.width() / 2, textBound.height()/2, textPaint);
        canvas.rotate(degree);
        canvas.translate(0,dy);
        canvas.rotate(-degree);
    }

    /**
     * 按顺序绘制，保证秒针在最上层。
     * @param canvas
     */
    private void drawIndicator(Canvas canvas){
        //时针
        canvas.save();
        indicatorPaint.setColor(hourIndicatorColor);
        indicatorPaint.setStrokeWidth(hourIndicatorWidth);
        canvas.rotate(mHourDegree);
        canvas.drawLine(0, 0, 0,
                300-dy, indicatorPaint);
        canvas.restore();
        //分针
        canvas.save();
        indicatorPaint.setColor(minIndicatorColor);
        indicatorPaint.setStrokeWidth(minIndicatorWidth);
        canvas.rotate(mMinDegree);
        canvas.drawLine(0, 0, 0,
                200-dy, indicatorPaint);
        canvas.restore();
        //秒针
        canvas.save();
        indicatorPaint.setColor(secondIndicatorColor);
        indicatorPaint.setStrokeWidth(secondIndicatorWidth);
        canvas.rotate(mSecondDegree);
        canvas.drawLine(0, 0, 0,
                50-dy, indicatorPaint);
        canvas.restore();



    }

    /**
     * 绘制年月日时间
     * @param canvas
     * @param text
     */
    private void drawYMD(Canvas canvas, String text) {
        canvas.save();
        Rect textBound = new Rect();
        textPaint.setColor(littleTextColor);
        textPaint.setTextSize(littleTextSize);
        textPaint.getTextBounds(text, 0, text.length(), textBound);
        canvas.translate(0,clockRadius/3);
        canvas.drawText(text, -textBound.width() / 2, textBound.height()/2, textPaint);
        canvas.translate(0,-clockRadius/3);
        canvas.restore();
    }
    /**
     * 每秒移动指针
     */
    private void start(){
        timer.schedule(timerTask,0,1000);
    }

    /**
     * 设置时间
     * @param hour
     * @param min
     * @param second
     */
    public void setTime(int hour, int min, int second) {
        if (hour<0||hour>12){
            throw new IllegalStateException("请按照12小时制设置");
        }
        if (min<0||min>60){
            throw new IllegalStateException("分钟设置不合理");
        }
        if (second<0||second>60){
            throw new IllegalStateException("秒钟设置不合理");
        }
        mMinDegree = (min + second * 1.0f/60f) *6f;
        mHourDegree = (hour + min * 1.0f/60f + second * 1.0f/3600f)*30f;
        mSecondDegree = second * 6f;
        invalidate();//重绘控件
    }
    private void setDefaultTime() {
        if (isSystemTime) {
            mSecondDegree = getNowTime(S) * 6f;
            mMinDegree = (getNowTime(M) + getNowTime(S) * 1.0f / 60f) * 6f;
            if (getNowTime(H) > 12) {
                mHourDegree = (getNowTime(H) - 12 + getNowTime(M) * 1.0f / 60f + getNowTime(S) * 1.0f / 3600f) * 30f;
            }
            mHourDegree = (getNowTime(H) + getNowTime(M) * 1.0f / 60f + getNowTime(S) * 1.0f / 3600f) * 30f;
        }else {
            mMinDegree = (min + second * 1.0f/60f) *6f;
            mHourDegree = (hour + min * 1.0f/60f + second * 1.0f/3600f)*30f;
            mSecondDegree = second * 6f;
        }
    }

    private float getNowTime(String s){
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int re=0;
        switch (s) {
            case YEAR:
                re = c.get(Calendar.YEAR);
                break;
            case MONTH:
                re = c.get(Calendar.MONTH) + 1;
                break;
            case DAY:
                re = c.get(Calendar.DAY_OF_MONTH);
                break;
            case H:
                re=c.get(Calendar.HOUR_OF_DAY);
                break;
            case M:
                re=c.get(Calendar.MINUTE);
                break;
            case S:
                re=c.get(Calendar.SECOND);
                break;
        }
        return re;
    }

    private String getYMDTime(){
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat s=new SimpleDateFormat("yyyy年MM月dd日"+"\n"+"HH:mm:ss", Locale.getDefault());
        return s.format(c.getTime());
    }

    private String geWeek() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int day = c.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch (day) {
            case 1:
                week = " 星期日";
                break;
            case 2:
                week = " 星期一";
                break;
            case 3:
                week = " 星期二";
                break;
            case 4:
                week = " 星期三";
                break;
            case 5:
                week = " 星期四";
                break;
            case 6:
                week = " 星期五";
                break;
            case 7:
                week = " 星期六";
                break;
        }
        return week;
    }

    private int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getContext().getResources().getDisplayMetrics());
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getContext().getResources().getDisplayMetrics());
    }

}
