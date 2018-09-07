package com.hhjt.study;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by SCY on 2018/9/5 at 15:19.
 */

public class NetBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            ConnectivityManager  connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if(info == null || !info.isAvailable()) {
                Toast.makeText(context, "网络错误",Toast.LENGTH_SHORT).show();
                Log.e("net","net");
            }
        }
    }

}

