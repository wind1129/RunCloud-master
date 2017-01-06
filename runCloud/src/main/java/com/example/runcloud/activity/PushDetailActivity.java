package com.example.runcloud.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.fragment.PushDetailFragment;
import com.example.runcloud.fragment.StoryFragment;
import com.example.runcloud.mvp.utils.Constants;

import butterknife.Bind;


public class PushDetailActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = PushDetailActivity.class.getSimpleName();

    protected Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        mToolbar = (Toolbar) findViewById(R.id.actionbarToolbar);
        setupActionBar();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        if (savedInstanceState == null) {
            String docID = getIntent().getStringExtra(Constants.ID);
            String keyWords = getIntent().getStringExtra(Constants.KEY_WORDS);

            PushDetailFragment pushDetailFragment = PushDetailFragment.newInstance(docID, keyWords);

            pushDetailFragment.setToolBar(mToolbar);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, pushDetailFragment, PushDetailFragment.TAG)
                    .commit();
        }
    }

    private void setupActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
