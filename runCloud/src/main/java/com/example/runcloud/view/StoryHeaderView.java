package com.example.runcloud.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.runcloud.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wind1129 on 16/11/24.
 */

public class StoryHeaderView extends RelativeLayout {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvAuthor)
    TextView tvAuthor;
    @Bind(R.id.IvImage)
    ImageView IvImage;

    public StoryHeaderView(Context context) {
        this(context, null);
    }

    public StoryHeaderView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public StoryHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.view_header_story_height)));
        LayoutInflater.from(getContext()).inflate(R.layout.view_header_story, this, true);
        ButterKnife.bind(this);
    }

    public void bindData(String title, String author, String url) {
        tvTitle.setText(title);
        if (TextUtils.isEmpty(author)) {
            this.tvAuthor.setVisibility(View.GONE);
        } else {
            this.tvAuthor.setVisibility(View.VISIBLE);
            this.tvAuthor.setText(author);
        }
        Glide.with(getContext()).load(url).centerCrop().error(R.drawable.error).into(IvImage);
    }

    public static StoryHeaderView newInstance(ViewGroup container) {
        return new StoryHeaderView(container.getContext());
    }


}
