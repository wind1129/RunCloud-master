package com.example.runcloud.entity;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Doc extends RealmObject {
	private String docId;
	private String url;
	private String topDomain;
	private String subDomain;
	private String siteId;
	private String siteName;
	private String channelId;
	private String channelName;
	private String mediaType;
	private String subMediaType;
	private String trdMediaType;
	private String contentType;
	private String postUrl;
	private String postDocId;
	private String replyFloor;
	private String title;
	private String content;
	private String publishDate;
	private String author;
	private String replyNum;
	private String clickNum;
	private String copyFrom;
	private String isOversea;
	private String imageUrls;
	private String dataSource;
	private String lastModifyDate;
	private String fetchDate;
	private String importDate;
	private String isGarbage;
	private String vectorWords;
	private String summary;
	private String sameDocId;
	private String similarDocId;
	private String negativeStatus;
	private String negativeWords;
	private String districtMarks;
	private String realmMarks;
	private String provinceName;
	private String repostNum;
	private String imagePaths;
	private String profileUrl;
	private String userUrl;
	private String verified;
	private String nickName;
	private String providerId;
	private String userAccount;
	private String userId;
	private String srcAccount;
	private String srcUserId;
	private String srcNickName;
	private String srcProfileUrl;
	private String srcUserUrl;
	private String srcWeiboId;
	private String srcContent;
	private String srcPublishDate;
	private String srcRepostNum;
	private String srcCommentNum;
	private String srcUrl;
	private String srcPicUrls;
	private String srcPicPaths;
	private String dealStatus;
	
	
	
	


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
	public String getTopDomain() {
		return topDomain;
	}
	public void setTopDomain(String topDomain) {
		this.topDomain = topDomain;
	}
	public String getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	public String getSubMediaType() {
		return subMediaType;
	}
	public void setSubMediaType(String subMediaType) {
		this.subMediaType = subMediaType;
	}
	public String getTrdMediaType() {
		return trdMediaType;
	}
	public void setTrdMediaType(String trdMediaType) {
		this.trdMediaType = trdMediaType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getPostDocId() {
		return postDocId;
	}
	public void setPostDocId(String postDocId) {
		this.postDocId = postDocId;
	}
	public String getReplyFloor() {
		return replyFloor;
	}
	public void setReplyFloor(String replyFloor) {
		this.replyFloor = replyFloor;
	}
	public String getTitle() {
		if(title==null){
			return "没有获取到标题";
		}else {
			return title;
		}
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		if(content==null){
			return "没有获取到内容";
		}else {
			return content;
		}
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
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getFetchDate() {
		return fetchDate;
	}
	public void setFetchDate(String fetchDate) {
		this.fetchDate = fetchDate;
	}
	public String getImportDate() {
		return importDate;
	}
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}
	public String getIsGarbage() {
		return isGarbage;
	}
	public void setIsGarbage(String isGarbage) {
		this.isGarbage = isGarbage;
	}
	public String getVectorWords() {
		return vectorWords;
	}
	public void setVectorWords(String vectorWords) {
		this.vectorWords = vectorWords;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSameDocId() {
		return sameDocId;
	}
	public void setSameDocId(String sameDocId) {
		this.sameDocId = sameDocId;
	}
	public String getSimilarDocId() {
		return similarDocId;
	}
	public void setSimilarDocId(String similarDocId) {
		this.similarDocId = similarDocId;
	}
	public String getNegativeStatus() {
		return negativeStatus;
	}
	public void setNegativeStatus(String negativeStatus) {
		this.negativeStatus = negativeStatus;
	}
	public String getNegativeWords() {
		return negativeWords;
	}
	public void setNegativeWords(String negativeWords) {
		this.negativeWords = negativeWords;
	}
	

	public String getDistrictMarks() {
		return districtMarks;
	}
	public void setDistrictMarks(String districtMarks) {
		this.districtMarks = districtMarks;
	}
	public String getRealmMarks() {
		return realmMarks;
	}
	public void setRealmMarks(String realmMarks) {
		this.realmMarks = realmMarks;
	}
	

	
	

	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getRepostNum() {
		return repostNum;
	}
	public void setRepostNum(String repostNum) {
		this.repostNum = repostNum;
	}
	public String getImagePaths() {
		return imagePaths;
	}
	public void setImagePaths(String imagePaths) {
		this.imagePaths = imagePaths;
	}
	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
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
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSrcAccount() {
		return srcAccount;
	}
	public void setSrcAccount(String srcAccount) {
		this.srcAccount = srcAccount;
	}
	public String getSrcUserId() {
		return srcUserId;
	}
	public void setSrcUserId(String srcUserId) {
		this.srcUserId = srcUserId;
	}
	public String getSrcNickName() {
		return srcNickName;
	}
	public void setSrcNickName(String srcNickName) {
		this.srcNickName = srcNickName;
	}
	public String getSrcProfileUrl() {
		return srcProfileUrl;
	}
	public void setSrcProfileUrl(String srcProfileUrl) {
		this.srcProfileUrl = srcProfileUrl;
	}
	public String getSrcUserUrl() {
		return srcUserUrl;
	}
	public void setSrcUserUrl(String srcUserUrl) {
		this.srcUserUrl = srcUserUrl;
	}
	public String getSrcWeiboId() {
		return srcWeiboId;
	}
	public void setSrcWeiboId(String srcWeiboId) {
		this.srcWeiboId = srcWeiboId;
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
	public String getSrcUrl() {
		return srcUrl;
	}
	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
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
	
	public String getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	
	
}
