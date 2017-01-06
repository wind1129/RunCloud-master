package com.example.runcloud.fragment;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.activity.FeedbackActivity;
import com.example.runcloud.activity.PushDataActivity;
import com.example.runcloud.activity.SettingActivity;
import com.example.runcloud.mvp.utils.UIUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FragmentMainActivity extends BaseActivity implements OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private MainTab01 mTab01;
    private MainTab02 mTab02;
    private MainTab03 mTab03;
    private MainTab04 mTab04;
    //private SettingFragment mTab05;

    /**
     * 底部五个按钮
     */
    private LinearLayout mTabBtnPublicfeelings;
    private LinearLayout mTabBtnOversea;
    private LinearLayout mTabBtnNetpeople;
    private LinearLayout mTabBtnWeixin;
    //private LinearLayout mTabBtnSettings;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    private String FramgmentId = "Tab5";

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_drawer)
    NavigationView navigationView;

    @Bind(R.id.actionbarToolbar)
    Toolbar mToolbar;

    @Bind(R.id.iv_feedback)
    ImageView iv_feedback;


    private ActionBarDrawerToggle mDrawerToggle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTranslucentStatus();
        setContentView(R.layout.fragment_main);
        ButterKnife.bind(this);
        setUpDrawer();
        initNavigationView();
        initViews();
        fragmentManager = getFragmentManager();
        Intent intent = getIntent();
        if (FramgmentId.equals(intent.getStringExtra("FramgmentId"))) {
            setTabSelection(4);
            return;
        }
        ;

        setTabSelection(0);
        iv_feedback.setOnClickListener(this);

    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateMenu(R.menu.nav_menu);

        Menu menu = navigationView.getMenu();
        menu.getItem(0).setChecked(true);
        menu.getItem(0).setIcon(R.drawable.nav_note);
        menu.getItem(1).setIcon(R.drawable.nav_star);
        menu.getItem(2).setIcon(R.drawable.nav_settings);
    }

    private void setUpDrawer() {
        if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = UIUtils.getStatusBarHeight(this);
            mToolbar.setPadding(0, statusBarHeight, 0, 0);
        }
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        drawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    private void initViews() {

        mTabBtnPublicfeelings = (LinearLayout) findViewById(R.id.id_tab_bottom_publicfeelings);
        mTabBtnOversea = (LinearLayout) findViewById(R.id.id_tab_bottom_oversea);
        mTabBtnNetpeople = (LinearLayout) findViewById(R.id.id_tab_bottom_netpeople);
        mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
        //mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

        mTabBtnPublicfeelings.setOnClickListener(this);
        mTabBtnOversea.setOnClickListener(this);
        mTabBtnNetpeople.setOnClickListener(this);
        mTabBtnWeixin.setOnClickListener(this);
        //mTabBtnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_bottom_publicfeelings:
                setTabSelection(0);
                break;
            case R.id.id_tab_bottom_oversea:
                setTabSelection(1);
                break;
            case R.id.id_tab_bottom_netpeople:
                setTabSelection(2);
                break;
            case R.id.id_tab_bottom_weixin:
                setTabSelection(3);
                break;
            case R.id.iv_feedback:
                Intent intent = new Intent();
                intent.setClass(this,FeedbackActivity.class);
                startActivity(intent);

            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        // 重置按钮
        resetBtn();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnPublicfeelings.findViewById(R.id.btn_tab_bottom_publicfeelings))
                        .setImageResource(R.drawable.label_publicfeelings_selected_2x);

                // 如果MessageFragment为空，则创建一个并添加到界面上
                mTab01 = new MainTab01();
                transaction.replace(R.id.id_content, mTab01);

                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnOversea.findViewById(R.id.btn_tab_bottom_oversea))
                        .setImageResource(R.drawable.label_oversea_selected_2x);

                // 如果MessageFragment为空，则创建一个并添加到界面上
                mTab02 = new MainTab02();
                transaction.replace(R.id.id_content, mTab02);

                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnNetpeople.findViewById(R.id.btn_tab_bottom_netpeople))
                        .setImageResource(R.drawable.label_netpeople_selected_2x);

                // 如果NewsFragment为空，则创建一个并添加到界面上
                mTab03 = new MainTab03();
                transaction.replace(R.id.id_content, mTab03);
                break;

            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                        .setImageResource(R.drawable.label_weixin_selected_2x);
                // 如果SettingFragment为空，则创建一个并添加到界面上
                mTab04 = new MainTab04();
                transaction.replace(R.id.id_content, mTab04);
                break;




        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        ((ImageButton) mTabBtnPublicfeelings.findViewById(R.id.btn_tab_bottom_publicfeelings))
                .setImageResource(R.drawable.label_publicfeelings_original_2x);
        ((ImageButton) mTabBtnOversea.findViewById(R.id.btn_tab_bottom_oversea))
                .setImageResource(R.drawable.label_oversea_original_2x);
        ((ImageButton) mTabBtnNetpeople.findViewById(R.id.btn_tab_bottom_netpeople))
                .setImageResource(R.drawable.label_netpeople_original_2x);
        ((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                .setImageResource(R.drawable.label_weixin_original_2x);

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.remove(mTab01);
        }
        if (mTab02 != null) {
            transaction.remove(mTab02);
        }
        if (mTab03 != null) {
            transaction.remove(mTab03);
        }
        if (mTab04 != null) {
            transaction.remove(mTab04);
        }


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_note:
                setTabSelection(0);
                break;
            case R.id.nav_star:
                Intent pushIntent = new Intent();
                pushIntent.setClass(this,PushDataActivity.class);
                startActivity(pushIntent);
                break;
            case R.id.nav_setting:
                Intent intent = new Intent();
                intent.setClass(this, SettingActivity.class);
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
