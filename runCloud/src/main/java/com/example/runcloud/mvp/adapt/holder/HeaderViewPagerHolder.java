package com.example.runcloud.mvp.adapt.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.example.runcloud.R;
import com.example.runcloud.activity.StoryActivity;
import com.example.runcloud.entity.PushData;
import com.example.runcloud.mvp.utils.Constants;
import com.example.runcloud.util.IntentUtils;
import com.example.runcloud.view.CirclePageIndicator;
import com.example.runcloud.view.RollViewPager;
import com.example.runcloud.view.StoryHeaderView;

import java.util.List;


/**
 * Created by Wind1129 on 16/11/23.
 */
public class HeaderViewPagerHolder extends RecyclerView.ViewHolder {
    private final RollViewPager mViewPager;
    private final CirclePageIndicator mPageIndicator;
    private PagerAdapter mPagerAdapter;

    public HeaderViewPagerHolder(View itemView, List<PushData> pushDatas) {
        super(itemView);
        mViewPager = (RollViewPager) itemView.findViewById(R.id.viewPager);
        mPageIndicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
        if (pushDatas==null||pushDatas.size()<1) {
            return;
        } else if (pushDatas.size() < 2) {
            mPageIndicator.setVisibility(View.GONE);
        }
        mPagerAdapter = new HeaderPagerAdapter(pushDatas);
    }

    public void bindHeaderView(){
        if (mViewPager.getAdapter() == null) {
            mViewPager.setAdapter(mPagerAdapter);
            mPageIndicator.setViewPager(mViewPager);
        } else {
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public boolean isAutoScrolling() {
        if (mViewPager != null) {
            return mViewPager.isAutoScrolling();
        }
        return false;
    }

    public void stopAutoScroll() {
        if (mViewPager != null) {
            mViewPager.stopAutoScroll();
        }
    }

    public void startAutoScroll() {
        if (mViewPager != null) {
            mViewPager.startAutoScroll();
        }
    }

    private class HeaderPagerAdapter extends PagerAdapter {
        private final List<PushData> mStories;
        private int mChildCount;

        public HeaderPagerAdapter(List<PushData> stories) {
            mStories = stories;
        }

        @Override
        public int getCount() {
            return mStories == null ? 0 : mStories.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {



            StoryHeaderView storyHeaderView = StoryHeaderView.newInstance(container);



            final PushData story = mStories.get(position);
            storyHeaderView.bindData(story.getTitle(), story.getAuthor(), "http://10.100.100.51:8080"+story.getImageUrls());

            storyHeaderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.intentToStoryActivity((Activity) v.getContext(), story);
                }
            });
            ViewGroup parent = (ViewGroup) storyHeaderView.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }

            container.addView(storyHeaderView);
            return storyHeaderView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((StoryHeaderView) object);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            mChildCount = getCount();
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }
    }
}
