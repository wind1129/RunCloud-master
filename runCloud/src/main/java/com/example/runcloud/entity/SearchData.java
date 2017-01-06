package com.example.runcloud.entity;

import io.realm.RealmObject;
import com.example.runcloud.mvp.other.DataContent;

/**
 * （舆情）微信的列表页单条信息
 */

public class SearchData extends RealmObject implements DataContent {
	private String docId;
	private String url;
	private String siteName;
	private String channelName;
	private String mediaType;
	private String title;
	private String content;
	private String publishDate;
	private String author;
	private String replyNum;
	private String clickNum;
	private String copyFrom;
	private String isAbroad;
	private String isOversea;
	private String imageUrls;
	private String summary;
	private int negativeStatus;
	private String repostNum;
	private String profileUrl;
	private String nickName;
	private String providerId;
	private String srcNickName;
	private String srcContent;
	private String srcPublishDate;
	private String srcRepostNum;
	private String srcCommentNum;
	private String srcPicUrls;
	private String srcPicPaths;
	private String keywords;
	//private RealmList<RealmString> keyWords;
	private String logo;
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}
	public String getClickNum() {
		return clickNum;
	}
	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
	}
	public String getCopyFrom() {
		return copyFrom;
	}
	public void setCopyFrom(String copyFrom) {
		this.copyFrom = copyFrom;
	}
	public String getIsAbroad() {
		return isAbroad;
	}
	public void setIsAbroad(String isAbroad) {
		this.isAbroad = isAbroad;
	}
	public String getIsOversea() {
		return isOversea;
	}
	public void setIsOversea(String isOversea) {
		this.isOversea = isOversea;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/*public String getNegativeStatus() {
		return negativeStatus;
	}
	public void setNegativeStatus(String negativeStatus) {
		this.negativeStatus = negativeStatus;
	}*/

	public int getNegativeStatus() {
		return negativeStatus;
	}

	public void setNegativeStatus(int negativeStatus) {
		this.negativeStatus = negativeStatus;
	}

	public String getRepostNum() {
		return repostNum;
	}
	public void setRepostNum(String repostNum) {
		this.repostNum = repostNum;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getSrcNickName() {
		return srcNickName;
	}
	public void setSrcNickName(String srcNickName) {
		this.srcNickName = srcNickName;
	}
	public String getSrcContent() {
		return srcContent;
	}
	public void setSrcContent(String srcContent) {
		this.srcContent = srcContent;
	}
	public String getSrcPublishDate() {
		return srcPublishDate;
	}
	public void setSrcPublishDate(String srcPublishDate) {
		this.srcPublishDate = srcPublishDate;
	}
	public String getSrcRepostNum() {
		return srcRepostNum;
	}
	public void setSrcRepostNum(String srcRepostNum) {
		this.srcRepostNum = srcRepostNum;
	}
	public String getSrcCommentNum() {
		return srcCommentNum;
	}
	public void setSrcCommentNum(String srcCommentNum) {
		this.srcCommentNum = srcCommentNum;
	}
	public String getSrcPicUrls() {
		return srcPicUrls;
	}
	public void setSrcPicUrls(String srcPicUrls) {
		this.srcPicUrls = srcPicUrls;
	}
	public String getSrcPicPaths() {
		return srcPicPaths;
	}
	public void setSrcPicPaths(String srcPicPaths) {
		this.srcPicPaths = srcPicPaths;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
