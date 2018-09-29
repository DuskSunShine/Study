package com.hhjt.study.custom_view;

import android.arch.persistence.room.RoomDatabase;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.MessageQueue;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hhjt.study.R;
import com.hhjt.study.annotation.FindId;
import com.hhjt.study.retrofit.User;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;


import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Stream;


import me.logg.Logg;


public class SafeViewActivity extends AppCompatActivity {
    private LinkedList<String> linkedList=new LinkedList<>();
    private HashMap<Integer,List<String>> hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_view);
        SafeView safeView=findViewById(R.id.safe);
        TimeView timeView=findViewById(R.id.timeView);
        LabelLayout labLayout=findViewById(R.id.labLayout);
        VFanView vFanView=findViewById(R.id.vFan);
        vFanView.start();
        LabelView lab=findViewById(R.id.lab);
        ArrayList<String> arrayList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("name中国科大夫"+i);
        }
        hashMap.put(1,arrayList);
        lab.setData(hashMap);

        labLayout.showLab(arrayList);
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
