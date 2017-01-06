package com.example.runcloud.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 * Created by Wind1129 on 16/11/24.
 */

public class RollViewPager extends ViewPager {
    private static final String TAG = RollViewPager.class.getSimpleName();
    private final int WHAT_SCROLL = 0;
    private final long mDelayTime = 5000;
    private boolean mIsAutoScroll;


    public RollViewPager(Context context) {
        this(context, null);
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_SCROLL) {
                scrollOnce();
                sendScrollMessage(mDelayTime);
            }
        }
    };

    private void scrollOnce() {
        PagerAdapter adapter = getAdapter();
        int currentItem = getCurrentItem();
        int count;

        if (adapter == null || (count = adapter.getCount()) < 1) {
            stopAutoScroll();
            return;
        }

        if (currentItem < count) {
            currentItem++;
        }

        if (currentItem == count) {
            currentItem = 0;
        }

        //设置viewpager的当前显示页
        setCurrentItem(currentItem);
    }

    public void stopAutoScroll() {
        mIsAutoScroll = false;
        handler.removeMessages(WHAT_SCROLL);
    }

    public void startAutoScroll() {
        mIsAutoScroll = true;
        sendScrollMessage(mDelayTime);
    }

    public boolean isAutoScrolling(){
        return mIsAutoScroll;
    }

    private void sendScrollMessage(long delayTimeMills) {
        if (mIsAutoScroll) {
            handler.removeMessages(WHAT_SCROLL);
            handler.sendEmptyMessageDelayed(WHAT_SCROLL, delayTimeMills);
        }
    }

}
