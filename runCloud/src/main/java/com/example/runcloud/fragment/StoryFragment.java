package com.example.runcloud.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.runcloud.R;
import com.example.runcloud.entity.Doc;
import com.example.runcloud.mvp.interf.NewsDetailView;
import com.example.runcloud.mvp.interf.StoryContract;
import com.example.runcloud.mvp.presenter.NewsDocDetailPresenter;
import com.example.runcloud.mvp.utils.Constants;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.ScrollPullDownHelper;
import com.example.runcloud.view.StoryHeaderView;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment implements StoryContract.View, View.OnClickListener {
    public static final String TAG = StoryFragment.class.getSimpleName();

    /**
     * 本地存储
     */
    private SharedPreferences sp;


    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind((R.id.rl_container_header))
    RelativeLayout rlStoryHeader;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.webViewContainer)
    LinearLayout llWebViewContainer;
    @Bind(R.id.spaceView)
    View spaceView;
    @Bind(R.id.content_linkto)
    View content_linkto;

    private Toolbar mActionBarToolbar;
    private StoryHeaderView mStoryHeaderView;
    private SoftReference<WebView> mWebViewSoftReference;
    private ScrollPullDownHelper mScrollPullDownHelper;
    private Doc mStory;

    private StoryContract.Presenter presenter;
    private String docID;
    private String keyWords;
    private String uri;
    private String uriPart = "/ares/restful/details/detail?docId=";
    private String url;

    /*初始化相关*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uri = sp.getString("URI", "");
        presenter = new NewsDocDetailPresenter(this);
        //setHasOptionsMenu(true);
        if (getArguments() != null) {
            docID = getArguments().getString(Constants.ID);
            keyWords = getArguments().getString(Constants.KEY_WORDS);
        }

        url = uri + uriPart + docID + "&keyWords=" + keyWords;

        mScrollPullDownHelper = new ScrollPullDownHelper();

    }


    /*view接口*/
    @Override
    public void showStory(final Doc story) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (story == null) {
                    return;
                } else {
                    mStory = story;
                    bindData(story);
                }
            }
        };
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showError() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "refresh error", Toast.LENGTH_SHORT).show();
            }
        };
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showProgressBar() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        };
        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void hideProgressBar() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        };
        getActivity().runOnUiThread(runnable);
    }
    /*view接口*/

    /*普通逻辑*/
    public static StoryFragment newInstance(String docID, String keyWords) {
        StoryFragment fragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ID, docID);
        bundle.putString(Constants.KEY_WORDS, keyWords);
        fragment.setArguments(bundle);
        return fragment;
    }

    private boolean isWebViewOK() {
        return mWebViewSoftReference != null && mWebViewSoftReference.get() != null;
    }

    private void bindData(Doc story) {
        boolean hasImage = !TextUtils.isEmpty(story.getImageUrls());
        bindHeaderView(hasImage);
        bindWebView(hasImage);
    }

    private void bindHeaderView(boolean hasImage) {
        if (hasImage) {
            if (mActionBarToolbar != null) {
                //如果有图片设置Toolbar透明
                mActionBarToolbar.getBackground().mutate().setAlpha(0);
            }
            spaceView.setVisibility(View.VISIBLE);
            rlStoryHeader.addView(mStoryHeaderView);
            String imageUrl = mStory.getImageUrls();
            String imageOne = imageUrl.split(";")[0];
            mStoryHeaderView.bindData(mStory.getTitle(), mStory.getAuthor(), imageOne);
        } else {
            spaceView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    mActionBarToolbar.getHeight()));
        }
    }


    private void bindWebView(boolean hasImage) {
        if (!TextUtils.isEmpty(mStory.getContent())) {
            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
            String s1 = "<div class=\"meta\">\n<span class=\"bio\">发布时间：" + FunctionUtils.timesOne(mStory
                    .getPublishDate()) + "</div>";
            String s2 = "<div class=\"meta\">\n<span class=\"bio\">作者：" + mStory.getAuthor() + "</div>";
            String s3 = "<div class=\"meta\">\n<span class=\"bio\">来源：" + mStory.getCopyFrom() + "</div>";
            String s0 = "<h2>" + mStory.getTitle() + "</h2>";
            if (mStory.getAuthor().trim().length() <= 0) {
                s2 = "";
            }
            if (mStory.getCopyFrom().trim().length() <= 0) {
                s3 = "";
            }

            if (hasImage) {
                s0 = "";
            }
            String html = "<html><head>" + css + "</head><body>" +
                    s0 +
                    s1 + s2 + s3 +
                    "<div class=\"content\">\n\n\n</div>" +
                    "<div class=\"content\">" + "\t\t\t\t" + mStory.getContent() + "</div></body></html>";
            html = html.replace("<div class=\"img-place-holder\">", "");
            //String data = WebUtils.buildHtmlWithCss(mStory.getBody(), mStory.getCssList(), isNightMode);
            mWebViewSoftReference.get().loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
            if (hasImage) {
                addScrollListener();
            }
        }
    }

    private void addScrollListener() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!isAdded()) {
                    return;
                }
                changeHeaderPosition();
                changeToolbarAlpha();
            }
        });
    }

    @SuppressWarnings("ResourceType")
    private void changeHeaderPosition() {
        int scrollY = scrollView.getScrollY();
        int headerScrollY = (scrollY > 0) ? (scrollY / 2) : 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mStoryHeaderView.setScrollY(headerScrollY);
            mStoryHeaderView.requestLayout();
        }
    }

    /**
     * 1.如果滚动量小于图片和toolbar的差值,设置toolbar透明度,坐标为0,直接返回
     * 2.如果滚动两大于差值,则开始修改toolbar的坐标,y坐标为滚动量的1中差值的差值
     */
    @SuppressWarnings("ResourceType")
    private void changeToolbarAlpha() {
        int scrollY = scrollView.getScrollY();
        int storyHeaderViewHeight = getStoryHeaderViewHeight();
        float toolbarHeight = mActionBarToolbar.getHeight();
        float contentHeight = storyHeaderViewHeight - toolbarHeight;

        float ratio = Math.min(scrollY / contentHeight, 1.0f);
        mActionBarToolbar.getBackground().mutate().setAlpha((int) (ratio * 0xFF));

        if (scrollY <= contentHeight) {
            mActionBarToolbar.setY(0f);
            return;
        }

        boolean isPullingDown = mScrollPullDownHelper.onScrollChange(scrollY);
        float toolBarPositionY = isPullingDown ? 0 : (contentHeight - scrollY);
        mActionBarToolbar.setY(toolBarPositionY);

    }

    private int getStoryHeaderViewHeight() {
        return getResources().getDimensionPixelSize(R.dimen.view_header_story_height);
    }

    public void setToolBar(Toolbar toolbar) {
        this.mActionBarToolbar = toolbar;
    }
    /*普通逻辑*/

    /*生命周期回调*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mStoryHeaderView = StoryHeaderView.newInstance(container);
        mWebViewSoftReference = new SoftReference<>(new WebView(getActivity()));

        if (isWebViewOK()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //相当于webview setLayoutParams
            mWebViewSoftReference.get().setLayoutParams(layoutParams);
        }

        return inflater.inflate(R.layout.fragment_story, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //去除滑动到底部的蓝色阴影
        scrollView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

        if (isWebViewOK()) {
            // 新添加webview视图
            llWebViewContainer.addView(mWebViewSoftReference.get());
        }
        content_linkto.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.refresh(url);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isWebViewOK()) {
            mWebViewSoftReference.get().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isWebViewOK()) {
            mWebViewSoftReference.get().onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isWebViewOK()) {
            mWebViewSoftReference.get().removeAllViews();
            mWebViewSoftReference.get().destroy();
            llWebViewContainer.removeView(mWebViewSoftReference.get());
            mWebViewSoftReference = null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_story, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        /*if (id == R.id.action_share) {
            if (mStory != null) {
                IntentUtils.share(getActivity(), mStory);
            }
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_linkto:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mStory.getUrl()));
                startActivity(intent);
                break;
        }
    }
}
