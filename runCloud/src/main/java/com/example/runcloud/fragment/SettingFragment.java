package com.example.runcloud.fragment;

import com.example.runcloud.R;
import com.example.runcloud.activity.ChangePassword;
import com.example.runcloud.activity.MyAttention;
import com.example.runcloud.activity.MyNetizens;
import com.example.runcloud.activity.SettingAboutActivity;
import com.example.runcloud.activity.Validate;
import com.example.runcloud.extra.CustomDialog;
import com.example.runcloud.util.FunctionUtils;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


@SuppressLint("NewApi")
public class SettingFragment extends Fragment implements OnClickListener {
    private LinearLayout settingAboutView;
    private LinearLayout settingMyIntentView;
    private LinearLayout setting_mynetizens;
    private LinearLayout setting_password;
    private LinearLayout setting_exit_login;
    //private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.main_tab_05, container, false);
        settingMyIntentView = (LinearLayout) newsLayout.findViewById(R.id.setting_myintent);
        setting_mynetizens = (LinearLayout) newsLayout.findViewById(R.id.setting_mynetizens);
        settingAboutView = (LinearLayout) newsLayout.findViewById(R.id.setting_about);
        setting_password = (LinearLayout) newsLayout.findViewById(R.id.setting_password);
        setting_exit_login = (LinearLayout) newsLayout.findViewById(R.id.setting_exit_login);

        //mToolbar = (Toolbar) newsLayout.findViewById(R.id.actionbarToolbar);


        getActivity().findViewById(R.id.first_add).setVisibility(View.GONE);
        getActivity().findViewById(R.id.first_search).setVisibility(View.GONE);
        getActivity().findViewById(R.id.title_arrow_image).setVisibility(View.GONE);
        ((TextView)(getActivity().findViewById(R.id.title_show))).setText("设置");

        settingMyIntentView.setOnClickListener(this);
        setting_mynetizens.setOnClickListener(this);
        settingAboutView.setOnClickListener(this);
        setting_password.setOnClickListener(this);
        setting_exit_login.setOnClickListener(this);
        return newsLayout;
    }


    /*private void setupActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !(this instanceof NavigationDrawerActivity)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }*/

    /**
     * 提示框
     */
    public void showAlertDialog() {

        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("确定要退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(getActivity(), Validate.class);
                startActivity(intent);
                dialog.dismiss();
                //设置你的操作事项
                //getActivity().finish();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();

    }

    @Override
    public void onClick(View view) {
        if (FunctionUtils.isFastDoubleClick()) {
            return;
        }
        Intent intent = null;
        switch (view.getId()) {
            case R.id.setting_about:
                intent = new Intent(getActivity(), SettingAboutActivity.class);
                break;
            case R.id.setting_myintent:
                intent = new Intent(getActivity(), MyAttention.class);
                break;
            case R.id.setting_mynetizens:
                intent = new Intent(getActivity(), MyNetizens.class);
                break;
            case R.id.setting_password:
                intent = new Intent(getActivity(), ChangePassword.class);
                break;
            case R.id.setting_exit_login:
                showAlertDialog();
                //intent = new Intent(getActivity(), Validate.class);
                return;
        }

        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);

    }

}