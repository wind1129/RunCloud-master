package com.example.runcloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CopyOfWelcomeActivity extends Activity {
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mData = new ArrayList<String>(Arrays.asList("Validate", "FragmentManager & Fragment", "MyAttention", "Netizen", "AddAttention"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome1);

        mListView = (ListView) findViewById(R.id.id_listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(CopyOfWelcomeActivity.this, Validate.class);
                        break;
                    case 1:
                        intent = new Intent(CopyOfWelcomeActivity.this, FragmentMainActivity.class);
                        break;
                    case 2:
                        intent = new Intent(CopyOfWelcomeActivity.this, SearchActivity.class);
                        break;
                    case 3:
                        intent = new Intent(CopyOfWelcomeActivity.this, AddNetizen.class);
                        break;
                    case 4:
                        intent = new Intent(CopyOfWelcomeActivity.this, AddAttention.class);
                        break;


                    default:
                        break;
                }
                startActivity(intent);
            }
        });

    }

}
