package com.example.runcloud.activity;


import com.example.runcloud.BaseActivity;
import com.example.runcloud.R;
import com.example.runcloud.entity.Doc;
import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.net.DB;
import com.example.runcloud.util.FunctionUtils;
import com.example.runcloud.util.HttpUtils;
import android.annotation.SuppressLint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.runcloud.mvp.interf.NewsDetailPresenter;
import com.example.runcloud.mvp.interf.NewsDetailView;
import com.example.runcloud.mvp.presenter.NewsDocDetailPresenter;
import com.example.runcloud.mvp.utils.Constants;
import com.example.runcloud.mvp.utils.Imager;
import com.example.runcloud.mvp.utils.Share;

public class ContentActivity extends BaseActivity implements OnClickListener,NewsDetailView {
	/**本地存储*/
	private SharedPreferences sp;
	
	private ImageView backView;
	private TextView titleView;
	private TextView fromView;
	private TextView authorView;
	private TextView dateView;
	private TextView contentView;
	
	private TextView contentLink;
	private String uri;
	private String uriPart = "/ares/restful/details/detail?docId=";
	private String url;


	//升级
	@Bind(R.id.detail_img)
	ImageView detailImg;
	@Bind(R.id.toolbar_layout)
	CollapsingToolbarLayout toolbarLayout;
	@Bind(R.id.fab)
	FloatingActionButton fab;
	@Bind(R.id.progress)
	ProgressBar progress;
	@Bind(R.id.web_container)
	FrameLayout webContainer;
	private WebView webView;
	private NewsContentMessage newsContentMessage;
	private Doc doc;
	String docID;
	String keyWords;
	protected Toolbar toolbar;
	@Bind(R.id.content_linkto)
	View content_linkto;
	String context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_content);
		//setTranslucentStatus();
		ButterKnife.bind(this);

		initAppBar();
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		uri = sp.getString("URI", "");
		initViews();


		content_linkto.setOnClickListener(this);

	}

	public void initAppBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (null != toolbar) {
			setSupportActionBar(toolbar);
		}
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void initViews() {
		Intent intent=getIntent();
		docID=intent.getStringExtra("docID");
		keyWords=intent.getStringExtra(Constants.KEY_WORDS);
		String url = uri+uriPart+docID+"&keyWords="+keyWords;
		Log.i("keywords",url);
		doc= DB.getById(mRealm, docID, Doc.class);

		/*NewsDetailPresenter presenter = new NewsDocDetailPresenter(this, this);
		initWebView();
		if (doc == null) {
			presenter.loadNewsDetail(url);
		} else {
			showDetail();
		}*/
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		webView = new WebView(this);
		webContainer.addView(webView);
		webView.setVisibility(View.INVISIBLE);
		webView.setVerticalScrollBarEnabled(false);
		webContainer.setVisibility(View.INVISIBLE);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		settings.setAppCacheEnabled(true);
		settings.setDomStorageEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(final WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					view.postDelayed(new Runnable() {
						@Override
						public void run() {
							if(doc.getImageUrls()!=null) {
								String imageUrl = doc.getImageUrls();
								String imageOne = imageUrl.split(";")[0];
								Imager.loadImage(ContentActivity.this, imageOne, detailImg);


							}


							view.setVisibility(View.VISIBLE);
							webContainer.setVisibility(View.VISIBLE);
							hideProgress();
						}
					}, 300);
				}
			}
		});
	}

	@Override
	public void showProgress() {
		progress.setVisibility(View.VISIBLE);
	}

	@Override
	public void showDetail() {

		doc= DB.getById(mRealm, docID, Doc.class);
		if(doc==null){
			FunctionUtils.showCustomDialog("网络连接超时，\n没有获取到数据...",
					this);
			return;
		}
		if(doc!=null) {
			String title = doc.getTitle();
			title = title.replaceAll("<em>", "<font color=red>");
			title = title.replaceAll("</em>", "</font>");
			toolbarLayout.setTitle(Html.fromHtml(title));
		}else{
			toolbarLayout.setTitle("锐云舆情");
		}

		//Imager.load(this, detailNews.getImage(), detailImg);
		//add css style to webView
		String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
		if(doc!=null){
		context = doc.getContent();
		}else{
			context = "没有获取到内容";
		}
		context=context.replaceAll("<em>","<font color=red>");
		context=context.replaceAll("</em>","</font>");
		String s1 ="<div class=\"meta\">\n<span class=\"bio\">发布时间："+FunctionUtils.timesOne(doc.getPublishDate())+ "</div>";
		String s2 ="<div class=\"meta\">\n<span class=\"bio\">作者："+doc.getAuthor()+ "</div>";
		String s3="<div class=\"meta\">\n<span class=\"bio\">来源："+doc.getCopyFrom()+ "</div>";
		if(doc.getAuthor().trim().length()<=0){
			s2="";
		}
		if(doc.getCopyFrom().trim().length()<=0){
			s3="";
		}
		String html = "<html><head>" + css + "</head><body>" +
				"\n" +
				s1+s2+s3+
				"<div class=\"content\">\n\n\n</div>"+
				"<div class=\"content\">"+"\t\t\t\t"+ context+ "</div></body></html>";
		html = html.replace("<div class=\"img-place-holder\">", "");
		webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);



		initFAB();
	}


	private void initFAB() {
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = doc.getTitle();
				title = title.replaceAll("<em>", "");
				title = title.replaceAll("</em>", "");
				Share.shareText(ContentActivity.this,"("+title+")"+doc.getUrl());
			}
		});
	}

	@Override
	public void hideProgress() {
		progress.setVisibility(View.GONE);
	}

	@Override
	public void showLoadFailed(String msg) {

	}


	private class ContentTask extends AsyncTask<Void, Void, NewsContentMessage> {

		@Override
		protected NewsContentMessage doInBackground(Void... arg0) {
			Intent intent=getIntent();
			String docID=intent.getStringExtra("docID");
			String url = uri+uriPart+docID;
			String jsonStr = HttpUtils.getJsonStr(url);
			NewsContentMessage newsContentMessage=HttpUtils.NewsContentsFromJson(jsonStr);
			return newsContentMessage;
		}
		
		@Override  
        protected void onPostExecute(NewsContentMessage newsContentMessage){
			if(newsContentMessage==null){
				FunctionUtils.showCustomDialog("与服务器失去连接，\n请检查网络",
						ContentActivity.this);
				return;
			}
			if(newsContentMessage.getDoc()==null){
				FunctionUtils.showCustomDialog("暂时查询不到数据，\n请稍后重试",
						ContentActivity.this);
				return;
			}
			String title = newsContentMessage.getDoc().getTitle();
     		title =title.replaceAll("<em>","<font color=red>");
     		title =title.replaceAll("</em>","</font>");
			titleView.setText(Html.fromHtml(title));
			fromView.setText("来源:"+newsContentMessage.getDoc().getCopyFrom());
			authorView.setText("作者:"+newsContentMessage.getDoc().getAuthor());
			String timeStamp=newsContentMessage.getDoc().getPublishDate();
			String time = FunctionUtils.timesOne(timeStamp);
			//Log.d("++++++++++++++++++++++++", time);
			dateView.setText(time);
			
			
			String context =  newsContentMessage.getDoc().getContent();
			/*Matcher contextMatcher = FunctionUtils.getMatcher(context, keyWordsBlock);
			while(contextMatcher.find()){*/
			context=context.replaceAll("<em>","<font color=red>");
			context=context.replaceAll("</em>","</font>");
			//}
			
					
			//Log.d("++++++++++++",context);		
			contentView.setText(Html.fromHtml("\t\t\t\t"+context));
			
			url =newsContentMessage.getDoc().getUrl();
			
			
			
		}
		
	}

	@Override
	public void onBackPressed() {
		webView.setVisibility(View.INVISIBLE);
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		webView.onPause();

	}

	@Override
	protected void onResume() {
		super.onResume();
		webView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		  case R.id.content_back:
			  finish();
			  overridePendingTransition(0, R.anim.base_slide_right_out);
				return;
		  case R.id.content_linkto:
			  Intent intent =new Intent(Intent.ACTION_VIEW);
			  intent.setData(Uri.parse(doc.getUrl()));
			  startActivity(intent);
		      break;
		}
		
	}

}
