package com.hhjt.study.retrofit;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.hhjt.study.App;

/**
 * Created by SCY on 2018/9/12 at 16:58.
 */

public class SToast {
    /**
     * 保证在UI线程中显示最新的一次Toast
     */
    private static Toast mToast = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            if (mToast != null) {
                mToast.cancel();
            }
            String text = (String) msg.obj;
            mToast = Toast.makeText(App.getApp(), text, Toast.LENGTH_SHORT);
            mToast.show();
        }
    };

    public static void toast(String text) {
        mHandler.sendMessage(mHandler.obtainMessage(0, 0, Toast.LENGTH_SHORT, text));

    }

}
