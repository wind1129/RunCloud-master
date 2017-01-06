package com.example.runcloud.mvp.adapt.holder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.runcloud.R;
import com.example.runcloud.entity.PushData;
import com.example.runcloud.mvp.utils.Imager;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.IntentUtils;


/**
 * Created by Wind1129 on 16/11/24.
 */
public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private PushData mPushData;

    private final CardView mCardView;

    private final ImageView newslistImage;
    private final TextView newslistTitle;
    private final TextView newslistTime;
    private final ImageView newslistFromImage;
    private final TextView newslistFrom;
    private final ImageView newslistAutherImage;
    private final TextView newslistAuther;
    private final TextView newslistContext;
    private final ImageView newslistHitImage;
    private final TextView newslistHit;
    private final ImageView newslistReplyImage;
    private final TextView newslistReply;
    private final ImageView newslistPositiveImage;
    //private final TextView tv_like_num;
    //private final ImageView iv_like;
    private final TextView newslistPositive;
    private final ImageView profileUrl_Image;
    private Context mContext;

    public StoryViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.news_item);

        newslistImage = (ImageView) itemView.findViewById(
                R.id.newslist_image);
        newslistTitle = (TextView) itemView.findViewById(
                R.id.newslist_title);
        newslistTime = (TextView) itemView.findViewById(
                R.id.newslist_time);
        newslistFromImage = (ImageView) itemView.findViewById(
                R.id.newslist_fromImage);
        newslistFrom = (TextView) itemView.findViewById(
                R.id.newslist_from);
        newslistAutherImage = (ImageView) itemView.findViewById(
                R.id.newslist_autherImage);
        newslistAuther = (TextView) itemView.findViewById(
                R.id.newslist_auther);
        newslistContext = (TextView) itemView.findViewById(
                R.id.newslist_context);
        newslistHitImage = (ImageView) itemView.findViewById(
                R.id.newslist_hitImage);
        newslistHit = (TextView) itemView.findViewById(
                R.id.newslist_hit);
        newslistReplyImage = (ImageView) itemView.findViewById(
                R.id.newslist_replyImage);
        newslistReply = (TextView) itemView.findViewById(
                R.id.newslist_reply);
        newslistPositiveImage = (ImageView) itemView.findViewById(
                R.id.newslist_positiveImage);
        newslistPositive = (TextView) itemView.findViewById(
                R.id.newslist_positive);
        profileUrl_Image = (ImageView) itemView.findViewById(
                R.id.profileUrl_Image);
        /*tv_like_num = (TextView) itemView.findViewById(
                R.id.tv_like_num);
        iv_like = (ImageView) itemView.findViewById(
                R.id.iv_like);*/
        mCardView.setOnClickListener(this);
        mContext = itemView.getContext();
    }


    public void bindStoryView(PushData pushData) {
        mPushData = pushData;
        String imageUrl = "http://10.100.100.51:8080"+pushData.getImageUrls();
        String imageOne = imageUrl.split(";")[0];
        if (imageUrl.trim().length() <= 0) {
            profileUrl_Image.setVisibility(View.GONE);
        } else {
               /*viewHolder.left_layout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));*/
            profileUrl_Image.setVisibility(View.VISIBLE);
            Imager.load(mContext, imageOne, profileUrl_Image);

        }

        /*String titleImage = pushData.getLogo();
        int i = R.drawable.home_sina_news_2x;
        switch (titleImage) {

            case "1":
                i = R.drawable.twitter2x;
                break;
            case "2":
                i = R.drawable.home_sina_news_2x;
                break;
            case "3":
                i = R.drawable.home_tengxun2x;
                break;
            case "4":
                i = R.drawable.home_sohu2x;
                break;
            case "5":
                i = R.drawable.home_wangyi2x;
                break;
            case "6":
                i = R.drawable.home_fenghuang2x;
                break;
            case "7":
                i = R.drawable.home_facebook_2x;
                break;
            case "8":
                i = R.drawable.weixin2x;
                break;
            case "9":
                i = R.drawable.label_oversea_original_2x;
                break;
            case "10":
                i = R.drawable.home_pc2x;
                break;
            case "11":
                i = R.drawable.home_sina_2x;
                break;
            case "12":
                i = R.drawable.home_mobile2x;
                break;

        }*/
        //newslistImage.setImageResource(i);

        String title = pushData.getTitle();
        title = title.replaceAll("<em>", "<font color=red>");
        title = title.replaceAll("</em>", "</font>");
        newslistTitle.setText(Html.fromHtml(title));

        newslistTime.setText(FunctionUtils.timesOne(pushData.getPublishDate()));

        if (pushData.getCopyFrom().trim().length() <= 0) {
            newslistFromImage.setVisibility(View.GONE);
            newslistFrom.setVisibility(View.GONE);
        } else {

            newslistFromImage.setImageResource(R.drawable.home_middle_from_2x);
            newslistFrom.setText(pushData.getCopyFrom());
        }

        if (pushData.getAuthor().trim().length() <= 0) {
            newslistAutherImage.setVisibility(View.GONE);
            newslistAuther.setVisibility(View.GONE);

        } else {
            newslistAutherImage.setImageResource(R.drawable.home_middle_auther_2x);
            newslistAuther.setText(pushData.getAuthor());

        }
        String summary = pushData.getSummary();
        summary = summary.replaceAll("<em>", "<font color=red>");
        summary = summary.replaceAll("</em>", "</font>");
        summary = summary.replaceAll("<p>", "");
        summary = summary.replaceAll("</p>", "");
        newslistContext.setText(Html.fromHtml("\t\t\t\t" + summary));

        newslistHitImage.setImageResource(R.drawable.home_middle_hit_2x);
        newslistHit.setText(pushData.getClickNum()+"");
        newslistReplyImage.setImageResource(R.drawable.home_middle_reply_2x);
        newslistReply.setText(pushData.getReplyNum()+"");
        newslistPositiveImage.setImageResource(R.drawable.home_middle_positive_2x);


        int status = pushData.getNegativeStatus();
        int statusImage = R.drawable.home_middle_positive_2x;
        switch (status) {
            case 1:
                statusImage = R.drawable.home_middle_positive_2x;
                newslistPositive.setText("正面");
                newslistPositive.setTextColor(Color.rgb(102, 205, 0));
                break;
            case 0:
                statusImage = R.drawable.home_middle_neutral_2x;
                newslistPositive.setText("中性");
                newslistPositive.setTextColor(Color.rgb(255, 127, 0));
                break;
            case -1:
                statusImage = R.drawable.home_middle_negative_2x;
                newslistPositive.setText("负面");
                newslistPositive.setTextColor(Color.rgb(255, 100, 97));
                break;
        }
        newslistPositiveImage.setImageResource(statusImage);
        //tv_like_num.setText(pushData.getYqLikeNum());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.news_item) {
            IntentUtils.intentToStoryActivity((Activity) v.getContext(), mPushData);
        }
    }
}
