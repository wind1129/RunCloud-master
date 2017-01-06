package com.example.runcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import cn.jpush.android.api.JPushInterface;

import com.example.runcloud.activity.AddAttention;
import com.example.runcloud.activity.AddNetizen;
import com.example.runcloud.activity.MyAttention;
import com.example.runcloud.activity.SearchActivity;
import com.example.runcloud.activity.Validate;
import com.example.runcloud.fragment.FragmentMainActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WelcomeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //setTranslucentStatus();
        setContentView(R.layout.welcome);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, Validate.class);
                startActivity(intent);
                //finish();
            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

}
