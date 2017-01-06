package com.example.runcloud.mvp.adapt;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.entity.NetizenData;
import com.example.runcloud.net.DB;
import com.example.runcloud.util.FunctionUtils;

import java.util.List;

import io.realm.Realm;
import com.example.runcloud.mvp.interf.OnListFragmentInteract;
import com.example.runcloud.mvp.other.Data;
import com.example.runcloud.mvp.utils.Imager;

/**
 * Created by Summers on 2016/9/1.
 */
public class NetizenNewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NetizenData> list;
    private Realm mRealm;
    private OnListFragmentInteract mListener;

    private static final int TYPE_ITEM = 2;
    private static final int TYPE_FOOTER = 3;

    public static int textGrey;
    public static int textDark;


    public NetizenNewsListAdapter(OnListFragmentInteract listener, BaseActivity activity, int newsType) {
        mListener = listener;
        mRealm = activity.mRealm;
        list = DB.findAll(mRealm, NetizenData.class);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.footer_loading, parent, false);
            return new FooterViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.news_list, parent, false);
            return new ItemViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context, R.color.darker_gray);
        textDark = ContextCompat.getColor(context, android.support.design.R.color.abc_primary_text_material_light);

        if (holder instanceof ItemViewHolder) {
            final ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.newsContent = list.get(position);


            String imageUrl = viewHolder.newsContent.getImageUrls();
            String imageOne = imageUrl.split(";")[0];
            if (imageUrl.trim().length() <= 0) {
                viewHolder.profileUrl_Image.setVisibility(View.GONE);
            } else {
               /*viewHolder.left_layout.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));*/
                viewHolder.profileUrl_Image.setVisibility(View.VISIBLE);
                Imager.load(viewHolder.itemView.getContext(), imageOne, viewHolder.profileUrl_Image);

            }

            String titleImage = viewHolder.newsContent.getLogo();
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

            }
            viewHolder.newslistImage.setImageResource(i);

            String title = viewHolder.newsContent.getTitle();
            title = title.replaceAll("<em>", "<font color=red>");
            title = title.replaceAll("</em>", "</font>");
            viewHolder.newslistTitle.setText(Html.fromHtml(title));

            viewHolder.newslistTime.setText(FunctionUtils.timesOne(viewHolder.newsContent.getPublishDate()));

            if (viewHolder.newsContent.getCopyFrom().trim().length() <= 0) {
                viewHolder.newslistFromImage.setVisibility(View.GONE);
                viewHolder.newslistFrom.setVisibility(View.GONE);
            } else {

                viewHolder.newslistFromImage.setImageResource(R.drawable.home_middle_from_2x);
                viewHolder.newslistFrom.setText(viewHolder.newsContent.getCopyFrom());
            }

            if (viewHolder.newsContent.getAuthor().trim().length() <= 0) {
                viewHolder.newslistAutherImage.setVisibility(View.GONE);
                viewHolder.newslistAuther.setVisibility(View.GONE);

            } else {
                viewHolder.newslistAutherImage.setImageResource(R.drawable.home_middle_auther_2x);
                viewHolder.newslistAuther.setText(viewHolder.newsContent.getAuthor());

            }
            String summary = viewHolder.newsContent.getSummary();
            summary = summary.replaceAll("<em>", "<font color=red>");
            summary = summary.replaceAll("</em>", "</font>");
            summary = summary.replaceAll("<p>", "");
            summary = summary.replaceAll("</p>", "");
            viewHolder.newslistContext.setText(Html.fromHtml("\t\t\t\t" + summary));

            viewHolder.newslistHitImage.setImageResource(R.drawable.home_middle_hit_2x);
            viewHolder.newslistHit.setText(viewHolder.newsContent.getClickNum());
            viewHolder.newslistReplyImage.setImageResource(R.drawable.home_middle_reply_2x);
            viewHolder.newslistReply.setText(viewHolder.newsContent.getReplyNum());
            viewHolder.newslistPositiveImage.setImageResource(R.drawable.home_middle_positive_2x);


            int status = viewHolder.newsContent.getNegativeStatus();
            int statusImage = R.drawable.home_middle_positive_2x;
            switch (status) {
                case 1:
                    statusImage = R.drawable.home_middle_positive_2x;
                    viewHolder.newslistPositive.setText("正面");
                    viewHolder.newslistPositive.setTextColor(Color.rgb(102,205,0));
                    break;
                case 0:
                    statusImage = R.drawable.home_middle_neutral_2x;
                    viewHolder.newslistPositive.setText("中性");
                    viewHolder.newslistPositive.setTextColor(Color.rgb(255,127,0));
                    break;
                case -1:
                    statusImage = R.drawable.home_middle_negative_2x;
                    viewHolder.newslistPositive.setText("负面");
                    viewHolder.newslistPositive.setTextColor(Color.rgb(255,100,97));
                    break;
            }
            viewHolder.newslistPositiveImage.setImageResource(statusImage);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(viewHolder);
                    }

                }
            });
        }

        if (holder instanceof FooterViewHolder) {
            final FooterViewHolder viewHolder = (FooterViewHolder) holder;
            if(list.size()%10!=0|list.size()==0){
                viewHolder.progress_bar.setVisibility(View.GONE);
                viewHolder.more_data_msg.setText("没有更多数据了");
            }else{
                viewHolder.progress_bar.setVisibility(View.VISIBLE);
                viewHolder.more_data_msg.setText("努力加载中...");

            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void addNews(Data news) {
        //notifyDataSetChanged();
    }

    public List<NetizenData> getList() {
        return list;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public final View progress_bar;
        public final TextView more_data_msg;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progress_bar = itemView.findViewById(R.id.progress_bar);
            more_data_msg = (TextView) itemView.findViewById(R.id.more_data_msg);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final ImageView newslistImage;
        public final TextView newslistTitle;
        public final TextView newslistTime;
        public final ImageView newslistFromImage;
        public final TextView newslistFrom;
        public final ImageView newslistAutherImage;
        public final TextView newslistAuther;
        public final TextView newslistContext;
        public final ImageView newslistHitImage;
        public final TextView newslistHit;
        public final ImageView newslistReplyImage;
        public final TextView newslistReply;
        public final ImageView newslistPositiveImage;
        public final TextView newslistPositive;
        public final ImageView profileUrl_Image;

        public final View mItem;
        public final LinearLayout left_layout;

        public NetizenData newsContent;


        public ItemViewHolder(View itemView) {
            super(itemView);

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
            left_layout = (LinearLayout) itemView.findViewById(
                    R.id.left_layout);
            mItem = itemView.findViewById(R.id.news_item);


        }
    }

}
