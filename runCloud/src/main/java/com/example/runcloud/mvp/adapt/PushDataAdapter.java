package com.example.runcloud.mvp.adapt;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.runcloud.R;
import com.example.runcloud.entity.PushData;
import com.example.runcloud.entity.PushDatas;
import com.example.runcloud.mvp.adapt.holder.HeaderViewPagerHolder;
import com.example.runcloud.mvp.adapt.holder.StoryViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wind1129 on 16/11/25.
 */

public class PushDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<Item> mItems;
    private final List<Item> mTmpItems;

    public static class Type {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_STORY = 1;
    }

    public PushDataAdapter() {
        mItems = new ArrayList<>();
        mTmpItems = new ArrayList<>();
    }

    public void setList(PushDatas pushDatas) {
        mItems.clear();
        appendList(pushDatas);
    }

    public void appendList(PushDatas pushDatas) {
        int positionStart = mItems.size();

        if (positionStart == 0) {
            Item header = new Item();
            header.setPushDatases(pushDatas.getTopList());
            mItems.add(header);
        }


        List<PushData> pushDataList = pushDatas.getPushDataList();
        for (PushData pushData : pushDataList) {
            Item storyItem = new Item();
            storyItem.setPushData(pushData);
            mItems.add(storyItem);
        }

        int itemCount = mItems.size() - positionStart;

        if (positionStart == 0) {
            notifyDataSetChanged();
        }else{
            //局部更新
            notifyItemRangeChanged(positionStart, itemCount);
        }

    }
    //下面这两个方法用来控制viewpage的自动滚动还是停止
    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof HeaderViewPagerHolder) {
            HeaderViewPagerHolder pagerHolder = (HeaderViewPagerHolder) holder;
            if (pagerHolder.isAutoScrolling()) {
                pagerHolder.stopAutoScroll();
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof HeaderViewPagerHolder) {
            HeaderViewPagerHolder pagerHolder = (HeaderViewPagerHolder) holder;
            if (!pagerHolder.isAutoScrolling()) {
                pagerHolder.startAutoScroll();
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case Type.TYPE_HEADER:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header_viewpager, parent,false);
                return new HeaderViewPagerHolder(itemView, mItems.get(0).getPushDatases());
            case Type.TYPE_STORY:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pushdata_item_list, parent,false);
                return new StoryViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Item item = mItems.get(position);
        switch (viewType) {
            case Type.TYPE_HEADER:
                ((HeaderViewPagerHolder) holder).bindHeaderView();
                break;
            case Type.TYPE_STORY:
                ((StoryViewHolder) holder).bindStoryView(item.getPushData());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return Type.TYPE_HEADER;
        }else {
            return Type.TYPE_STORY;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }



    public static class Item {
        private List<PushData> pushDatases;
        private PushData pushData;

        public List<PushData> getPushDatases() {
            return pushDatases;
        }

        public void setPushDatases(List<PushData> pushDatases) {
            this.pushDatases = pushDatases;
        }

        public PushData getPushData() {
            return pushData;
        }

        public void setPushData(PushData pushData) {
            this.pushData = pushData;
        }
    }
}
