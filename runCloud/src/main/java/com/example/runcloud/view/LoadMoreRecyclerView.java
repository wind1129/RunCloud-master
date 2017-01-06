package com.example.runcloud.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Wind1129 on 16/11/23.
 */

public class LoadMoreRecyclerView extends RecyclerView {
    private static final String TAG = LoadMoreRecyclerView.class.getSimpleName();
    private OnLoadMoreListener mLoadMoreListener;

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init(){
        this.setOnScrollListener(new OnLoadMoreScrollListener());

    }

    private static class OnLoadMoreScrollListener extends OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LoadMoreRecyclerView view = (LoadMoreRecyclerView) recyclerView;
            OnLoadMoreListener loadMoreListener = view.getLoadMoreListener();

            LinearLayoutManager layoutManager = (LinearLayoutManager) view.getLayoutManager();
            int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();

            if(lastPosition>=itemCount-1){
                loadMoreListener.onLoadMore();
            }else{
                super.onScrolled(recyclerView, dx, dy);
            }
        }
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if(layout instanceof LinearLayoutManager) {
            super.setLayoutManager(layout);
        }else{
            throw new IllegalArgumentException("LoadMoreRecyclerView must have a LinearLayoutManager!");
        }
    }

    public OnLoadMoreListener getLoadMoreListener() {
        return mLoadMoreListener;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
        init();
    }
}
