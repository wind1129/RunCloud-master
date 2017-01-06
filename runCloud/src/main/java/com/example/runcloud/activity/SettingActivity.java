package com.example.runcloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.fragment.FragmentMainActivity;
import com.example.runcloud.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wind1129 on 16/11/17.
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.actionbarToolbar)
    Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ButterKnife.bind(this);
        setupActionBar();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.setting_container,new SettingFragment()).commit();
    }

     private void setupActionBar() {
         if (null != mToolbar) {
             setSupportActionBar(mToolbar);
         }
         if (getSupportActionBar() != null) {
             getSupportActionBar().setTitle(null);
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.setClass(this, FragmentMainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
