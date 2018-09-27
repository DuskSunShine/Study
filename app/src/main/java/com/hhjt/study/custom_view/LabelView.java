package com.hhjt.study.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hhjt.study.R;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**标签view
 * Created by SCY on 2018/9/26 at 16:02.
 */

public class LabelView extends View{

    private static final int COLOR_DEFAULT=0x43;//只有一种颜色
    private static final int COLOR_MUTIPLE=0x56;//根据不同内容，标签不同颜色
    private Paint mPaint;
    private Paint textPaint;
    private Context context;
    private int rectBorder;//边界线宽
    private int backColor;//背景颜色
    private int style;//填充方式
    private RectF rectF;//绘制label标签的矩形框大小
    private int radius;//圆角矩形的圆角大小
    private Map<Integer,List<String>> data;
    private Rect rect;
    private int textSize;
    private int textColor;
    private int screenHeightDp;
    private int screenWidthDp;
    private int left=0;
    private int top=0;
    private int right=0;
    private int bottom=0;
    private int [] dxdy=new int[2];
    public LabelView(Context context) {
        this(context,null);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        rectBorder= (int) typedArray.getDimension(R.styleable.LabelView_rectBorder,4);
        backColor=typedArray.getColor(R.styleable.LabelView_backColor, Color.YELLOW);
        style=typedArray.getInteger(R.styleable.LabelView_style,0);
        radius= (int) typedArray.getDimension(R.styleable.LabelView_radius,10);
        textSize= (int) typedArray.getDimension(R.styleable.LabelView_labTextSize,sp2px(14));
        textColor= (int) typedArray.getDimension(R.styleable.LabelView_labTextColor,Color.WHITE);
        typedArray.recycle();
        init();

    }

    public int getRectBorder() {
        return rectBorder;
    }

    public LabelView setRectBorder(int rectBorder) {
        this.rectBorder = rectBorder;
        return this;
    }

    public int getBackColor() {
        return backColor;
    }

    public LabelView setBackColor(int backColor) {
        this.backColor = backColor;
        return this;
    }

    public int getStyle() {
        return style;
    }

    public LabelView setStyle(int style) {
        this.style = style;
        return this;
    }

    public int getRadius() {
        return radius;
    }

    public LabelView setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public Map<Integer, List<String>> getData() {
        return data;
    }

    public LabelView setData(Map<Integer, List<String>> data) {
        this.data = data;
        return this;
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(rectBorder);
        mPaint.setColor(backColor);
        textPaint=new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        if (style==0){
            mPaint.setStyle(Paint.Style.FILL);
        }else {
            mPaint.setStyle(Paint.Style.STROKE);
        }

        Configuration configuration = getResources().getConfiguration();
        screenHeightDp = configuration.screenHeightDp;
        screenWidthDp = configuration.screenWidthDp;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec)+getPaddingLeft()+getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec)+getPaddingBottom()+getPaddingTop();
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int leftStartX = getLeft() + getPaddingLeft();
        int topStartY = getTop() + getPaddingTop();
        int rightEndX = getRight() - getPaddingRight();
        int bottomEndY = getBottom() - getPaddingBottom();

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (data!=null){
            for (Map.Entry<Integer, List<String>> da:data.entrySet()) {
                Integer key = da.getKey();
                List<String> value = da.getValue();

                int size = value.size();
                for (int i = 0; i < size; i++) {

                    String s = value.get(i);
                    rect = new Rect();
                    textPaint.getTextBounds(s,0,s.length(), rect);
                    if (screenWidth()-(right-left)>30){
                        left+=getPaddingLeft()+10+right;
                        top = getPaddingTop() + 10+rect.top;
                        right+=rect.width()+10+right;
                        bottom+=rect.height()+10+rect.bottom;
                        dxdy[0]=right;
                        dxdy[1]=0;
                    }else {//剩下的宽度小于30px时
                        left=0;top=0;right=0;bottom=0;
                        left+=getPaddingLeft()+10+right;
                        top += getPaddingTop() + 10+rect.height();
                        right+=rect.width()+10+right;
                        bottom+=rect.height()+10+rect.height();
                        dxdy[0]=0;
                        dxdy[1]=bottom;
                    }
                    rectF=new RectF(left,top, right,bottom);
                    canvas.save();
                    canvas.drawRoundRect(rectF,radius,radius,mPaint);
                    canvas.drawText(s,left,rect.height(),textPaint);
                    canvas.translate(dxdy[0],dxdy[1]);
                    canvas.restore();
                    rect=null;
                    rectF=null;
                }
            }
        }

    }

    private int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
    private int screenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }
}
