package com.hhjt.study.custom_view;

import android.content.res.Configuration;
import android.os.MessageQueue;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hhjt.study.R;
import com.hhjt.study.retrofit.User;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;


import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Stream;


import me.logg.Logg;


public class SafeViewActivity extends AppCompatActivity {
    private LinkedList<String> linkedList=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_view);
        SafeView safeView=findViewById(R.id.safe);
        TimeView timeView=findViewById(R.id.timeView);
        //safeView.setText("hahahha");
        //safeView.start(10,5000);
        linkedList.add("new one");
        linkedList.add("new two");
        ArrayList<User> list=new ArrayList<>();
        long l = System.currentTimeMillis();
        User user=null;
        for (int i = 0; i < 1000000; i++) {
            user=new User(i,"name"+i);
            list.add(user);
        }
        long l1 = System.currentTimeMillis();
        Logg.e("时间",(l1-l)/1000+""+list.get(0));
        //CrashReport.testJavaCrash();
        String s="0.9.0";
        String s1="1.0.0";
        String[] split = s.split("\\.");
        String[] split1 = s1.split("\\.");
        Logg.e("len",split.length+":"+split1.length);
        HashMap<Integer,String> map=new HashMap<>();
        map.put(1,"s");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("as","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String as = savedInstanceState.getString("as");
        Logg.e(as);
    }
}
