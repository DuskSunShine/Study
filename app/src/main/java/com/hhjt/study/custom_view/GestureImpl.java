package com.hhjt.study.custom_view;


import android.view.GestureDetector;
import android.view.MotionEvent;

import me.logg.Logg;

/**
 * Created by SCY on 2018/9/20 at 11:39.
 */

public class GestureImpl implements GestureDetector.OnGestureListener {

    @Override
    public boolean onDown(MotionEvent e) {
        Logg.e("onDown触摸屏幕时均会调用该方法");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Logg.e("onShowPress手指在屏幕上按下,且未移动和松开时调用该方法");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Logg.e("onSingleTapUp轻击屏幕时调用该方法");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Logg.e("onScroll手指在屏幕上滚动时会调用该方法");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Logg.e("onLongPress手指长按屏幕时均会调用该方法");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Logg.e("onFling手指在屏幕上拖动时会调用该方法");
        return false;
    }
}
