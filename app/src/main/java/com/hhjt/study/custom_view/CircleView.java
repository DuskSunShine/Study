package com.hhjt.study.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hhjt.study.R;

/**
 * Created by SCY on 2018/9/21 at 15:23.
 */

public class CircleView extends View{
    private Paint paint;
    private Bitmap bitmap;
    private BitmapShader bitmapShader;
    private int width;
    private int height;
    private int resourceId;
    private int anInt;
    private Matrix matrix;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        resourceId = typedArray.getResourceId(R.styleable.CircleView_srcBitmap, R.drawable.timg);
        anInt = typedArray.getInt(R.styleable.CircleView_tileMode, 0);
        typedArray.recycle();
        paint=new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(),resourceId);

        if (anInt==0){
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        }else if (anInt==1){
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }else if (anInt==2){
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        }
        matrix = new Matrix();
        matrix.postScale(0.3f,0.3f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bitmapSize=Math.min(bitmap.getWidth(),bitmap.getHeight())/2;
        int canvasSize=Math.min(getHeight(),getWidth())/2;
        int radius=Math.min(bitmapSize,canvasSize);
        canvas.setMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(canvasSize,canvasSize,radius,paint);
    }
}
