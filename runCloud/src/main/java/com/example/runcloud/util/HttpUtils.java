package com.example.runcloud.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import android.util.Log;

import com.example.runcloud.entity.AbroadMessage;
import com.example.runcloud.entity.AbroadSiteGroups;
import com.example.runcloud.entity.AbroadSites;
import com.example.runcloud.entity.WechatMessage;
import com.example.runcloud.entity.HttpMessage;
import com.example.runcloud.entity.NetizenMessage;
import com.example.runcloud.entity.NetizenShow;
import com.example.runcloud.entity.Netizens;
import com.example.runcloud.entity.NewsContentMessage;
import com.example.runcloud.entity.SearchMessage;
import com.example.runcloud.entity.SensRuleShow;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.entity.Sensrules;
import com.example.runcloud.entity.StatisticalCharts;
import com.example.runcloud.entity.UserInformation;
import com.google.gson.Gson;


public class HttpUtils {
	/**通过HttpGet请求访问互联网获取页面数据*/
	public static String getJsonStr(String url) {
		HttpGet get = new HttpGet(url);
		//HttpClient client = new DefaultHttpClient();
		HttpClient client = HttpClientTool.getHttpClient();
		HttpResponse response;
		try {
			get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			get.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
			response = client.execute(get);
			//Log.d("+++++++", response.getStatusLine().getStatusCode()+"");
			if (response.getStatusLine().getStatusCode() == 200) {
				InputStream in = response.getEntity().getContent();
                
				return getResult(in);
			}
		} catch (ClientProtocolException e) {		
			e.printStackTrace();
			Log.d("ClientProtocolException", e.getMessage());
		} catch (IOException e) {
			Log.d("IOException e", e.getMessage());
		}
		return null;
	}
	
	
	/**通过Httpdelete请求访问互联网获取页面数据*/
	public static int doDelete(String url) {
		HttpDelete delete = new HttpDelete(url);
		//HttpClient client = new DefaultHttpClient();
		HttpClient client = HttpClientTool.getHttpClient();
		HttpResponse response;
		try {
			delete.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			delete.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
			response = client.execute(delete);
			return response.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**通过Httppost请求访问互联网获取页面数据*/
	public static HttpResponse doPost(String url,JSONObject param) {
		HttpPost httpPost = new HttpPost(url);
		StringEntity se;
	try {
		se = new StringEntity(param.toString(), "utf-8");
		httpPost.setEntity(se);
		//HttpClient client = new DefaultHttpClient();
		HttpClient client = HttpClientTool.getHttpClient();
		HttpResponse response;
		httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		response = client.execute(httpPost);
		//HttpEntity entity = response.getEntity();
		//res = EntityUtils.toString(entity, "UTF-8");
		//Log.d("RESULT", res);
		//return response.getStatusLine().getStatusCode();
		return response;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String getResult(InputStream in) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		try {
			while ((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
				bos.flush();
			}
			// System.out.println(new String(bos.toByteArray(), "utf-8"));
			return new String(bos.toByteArray(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//解析json串
	/*public static WechatMessage fromJson(String jsonStr) {
		try {
			JSONObject obj = new JSONObject(jsonStr);
			String resultCode = obj.getString("resultCode");
			if(resultCode.equals("000000")){
				WechatMessage datasMessage = new WechatMessage();
				datasMessage.setResultCode(resultCode);
				List<DataList> dataLists = new ArrayList<DataList>();
				JSONArray rArr = obj.getJSONArray("wechatDataList");
				for (int i = 0; i < rArr.length(); i++) {
					JSONObject rObj = rArr.getJSONObject(i);
					DataList dataList = new DataList();
					dataList.setDocId(rObj.getString("docId"));
					dataList.setUrl(rObj.getString("url"));
					dataList.setSiteName(rObj.getString("siteName"));
					dataList.setChannelName(rObj.getString("channelName"));
					dataList.setMediaType(rObj.getString("mediaType"));
					dataList.setTitle(rObj.getString("title"));
					dataList.setContent(rObj.getString("content"));
					dataList.setPublishDate(rObj.getString("publishDate"));
					dataList.setAuthor(rObj.getString("author"));
					dataList.setReplyNum(rObj.getString("replyNum"));
					dataList.setClickNum(rObj.getString("clickNum"));
					dataList.setCopyFrom(rObj.getString("copyFrom"));
					dataList.setIsAbroad(rObj.getString("isAbroad"));
					dataList.setIsOversea(rObj.getString("isOversea"));
					dataList.setImageUrls(rObj.getString("imageUrls"));
					dataList.setSummary(rObj.getString("summary"));
					dataList.setNegativeStatus(rObj.getString("negativeStatus"));
					dataList.setRepostNum(rObj.getString("repostNum"));
					dataList.setProfileUrl(rObj.getString("profileUrl"));
					dataList.setNickName(rObj.getString("nickName"));
					dataList.setSrcContent(rObj.getString("srcContent"));
					dataList.setSrcPublishDate(rObj.getString("srcPublishDate"));
					dataList.setSrcRepostNum(rObj.getString("srcRepostNum"));
					dataList.setSrcCommentNum(rObj.getString("srcCommentNum"));
					dataList.setSrcPicUrls(rObj.getString("srcPicUrls"));
					dataList.setSrcPicPaths(rObj.getString("srcPicPaths"));
					dataList.setKeyWords(rObj.getString("keyWords"));
					dataList.setLogo(rObj.getString("logo"));
					dataLists.add(dataList);
				}
				//datasMessage.setDatalists(dataLists);
				datasMessage.setWechatDataList(dataLists);
				return datasMessage;
			
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**微信解析*/
	public static WechatMessage DatasMessageFromJson(String jsonStr) {
		Gson gson = new Gson();
		WechatMessage message = gson.fromJson(jsonStr, WechatMessage.class);
		return message;
	}
	
	/**舆情解析*/
	public static SensitiveMessage SensitiveMessageFromJson(String jsonStr) {
		Gson gson = new Gson();
		SensitiveMessage message = gson.fromJson(jsonStr, SensitiveMessage.class);
		return message;
	}
	/**网名解析*/
	public static NetizenMessage NetizenMessageFromJson(String jsonStr) {
		Gson gson = new Gson();
		NetizenMessage message = gson.fromJson(jsonStr, NetizenMessage.class);
		return message;
	}
	/**境外解析*/
	public static AbroadMessage AbroadMessageFromJson(String jsonStr) {
		Gson gson = new Gson();
		AbroadMessage message = gson.fromJson(jsonStr, AbroadMessage.class);
		return message;
	}
	/**快速查询解析*/
	public static SearchMessage SearchMessageFromJson(String jsonStr) {
		Gson gson = new Gson();
		SearchMessage message = gson.fromJson(jsonStr, SearchMessage.class);
		return message;
	}
	/**用户信息解析*/
	public static UserInformation UserInformationFromJson(String jsonStr) {
		Gson gson = new Gson();
		UserInformation message = gson.fromJson(jsonStr, UserInformation.class);
		return message;
	}
	
	
	/**新闻内容Json转换*/
	public static NewsContentMessage NewsContentsFromJson(String jsonStr){
		Gson gson = new Gson();
		NewsContentMessage message = gson.fromJson(jsonStr, NewsContentMessage.class);
		return message;
		
		
	}
	
	
	/**图表Json转换*/
	public static StatisticalCharts StatisticalChartsFromJson(String jsonStr){
		Gson gson = new Gson();
		StatisticalCharts charts = gson.fromJson(jsonStr, StatisticalCharts.class);
		return charts;
		
	}
	
	
	/**重点关注网民Json转换*/
	public static Netizens NetizensFromJson(String jsonStr){
		Gson gson = new Gson();
		Netizens netizens = gson.fromJson(jsonStr, Netizens.class);
		return netizens;
		
	}
	
	
	/**重点关注规则Json转换*/
	public static Sensrules SensrulesFromJson(String jsonStr){
		Gson gson = new Gson();
		Sensrules sensrules = gson.fromJson(jsonStr, Sensrules.class);
		return sensrules;
		
	}
	
	
	/**境外网站分组Json转换*/
	public static AbroadSiteGroups AbroadSiteGroupsFromJson(String jsonStr){
		Gson gson = new Gson();
		AbroadSiteGroups abroadSiteGroups = gson.fromJson(jsonStr, AbroadSiteGroups.class);
		return abroadSiteGroups;
		
	}
	
	/**具体网名信息回显Json转换*/
	public static NetizenShow NetizenShowFromJson(String jsonStr){
		Gson gson = new Gson();
		NetizenShow netizenShow = gson.fromJson(jsonStr, NetizenShow.class);
		return netizenShow;
		
	}
	
	
	/**具体规则信息回显Json转换*/
	public static SensRuleShow SensRuleShowFromJson(String jsonStr){
		Gson gson = new Gson();
		SensRuleShow sensRuleShow = gson.fromJson(jsonStr, SensRuleShow.class);
		return sensRuleShow;
		
	}
	
	
	/**具体AbroadSites站点Json转换*/
	public static AbroadSites AbroadSitesFromJson(String jsonStr){
		Gson gson = new Gson();
		AbroadSites abroadSites = gson.fromJson(jsonStr, AbroadSites.class);
		return abroadSites;
		
	}
	
	
	/**网络反馈的HttpMessage信息Json转换*/
	public static HttpMessage HttpMessageFromJson(String jsonStr){
		Gson gson = new Gson();
		HttpMessage httpMessage = gson.fromJson(jsonStr, HttpMessage.class);
		return httpMessage;
		
	}
	
	
	
	
	
	

}
