package com.example.runcloud.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.fragment.PushDataFragment;

/**
 * Created by Wind1129 on 16/11/25.
 */
public class PushDataActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_activity_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container,new PushDataFragment()).commit();
    }
}
