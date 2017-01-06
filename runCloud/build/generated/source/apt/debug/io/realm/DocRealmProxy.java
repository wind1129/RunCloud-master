package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.Doc;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocRealmProxy extends Doc
    implements RealmObjectProxy {

    static final class DocColumnInfo extends ColumnInfo {

        public final long docIdIndex;
        public final long urlIndex;
        public final long topDomainIndex;
        public final long subDomainIndex;
        public final long siteIdIndex;
        public final long siteNameIndex;
        public final long channelIdIndex;
        public final long channelNameIndex;
        public final long mediaTypeIndex;
        public final long subMediaTypeIndex;
        public final long trdMediaTypeIndex;
        public final long contentTypeIndex;
        public final long postUrlIndex;
        public final long postDocIdIndex;
        public final long replyFloorIndex;
        public final long titleIndex;
        public final long contentIndex;
        public final long publishDateIndex;
        public final long authorIndex;
        public final long replyNumIndex;
        public final long clickNumIndex;
        public final long copyFromIndex;
        public final long isOverseaIndex;
        public final long imageUrlsIndex;
        public final long dataSourceIndex;
        public final long lastModifyDateIndex;
        public final long fetchDateIndex;
        public final long importDateIndex;
        public final long isGarbageIndex;
        public final long vectorWordsIndex;
        public final long summaryIndex;
        public final long sameDocIdIndex;
        public final long similarDocIdIndex;
        public final long negativeStatusIndex;
        public final long negativeWordsIndex;
        public final long districtMarksIndex;
        public final long realmMarksIndex;
        public final long provinceNameIndex;
        public final long repostNumIndex;
        public final long imagePathsIndex;
        public final long profileUrlIndex;
        public final long userUrlIndex;
        public final long verifiedIndex;
        public final long nickNameIndex;
        public final long providerIdIndex;
        public final long userAccountIndex;
        public final long userIdIndex;
        public final long srcAccountIndex;
        public final long srcUserIdIndex;
        public final long srcNickNameIndex;
        public final long srcProfileUrlIndex;
        public final long srcUserUrlIndex;
        public final long srcWeiboIdIndex;
        public final long srcContentIndex;
        public final long srcPublishDateIndex;
        public final long srcRepostNumIndex;
        public final long srcCommentNumIndex;
        public final long srcUrlIndex;
        public final long srcPicUrlsIndex;
        public final long srcPicPathsIndex;
        public final long dealStatusIndex;

        DocColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(61);
            this.docIdIndex = getValidColumnIndex(path, table, "Doc", "docId");
            indicesMap.put("docId", this.docIdIndex);

            this.urlIndex = getValidColumnIndex(path, table, "Doc", "url");
            indicesMap.put("url", this.urlIndex);

            this.topDomainIndex = getValidColumnIndex(path, table, "Doc", "topDomain");
            indicesMap.put("topDomain", this.topDomainIndex);

            this.subDomainIndex = getValidColumnIndex(path, table, "Doc", "subDomain");
            indicesMap.put("subDomain", this.subDomainIndex);

            this.siteIdIndex = getValidColumnIndex(path, table, "Doc", "siteId");
            indicesMap.put("siteId", this.siteIdIndex);

            this.siteNameIndex = getValidColumnIndex(path, table, "Doc", "siteName");
            indicesMap.put("siteName", this.siteNameIndex);

            this.channelIdIndex = getValidColumnIndex(path, table, "Doc", "channelId");
            indicesMap.put("channelId", this.channelIdIndex);

            this.channelNameIndex = getValidColumnIndex(path, table, "Doc", "channelName");
            indicesMap.put("channelName", this.channelNameIndex);

            this.mediaTypeIndex = getValidColumnIndex(path, table, "Doc", "mediaType");
            indicesMap.put("mediaType", this.mediaTypeIndex);

            this.subMediaTypeIndex = getValidColumnIndex(path, table, "Doc", "subMediaType");
            indicesMap.put("subMediaType", this.subMediaTypeIndex);

            this.trdMediaTypeIndex = getValidColumnIndex(path, table, "Doc", "trdMediaType");
            indicesMap.put("trdMediaType", this.trdMediaTypeIndex);

            this.contentTypeIndex = getValidColumnIndex(path, table, "Doc", "contentType");
            indicesMap.put("contentType", this.contentTypeIndex);

            this.postUrlIndex = getValidColumnIndex(path, table, "Doc", "postUrl");
            indicesMap.put("postUrl", this.postUrlIndex);

            this.postDocIdIndex = getValidColumnIndex(path, table, "Doc", "postDocId");
            indicesMap.put("postDocId", this.postDocIdIndex);

            this.replyFloorIndex = getValidColumnIndex(path, table, "Doc", "replyFloor");
            indicesMap.put("replyFloor", this.replyFloorIndex);

            this.titleIndex = getValidColumnIndex(path, table, "Doc", "title");
            indicesMap.put("title", this.titleIndex);

            this.contentIndex = getValidColumnIndex(path, table, "Doc", "content");
            indicesMap.put("content", this.contentIndex);

            this.publishDateIndex = getValidColumnIndex(path, table, "Doc", "publishDate");
            indicesMap.put("publishDate", this.publishDateIndex);

            this.authorIndex = getValidColumnIndex(path, table, "Doc", "author");
            indicesMap.put("author", this.authorIndex);

            this.replyNumIndex = getValidColumnIndex(path, table, "Doc", "replyNum");
            indicesMap.put("replyNum", this.replyNumIndex);

            this.clickNumIndex = getValidColumnIndex(path, table, "Doc", "clickNum");
            indicesMap.put("clickNum", this.clickNumIndex);

            this.copyFromIndex = getValidColumnIndex(path, table, "Doc", "copyFrom");
            indicesMap.put("copyFrom", this.copyFromIndex);

            this.isOverseaIndex = getValidColumnIndex(path, table, "Doc", "isOversea");
            indicesMap.put("isOversea", this.isOverseaIndex);

            this.imageUrlsIndex = getValidColumnIndex(path, table, "Doc", "imageUrls");
            indicesMap.put("imageUrls", this.imageUrlsIndex);

            this.dataSourceIndex = getValidColumnIndex(path, table, "Doc", "dataSource");
            indicesMap.put("dataSource", this.dataSourceIndex);

            this.lastModifyDateIndex = getValidColumnIndex(path, table, "Doc", "lastModifyDate");
            indicesMap.put("lastModifyDate", this.lastModifyDateIndex);

            this.fetchDateIndex = getValidColumnIndex(path, table, "Doc", "fetchDate");
            indicesMap.put("fetchDate", this.fetchDateIndex);

            this.importDateIndex = getValidColumnIndex(path, table, "Doc", "importDate");
            indicesMap.put("importDate", this.importDateIndex);

            this.isGarbageIndex = getValidColumnIndex(path, table, "Doc", "isGarbage");
            indicesMap.put("isGarbage", this.isGarbageIndex);

            this.vectorWordsIndex = getValidColumnIndex(path, table, "Doc", "vectorWords");
            indicesMap.put("vectorWords", this.vectorWordsIndex);

            this.summaryIndex = getValidColumnIndex(path, table, "Doc", "summary");
            indicesMap.put("summary", this.summaryIndex);

            this.sameDocIdIndex = getValidColumnIndex(path, table, "Doc", "sameDocId");
            indicesMap.put("sameDocId", this.sameDocIdIndex);

            this.similarDocIdIndex = getValidColumnIndex(path, table, "Doc", "similarDocId");
            indicesMap.put("similarDocId", this.similarDocIdIndex);

            this.negativeStatusIndex = getValidColumnIndex(path, table, "Doc", "negativeStatus");
            indicesMap.put("negativeStatus", this.negativeStatusIndex);

            this.negativeWordsIndex = getValidColumnIndex(path, table, "Doc", "negativeWords");
            indicesMap.put("negativeWords", this.negativeWordsIndex);

            this.districtMarksIndex = getValidColumnIndex(path, table, "Doc", "districtMarks");
            indicesMap.put("districtMarks", this.districtMarksIndex);

            this.realmMarksIndex = getValidColumnIndex(path, table, "Doc", "realmMarks");
            indicesMap.put("realmMarks", this.realmMarksIndex);

            this.provinceNameIndex = getValidColumnIndex(path, table, "Doc", "provinceName");
            indicesMap.put("provinceName", this.provinceNameIndex);

            this.repostNumIndex = getValidColumnIndex(path, table, "Doc", "repostNum");
            indicesMap.put("repostNum", this.repostNumIndex);

            this.imagePathsIndex = getValidColumnIndex(path, table, "Doc", "imagePaths");
            indicesMap.put("imagePaths", this.imagePathsIndex);

            this.profileUrlIndex = getValidColumnIndex(path, table, "Doc", "profileUrl");
            indicesMap.put("profileUrl", this.profileUrlIndex);

            this.userUrlIndex = getValidColumnIndex(path, table, "Doc", "userUrl");
            indicesMap.put("userUrl", this.userUrlIndex);

            this.verifiedIndex = getValidColumnIndex(path, table, "Doc", "verified");
            indicesMap.put("verified", this.verifiedIndex);

            this.nickNameIndex = getValidColumnIndex(path, table, "Doc", "nickName");
            indicesMap.put("nickName", this.nickNameIndex);

            this.providerIdIndex = getValidColumnIndex(path, table, "Doc", "providerId");
            indicesMap.put("providerId", this.providerIdIndex);

            this.userAccountIndex = getValidColumnIndex(path, table, "Doc", "userAccount");
            indicesMap.put("userAccount", this.userAccountIndex);

            this.userIdIndex = getValidColumnIndex(path, table, "Doc", "userId");
            indicesMap.put("userId", this.userIdIndex);

            this.srcAccountIndex = getValidColumnIndex(path, table, "Doc", "srcAccount");
            indicesMap.put("srcAccount", this.srcAccountIndex);

            this.srcUserIdIndex = getValidColumnIndex(path, table, "Doc", "srcUserId");
            indicesMap.put("srcUserId", this.srcUserIdIndex);

            this.srcNickNameIndex = getValidColumnIndex(path, table, "Doc", "srcNickName");
            indicesMap.put("srcNickName", this.srcNickNameIndex);

            this.srcProfileUrlIndex = getValidColumnIndex(path, table, "Doc", "srcProfileUrl");
            indicesMap.put("srcProfileUrl", this.srcProfileUrlIndex);

            this.srcUserUrlIndex = getValidColumnIndex(path, table, "Doc", "srcUserUrl");
            indicesMap.put("srcUserUrl", this.srcUserUrlIndex);

            this.srcWeiboIdIndex = getValidColumnIndex(path, table, "Doc", "srcWeiboId");
            indicesMap.put("srcWeiboId", this.srcWeiboIdIndex);

            this.srcContentIndex = getValidColumnIndex(path, table, "Doc", "srcContent");
            indicesMap.put("srcContent", this.srcContentIndex);

            this.srcPublishDateIndex = getValidColumnIndex(path, table, "Doc", "srcPublishDate");
            indicesMap.put("srcPublishDate", this.srcPublishDateIndex);

            this.srcRepostNumIndex = getValidColumnIndex(path, table, "Doc", "srcRepostNum");
            indicesMap.put("srcRepostNum", this.srcRepostNumIndex);

            this.srcCommentNumIndex = getValidColumnIndex(path, table, "Doc", "srcCommentNum");
            indicesMap.put("srcCommentNum", this.srcCommentNumIndex);

            this.srcUrlIndex = getValidColumnIndex(path, table, "Doc", "srcUrl");
            indicesMap.put("srcUrl", this.srcUrlIndex);

            this.srcPicUrlsIndex = getValidColumnIndex(path, table, "Doc", "srcPicUrls");
            indicesMap.put("srcPicUrls", this.srcPicUrlsIndex);

            this.srcPicPathsIndex = getValidColumnIndex(path, table, "Doc", "srcPicPaths");
            indicesMap.put("srcPicPaths", this.srcPicPathsIndex);

            this.dealStatusIndex = getValidColumnIndex(path, table, "Doc", "dealStatus");
            indicesMap.put("dealStatus", this.dealStatusIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DocColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("docId");
        fieldNames.add("url");
        fieldNames.add("topDomain");
        fieldNames.add("subDomain");
        fieldNames.add("siteId");
        fieldNames.add("siteName");
        fieldNames.add("channelId");
        fieldNames.add("channelName");
        fieldNames.add("mediaType");
        fieldNames.add("subMediaType");
        fieldNames.add("trdMediaType");
        fieldNames.add("contentType");
        fieldNames.add("postUrl");
        fieldNames.add("postDocId");
        fieldNames.add("replyFloor");
        fieldNames.add("title");
        fieldNames.add("content");
        fieldNames.add("publishDate");
        fieldNames.add("author");
        fieldNames.add("replyNum");
        fieldNames.add("clickNum");
        fieldNames.add("copyFrom");
        fieldNames.add("isOversea");
        fieldNames.add("imageUrls");
        fieldNames.add("dataSource");
        fieldNames.add("lastModifyDate");
        fieldNames.add("fetchDate");
        fieldNames.add("importDate");
        fieldNames.add("isGarbage");
        fieldNames.add("vectorWords");
        fieldNames.add("summary");
        fieldNames.add("sameDocId");
        fieldNames.add("similarDocId");
        fieldNames.add("negativeStatus");
        fieldNames.add("negativeWords");
        fieldNames.add("districtMarks");
        fieldNames.add("realmMarks");
        fieldNames.add("provinceName");
        fieldNames.add("repostNum");
        fieldNames.add("imagePaths");
        fieldNames.add("profileUrl");
        fieldNames.add("userUrl");
        fieldNames.add("verified");
        fieldNames.add("nickName");
        fieldNames.add("providerId");
        fieldNames.add("userAccount");
        fieldNames.add("userId");
        fieldNames.add("srcAccount");
        fieldNames.add("srcUserId");
        fieldNames.add("srcNickName");
        fieldNames.add("srcProfileUrl");
        fieldNames.add("srcUserUrl");
        fieldNames.add("srcWeiboId");
        fieldNames.add("srcContent");
        fieldNames.add("srcPublishDate");
        fieldNames.add("srcRepostNum");
        fieldNames.add("srcCommentNum");
        fieldNames.add("srcUrl");
        fieldNames.add("srcPicUrls");
        fieldNames.add("srcPicPaths");
        fieldNames.add("dealStatus");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DocRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DocColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public String getDocId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.docIdIndex);
    }

    @Override
    public void setDocId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.docIdIndex);
            return;
        }
        row.setString(columnInfo.docIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.urlIndex);
    }

    @Override
    public void setUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.urlIndex);
            return;
        }
        row.setString(columnInfo.urlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTopDomain() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.topDomainIndex);
    }

    @Override
    public void setTopDomain(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.topDomainIndex);
            return;
        }
        row.setString(columnInfo.topDomainIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSubDomain() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.subDomainIndex);
    }

    @Override
    public void setSubDomain(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.subDomainIndex);
            return;
        }
        row.setString(columnInfo.subDomainIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSiteId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.siteIdIndex);
    }

    @Override
    public void setSiteId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.siteIdIndex);
            return;
        }
        row.setString(columnInfo.siteIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSiteName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.siteNameIndex);
    }

    @Override
    public void setSiteName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.siteNameIndex);
            return;
        }
        row.setString(columnInfo.siteNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getChannelId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.channelIdIndex);
    }

    @Override
    public void setChannelId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.channelIdIndex);
            return;
        }
        row.setString(columnInfo.channelIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getChannelName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.channelNameIndex);
    }

    @Override
    public void setChannelName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.channelNameIndex);
            return;
        }
        row.setString(columnInfo.channelNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getMediaType() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.mediaTypeIndex);
    }

    @Override
    public void setMediaType(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.mediaTypeIndex);
            return;
        }
        row.setString(columnInfo.mediaTypeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSubMediaType() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.subMediaTypeIndex);
    }

    @Override
    public void setSubMediaType(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.subMediaTypeIndex);
            return;
        }
        row.setString(columnInfo.subMediaTypeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTrdMediaType() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.trdMediaTypeIndex);
    }

    @Override
    public void setTrdMediaType(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.trdMediaTypeIndex);
            return;
        }
        row.setString(columnInfo.trdMediaTypeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getContentType() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.contentTypeIndex);
    }

    @Override
    public void setContentType(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.contentTypeIndex);
            return;
        }
        row.setString(columnInfo.contentTypeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPostUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.postUrlIndex);
    }

    @Override
    public void setPostUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.postUrlIndex);
            return;
        }
        row.setString(columnInfo.postUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPostDocId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.postDocIdIndex);
    }

    @Override
    public void setPostDocId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.postDocIdIndex);
            return;
        }
        row.setString(columnInfo.postDocIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getReplyFloor() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.replyFloorIndex);
    }

    @Override
    public void setReplyFloor(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.replyFloorIndex);
            return;
        }
        row.setString(columnInfo.replyFloorIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTitle() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.titleIndex);
    }

    @Override
    public void setTitle(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.titleIndex);
            return;
        }
        row.setString(columnInfo.titleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getContent() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.contentIndex);
    }

    @Override
    public void setContent(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.contentIndex);
            return;
        }
        row.setString(columnInfo.contentIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPublishDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.publishDateIndex);
    }

    @Override
    public void setPublishDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.publishDateIndex);
            return;
        }
        row.setString(columnInfo.publishDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getAuthor() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.authorIndex);
    }

    @Override
    public void setAuthor(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.authorIndex);
            return;
        }
        row.setString(columnInfo.authorIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getReplyNum() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.replyNumIndex);
    }

    @Override
    public void setReplyNum(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.replyNumIndex);
            return;
        }
        row.setString(columnInfo.replyNumIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getClickNum() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.clickNumIndex);
    }

    @Override
    public void setClickNum(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.clickNumIndex);
            return;
        }
        row.setString(columnInfo.clickNumIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getCopyFrom() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.copyFromIndex);
    }

    @Override
    public void setCopyFrom(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.copyFromIndex);
            return;
        }
        row.setString(columnInfo.copyFromIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getIsOversea() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.isOverseaIndex);
    }

    @Override
    public void setIsOversea(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.isOverseaIndex);
            return;
        }
        row.setString(columnInfo.isOverseaIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getImageUrls() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.imageUrlsIndex);
    }

    @Override
    public void setImageUrls(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.imageUrlsIndex);
            return;
        }
        row.setString(columnInfo.imageUrlsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getDataSource() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.dataSourceIndex);
    }

    @Override
    public void setDataSource(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.dataSourceIndex);
            return;
        }
        row.setString(columnInfo.dataSourceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getLastModifyDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.lastModifyDateIndex);
    }

    @Override
    public void setLastModifyDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.lastModifyDateIndex);
            return;
        }
        row.setString(columnInfo.lastModifyDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getFetchDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.fetchDateIndex);
    }

    @Override
    public void setFetchDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.fetchDateIndex);
            return;
        }
        row.setString(columnInfo.fetchDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getImportDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.importDateIndex);
    }

    @Override
    public void setImportDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.importDateIndex);
            return;
        }
        row.setString(columnInfo.importDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getIsGarbage() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.isGarbageIndex);
    }

    @Override
    public void setIsGarbage(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.isGarbageIndex);
            return;
        }
        row.setString(columnInfo.isGarbageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getVectorWords() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.vectorWordsIndex);
    }

    @Override
    public void setVectorWords(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.vectorWordsIndex);
            return;
        }
        row.setString(columnInfo.vectorWordsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSummary() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.summaryIndex);
    }

    @Override
    public void setSummary(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.summaryIndex);
            return;
        }
        row.setString(columnInfo.summaryIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSameDocId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.sameDocIdIndex);
    }

    @Override
    public void setSameDocId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.sameDocIdIndex);
            return;
        }
        row.setString(columnInfo.sameDocIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSimilarDocId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.similarDocIdIndex);
    }

    @Override
    public void setSimilarDocId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.similarDocIdIndex);
            return;
        }
        row.setString(columnInfo.similarDocIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getNegativeStatus() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.negativeStatusIndex);
    }

    @Override
    public void setNegativeStatus(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.negativeStatusIndex);
            return;
        }
        row.setString(columnInfo.negativeStatusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getNegativeWords() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.negativeWordsIndex);
    }

    @Override
    public void setNegativeWords(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.negativeWordsIndex);
            return;
        }
        row.setString(columnInfo.negativeWordsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getDistrictMarks() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.districtMarksIndex);
    }

    @Override
    public void setDistrictMarks(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.districtMarksIndex);
            return;
        }
        row.setString(columnInfo.districtMarksIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getRealmMarks() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.realmMarksIndex);
    }

    @Override
    public void setRealmMarks(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.realmMarksIndex);
            return;
        }
        row.setString(columnInfo.realmMarksIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getProvinceName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.provinceNameIndex);
    }

    @Override
    public void setProvinceName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.provinceNameIndex);
            return;
        }
        row.setString(columnInfo.provinceNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getRepostNum() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.repostNumIndex);
    }

    @Override
    public void setRepostNum(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.repostNumIndex);
            return;
        }
        row.setString(columnInfo.repostNumIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getImagePaths() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.imagePathsIndex);
    }

    @Override
    public void setImagePaths(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.imagePathsIndex);
            return;
        }
        row.setString(columnInfo.imagePathsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getProfileUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.profileUrlIndex);
    }

    @Override
    public void setProfileUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.profileUrlIndex);
            return;
        }
        row.setString(columnInfo.profileUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getUserUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.userUrlIndex);
    }

    @Override
    public void setUserUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.userUrlIndex);
            return;
        }
        row.setString(columnInfo.userUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getVerified() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.verifiedIndex);
    }

    @Override
    public void setVerified(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.verifiedIndex);
            return;
        }
        row.setString(columnInfo.verifiedIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getNickName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.nickNameIndex);
    }

    @Override
    public void setNickName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.nickNameIndex);
            return;
        }
        row.setString(columnInfo.nickNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getProviderId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.providerIdIndex);
    }

    @Override
    public void setProviderId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.providerIdIndex);
            return;
        }
        row.setString(columnInfo.providerIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getUserAccount() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.userAccountIndex);
    }

    @Override
    public void setUserAccount(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.userAccountIndex);
            return;
        }
        row.setString(columnInfo.userAccountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getUserId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.userIdIndex);
    }

    @Override
    public void setUserId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.userIdIndex);
            return;
        }
        row.setString(columnInfo.userIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcAccount() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcAccountIndex);
    }

    @Override
    public void setSrcAccount(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcAccountIndex);
            return;
        }
        row.setString(columnInfo.srcAccountIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcUserId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcUserIdIndex);
    }

    @Override
    public void setSrcUserId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcUserIdIndex);
            return;
        }
        row.setString(columnInfo.srcUserIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcNickName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcNickNameIndex);
    }

    @Override
    public void setSrcNickName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcNickNameIndex);
            return;
        }
        row.setString(columnInfo.srcNickNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcProfileUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcProfileUrlIndex);
    }

    @Override
    public void setSrcProfileUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcProfileUrlIndex);
            return;
        }
        row.setString(columnInfo.srcProfileUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcUserUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcUserUrlIndex);
    }

    @Override
    public void setSrcUserUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcUserUrlIndex);
            return;
        }
        row.setString(columnInfo.srcUserUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcWeiboId() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcWeiboIdIndex);
    }

    @Override
    public void setSrcWeiboId(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcWeiboIdIndex);
            return;
        }
        row.setString(columnInfo.srcWeiboIdIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcContent() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcContentIndex);
    }

    @Override
    public void setSrcContent(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcContentIndex);
            return;
        }
        row.setString(columnInfo.srcContentIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcPublishDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcPublishDateIndex);
    }

    @Override
    public void setSrcPublishDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcPublishDateIndex);
            return;
        }
        row.setString(columnInfo.srcPublishDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcRepostNum() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcRepostNumIndex);
    }

    @Override
    public void setSrcRepostNum(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcRepostNumIndex);
            return;
        }
        row.setString(columnInfo.srcRepostNumIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcCommentNum() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcCommentNumIndex);
    }

    @Override
    public void setSrcCommentNum(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcCommentNumIndex);
            return;
        }
        row.setString(columnInfo.srcCommentNumIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcUrl() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcUrlIndex);
    }

    @Override
    public void setSrcUrl(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcUrlIndex);
            return;
        }
        row.setString(columnInfo.srcUrlIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcPicUrls() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcPicUrlsIndex);
    }

    @Override
    public void setSrcPicUrls(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcPicUrlsIndex);
            return;
        }
        row.setString(columnInfo.srcPicUrlsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSrcPicPaths() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.srcPicPathsIndex);
    }

    @Override
    public void setSrcPicPaths(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.srcPicPathsIndex);
            return;
        }
        row.setString(columnInfo.srcPicPathsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getDealStatus() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.dealStatusIndex);
    }

    @Override
    public void setDealStatus(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.dealStatusIndex);
            return;
        }
        row.setString(columnInfo.dealStatusIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Doc")) {
            Table table = transaction.getTable("class_Doc");
            table.addColumn(RealmFieldType.STRING, "docId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "url", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "topDomain", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "subDomain", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "siteId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "siteName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "channelId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "channelName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mediaType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "subMediaType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "trdMediaType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "contentType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "postUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "postDocId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "replyFloor", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "publishDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "author", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "replyNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "clickNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "copyFrom", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "isOversea", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "imageUrls", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "dataSource", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "lastModifyDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "fetchDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "importDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "isGarbage", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "vectorWords", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "summary", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "sameDocId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "similarDocId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "negativeStatus", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "negativeWords", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "districtMarks", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "realmMarks", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "provinceName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "repostNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "imagePaths", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "profileUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "userUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "verified", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "nickName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "providerId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "userAccount", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "userId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcAccount", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcUserId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcNickName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcProfileUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcUserUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcWeiboId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcContent", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPublishDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcRepostNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcCommentNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPicUrls", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPicPaths", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "dealStatus", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Doc");
    }

    public static DocColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Doc")) {
            Table table = transaction.getTable("class_Doc");
            if (table.getColumnCount() != 61) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 61 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 61; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DocColumnInfo columnInfo = new DocColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("docId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'docId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("docId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'docId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.docIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'docId' is required. Either set @Required to field 'docId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("url")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'url' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("url") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'url' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.urlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'url' is required. Either set @Required to field 'url' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("topDomain")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'topDomain' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("topDomain") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'topDomain' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.topDomainIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'topDomain' is required. Either set @Required to field 'topDomain' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("subDomain")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'subDomain' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("subDomain") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'subDomain' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.subDomainIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'subDomain' is required. Either set @Required to field 'subDomain' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("siteId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'siteId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("siteId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'siteId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.siteIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'siteId' is required. Either set @Required to field 'siteId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("siteName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'siteName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("siteName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'siteName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.siteNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'siteName' is required. Either set @Required to field 'siteName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("channelId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'channelId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("channelId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'channelId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.channelIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'channelId' is required. Either set @Required to field 'channelId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("channelName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'channelName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("channelName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'channelName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.channelNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'channelName' is required. Either set @Required to field 'channelName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("mediaType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'mediaType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("mediaType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'mediaType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.mediaTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'mediaType' is required. Either set @Required to field 'mediaType' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("subMediaType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'subMediaType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("subMediaType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'subMediaType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.subMediaTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'subMediaType' is required. Either set @Required to field 'subMediaType' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("trdMediaType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'trdMediaType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("trdMediaType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'trdMediaType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.trdMediaTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'trdMediaType' is required. Either set @Required to field 'trdMediaType' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("contentType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'contentType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("contentType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'contentType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.contentTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'contentType' is required. Either set @Required to field 'contentType' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("postUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'postUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("postUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'postUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.postUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'postUrl' is required. Either set @Required to field 'postUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("postDocId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'postDocId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("postDocId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'postDocId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.postDocIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'postDocId' is required. Either set @Required to field 'postDocId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("replyFloor")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'replyFloor' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("replyFloor") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'replyFloor' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.replyFloorIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'replyFloor' is required. Either set @Required to field 'replyFloor' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("content")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'content' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("content") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'content' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.contentIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'content' is required. Either set @Required to field 'content' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("publishDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'publishDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("publishDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'publishDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.publishDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'publishDate' is required. Either set @Required to field 'publishDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("author")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'author' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("author") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'author' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.authorIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'author' is required. Either set @Required to field 'author' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("replyNum")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'replyNum' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("replyNum") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'replyNum' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.replyNumIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'replyNum' is required. Either set @Required to field 'replyNum' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("clickNum")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'clickNum' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("clickNum") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'clickNum' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.clickNumIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'clickNum' is required. Either set @Required to field 'clickNum' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("copyFrom")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'copyFrom' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("copyFrom") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'copyFrom' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.copyFromIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'copyFrom' is required. Either set @Required to field 'copyFrom' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("isOversea")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'isOversea' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isOversea") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'isOversea' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.isOverseaIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'isOversea' is required. Either set @Required to field 'isOversea' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("imageUrls")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'imageUrls' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("imageUrls") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'imageUrls' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.imageUrlsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'imageUrls' is required. Either set @Required to field 'imageUrls' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("dataSource")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'dataSource' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("dataSource") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'dataSource' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.dataSourceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'dataSource' is required. Either set @Required to field 'dataSource' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("lastModifyDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'lastModifyDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("lastModifyDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'lastModifyDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.lastModifyDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'lastModifyDate' is required. Either set @Required to field 'lastModifyDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("fetchDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'fetchDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("fetchDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'fetchDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.fetchDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'fetchDate' is required. Either set @Required to field 'fetchDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("importDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'importDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("importDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'importDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.importDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'importDate' is required. Either set @Required to field 'importDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("isGarbage")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'isGarbage' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isGarbage") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'isGarbage' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.isGarbageIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'isGarbage' is required. Either set @Required to field 'isGarbage' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("vectorWords")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'vectorWords' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("vectorWords") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'vectorWords' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.vectorWordsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'vectorWords' is required. Either set @Required to field 'vectorWords' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("summary")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'summary' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("summary") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'summary' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.summaryIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'summary' is required. Either set @Required to field 'summary' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("sameDocId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'sameDocId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("sameDocId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'sameDocId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.sameDocIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'sameDocId' is required. Either set @Required to field 'sameDocId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("similarDocId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'similarDocId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("similarDocId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'similarDocId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.similarDocIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'similarDocId' is required. Either set @Required to field 'similarDocId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("negativeStatus")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'negativeStatus' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("negativeStatus") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'negativeStatus' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.negativeStatusIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'negativeStatus' is required. Either set @Required to field 'negativeStatus' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("negativeWords")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'negativeWords' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("negativeWords") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'negativeWords' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.negativeWordsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'negativeWords' is required. Either set @Required to field 'negativeWords' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("districtMarks")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'districtMarks' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("districtMarks") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'districtMarks' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.districtMarksIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'districtMarks' is required. Either set @Required to field 'districtMarks' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("realmMarks")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmMarks' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmMarks") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'realmMarks' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.realmMarksIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmMarks' is required. Either set @Required to field 'realmMarks' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("provinceName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'provinceName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("provinceName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'provinceName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.provinceNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'provinceName' is required. Either set @Required to field 'provinceName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("repostNum")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'repostNum' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("repostNum") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'repostNum' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.repostNumIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'repostNum' is required. Either set @Required to field 'repostNum' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("imagePaths")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'imagePaths' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("imagePaths") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'imagePaths' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.imagePathsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'imagePaths' is required. Either set @Required to field 'imagePaths' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("profileUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'profileUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("profileUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'profileUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.profileUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'profileUrl' is required. Either set @Required to field 'profileUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("userUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'userUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'userUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'userUrl' is required. Either set @Required to field 'userUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("verified")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'verified' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("verified") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'verified' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.verifiedIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'verified' is required. Either set @Required to field 'verified' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("nickName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'nickName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("nickName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'nickName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.nickNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'nickName' is required. Either set @Required to field 'nickName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("providerId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'providerId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("providerId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'providerId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.providerIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'providerId' is required. Either set @Required to field 'providerId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("userAccount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'userAccount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userAccount") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'userAccount' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userAccountIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'userAccount' is required. Either set @Required to field 'userAccount' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("userId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'userId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'userId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'userId' is required. Either set @Required to field 'userId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcAccount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcAccount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcAccount") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcAccount' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcAccountIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcAccount' is required. Either set @Required to field 'srcAccount' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcUserId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcUserId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcUserId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcUserId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcUserIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcUserId' is required. Either set @Required to field 'srcUserId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcNickName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcNickName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcNickName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcNickName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcNickNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcNickName' is required. Either set @Required to field 'srcNickName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcProfileUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcProfileUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcProfileUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcProfileUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcProfileUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcProfileUrl' is required. Either set @Required to field 'srcProfileUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcUserUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcUserUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcUserUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcUserUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcUserUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcUserUrl' is required. Either set @Required to field 'srcUserUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcWeiboId")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcWeiboId' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcWeiboId") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcWeiboId' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcWeiboIdIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcWeiboId' is required. Either set @Required to field 'srcWeiboId' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcContent")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcContent' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcContent") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcContent' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcContentIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcContent' is required. Either set @Required to field 'srcContent' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcPublishDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcPublishDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcPublishDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcPublishDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcPublishDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcPublishDate' is required. Either set @Required to field 'srcPublishDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcRepostNum")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcRepostNum' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcRepostNum") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcRepostNum' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcRepostNumIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcRepostNum' is required. Either set @Required to field 'srcRepostNum' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcCommentNum")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcCommentNum' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcCommentNum") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcCommentNum' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcCommentNumIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcCommentNum' is required. Either set @Required to field 'srcCommentNum' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcUrl' is required. Either set @Required to field 'srcUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcPicUrls")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcPicUrls' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcPicUrls") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcPicUrls' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcPicUrlsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcPicUrls' is required. Either set @Required to field 'srcPicUrls' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("srcPicPaths")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcPicPaths' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcPicPaths") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcPicPaths' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcPicPathsIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcPicPaths' is required. Either set @Required to field 'srcPicPaths' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("dealStatus")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'dealStatus' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("dealStatus") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'dealStatus' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.dealStatusIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'dealStatus' is required. Either set @Required to field 'dealStatus' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Doc class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Doc";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Doc createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Doc obj = realm.createObject(Doc.class);
        if (json.has("docId")) {
            if (json.isNull("docId")) {
                obj.setDocId(null);
            } else {
                obj.setDocId((String) json.getString("docId"));
            }
        }
        if (json.has("url")) {
            if (json.isNull("url")) {
                obj.setUrl(null);
            } else {
                obj.setUrl((String) json.getString("url"));
            }
        }
        if (json.has("topDomain")) {
            if (json.isNull("topDomain")) {
                obj.setTopDomain(null);
            } else {
                obj.setTopDomain((String) json.getString("topDomain"));
            }
        }
        if (json.has("subDomain")) {
            if (json.isNull("subDomain")) {
                obj.setSubDomain(null);
            } else {
                obj.setSubDomain((String) json.getString("subDomain"));
            }
        }
        if (json.has("siteId")) {
            if (json.isNull("siteId")) {
                obj.setSiteId(null);
            } else {
                obj.setSiteId((String) json.getString("siteId"));
            }
        }
        if (json.has("siteName")) {
            if (json.isNull("siteName")) {
                obj.setSiteName(null);
            } else {
                obj.setSiteName((String) json.getString("siteName"));
            }
        }
        if (json.has("channelId")) {
            if (json.isNull("channelId")) {
                obj.setChannelId(null);
            } else {
                obj.setChannelId((String) json.getString("channelId"));
            }
        }
        if (json.has("channelName")) {
            if (json.isNull("channelName")) {
                obj.setChannelName(null);
            } else {
                obj.setChannelName((String) json.getString("channelName"));
            }
        }
        if (json.has("mediaType")) {
            if (json.isNull("mediaType")) {
                obj.setMediaType(null);
            } else {
                obj.setMediaType((String) json.getString("mediaType"));
            }
        }
        if (json.has("subMediaType")) {
            if (json.isNull("subMediaType")) {
                obj.setSubMediaType(null);
            } else {
                obj.setSubMediaType((String) json.getString("subMediaType"));
            }
        }
        if (json.has("trdMediaType")) {
            if (json.isNull("trdMediaType")) {
                obj.setTrdMediaType(null);
            } else {
                obj.setTrdMediaType((String) json.getString("trdMediaType"));
            }
        }
        if (json.has("contentType")) {
            if (json.isNull("contentType")) {
                obj.setContentType(null);
            } else {
                obj.setContentType((String) json.getString("contentType"));
            }
        }
        if (json.has("postUrl")) {
            if (json.isNull("postUrl")) {
                obj.setPostUrl(null);
            } else {
                obj.setPostUrl((String) json.getString("postUrl"));
            }
        }
        if (json.has("postDocId")) {
            if (json.isNull("postDocId")) {
                obj.setPostDocId(null);
            } else {
                obj.setPostDocId((String) json.getString("postDocId"));
            }
        }
        if (json.has("replyFloor")) {
            if (json.isNull("replyFloor")) {
                obj.setReplyFloor(null);
            } else {
                obj.setReplyFloor((String) json.getString("replyFloor"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                obj.setTitle(null);
            } else {
                obj.setTitle((String) json.getString("title"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                obj.setContent(null);
            } else {
                obj.setContent((String) json.getString("content"));
            }
        }
        if (json.has("publishDate")) {
            if (json.isNull("publishDate")) {
                obj.setPublishDate(null);
            } else {
                obj.setPublishDate((String) json.getString("publishDate"));
            }
        }
        if (json.has("author")) {
            if (json.isNull("author")) {
                obj.setAuthor(null);
            } else {
                obj.setAuthor((String) json.getString("author"));
            }
        }
        if (json.has("replyNum")) {
            if (json.isNull("replyNum")) {
                obj.setReplyNum(null);
            } else {
                obj.setReplyNum((String) json.getString("replyNum"));
            }
        }
        if (json.has("clickNum")) {
            if (json.isNull("clickNum")) {
                obj.setClickNum(null);
            } else {
                obj.setClickNum((String) json.getString("clickNum"));
            }
        }
        if (json.has("copyFrom")) {
            if (json.isNull("copyFrom")) {
                obj.setCopyFrom(null);
            } else {
                obj.setCopyFrom((String) json.getString("copyFrom"));
            }
        }
        if (json.has("isOversea")) {
            if (json.isNull("isOversea")) {
                obj.setIsOversea(null);
            } else {
                obj.setIsOversea((String) json.getString("isOversea"));
            }
        }
        if (json.has("imageUrls")) {
            if (json.isNull("imageUrls")) {
                obj.setImageUrls(null);
            } else {
                obj.setImageUrls((String) json.getString("imageUrls"));
            }
        }
        if (json.has("dataSource")) {
            if (json.isNull("dataSource")) {
                obj.setDataSource(null);
            } else {
                obj.setDataSource((String) json.getString("dataSource"));
            }
        }
        if (json.has("lastModifyDate")) {
            if (json.isNull("lastModifyDate")) {
                obj.setLastModifyDate(null);
            } else {
                obj.setLastModifyDate((String) json.getString("lastModifyDate"));
            }
        }
        if (json.has("fetchDate")) {
            if (json.isNull("fetchDate")) {
                obj.setFetchDate(null);
            } else {
                obj.setFetchDate((String) json.getString("fetchDate"));
            }
        }
        if (json.has("importDate")) {
            if (json.isNull("importDate")) {
                obj.setImportDate(null);
            } else {
                obj.setImportDate((String) json.getString("importDate"));
            }
        }
        if (json.has("isGarbage")) {
            if (json.isNull("isGarbage")) {
                obj.setIsGarbage(null);
            } else {
                obj.setIsGarbage((String) json.getString("isGarbage"));
            }
        }
        if (json.has("vectorWords")) {
            if (json.isNull("vectorWords")) {
                obj.setVectorWords(null);
            } else {
                obj.setVectorWords((String) json.getString("vectorWords"));
            }
        }
        if (json.has("summary")) {
            if (json.isNull("summary")) {
                obj.setSummary(null);
            } else {
                obj.setSummary((String) json.getString("summary"));
            }
        }
        if (json.has("sameDocId")) {
            if (json.isNull("sameDocId")) {
                obj.setSameDocId(null);
            } else {
                obj.setSameDocId((String) json.getString("sameDocId"));
            }
        }
        if (json.has("similarDocId")) {
            if (json.isNull("similarDocId")) {
                obj.setSimilarDocId(null);
            } else {
                obj.setSimilarDocId((String) json.getString("similarDocId"));
            }
        }
        if (json.has("negativeStatus")) {
            if (json.isNull("negativeStatus")) {
                obj.setNegativeStatus(null);
            } else {
                obj.setNegativeStatus((String) json.getString("negativeStatus"));
            }
        }
        if (json.has("negativeWords")) {
            if (json.isNull("negativeWords")) {
                obj.setNegativeWords(null);
            } else {
                obj.setNegativeWords((String) json.getString("negativeWords"));
            }
        }
        if (json.has("districtMarks")) {
            if (json.isNull("districtMarks")) {
                obj.setDistrictMarks(null);
            } else {
                obj.setDistrictMarks((String) json.getString("districtMarks"));
            }
        }
        if (json.has("realmMarks")) {
            if (json.isNull("realmMarks")) {
                obj.setRealmMarks(null);
            } else {
                obj.setRealmMarks((String) json.getString("realmMarks"));
            }
        }
        if (json.has("provinceName")) {
            if (json.isNull("provinceName")) {
                obj.setProvinceName(null);
            } else {
                obj.setProvinceName((String) json.getString("provinceName"));
            }
        }
        if (json.has("repostNum")) {
            if (json.isNull("repostNum")) {
                obj.setRepostNum(null);
            } else {
                obj.setRepostNum((String) json.getString("repostNum"));
            }
        }
        if (json.has("imagePaths")) {
            if (json.isNull("imagePaths")) {
                obj.setImagePaths(null);
            } else {
                obj.setImagePaths((String) json.getString("imagePaths"));
            }
        }
        if (json.has("profileUrl")) {
            if (json.isNull("profileUrl")) {
                obj.setProfileUrl(null);
            } else {
                obj.setProfileUrl((String) json.getString("profileUrl"));
            }
        }
        if (json.has("userUrl")) {
            if (json.isNull("userUrl")) {
                obj.setUserUrl(null);
            } else {
                obj.setUserUrl((String) json.getString("userUrl"));
            }
        }
        if (json.has("verified")) {
            if (json.isNull("verified")) {
                obj.setVerified(null);
            } else {
                obj.setVerified((String) json.getString("verified"));
            }
        }
        if (json.has("nickName")) {
            if (json.isNull("nickName")) {
                obj.setNickName(null);
            } else {
                obj.setNickName((String) json.getString("nickName"));
            }
        }
        if (json.has("providerId")) {
            if (json.isNull("providerId")) {
                obj.setProviderId(null);
            } else {
                obj.setProviderId((String) json.getString("providerId"));
            }
        }
        if (json.has("userAccount")) {
            if (json.isNull("userAccount")) {
                obj.setUserAccount(null);
            } else {
                obj.setUserAccount((String) json.getString("userAccount"));
            }
        }
        if (json.has("userId")) {
            if (json.isNull("userId")) {
                obj.setUserId(null);
            } else {
                obj.setUserId((String) json.getString("userId"));
            }
        }
        if (json.has("srcAccount")) {
            if (json.isNull("srcAccount")) {
                obj.setSrcAccount(null);
            } else {
                obj.setSrcAccount((String) json.getString("srcAccount"));
            }
        }
        if (json.has("srcUserId")) {
            if (json.isNull("srcUserId")) {
                obj.setSrcUserId(null);
            } else {
                obj.setSrcUserId((String) json.getString("srcUserId"));
            }
        }
        if (json.has("srcNickName")) {
            if (json.isNull("srcNickName")) {
                obj.setSrcNickName(null);
            } else {
                obj.setSrcNickName((String) json.getString("srcNickName"));
            }
        }
        if (json.has("srcProfileUrl")) {
            if (json.isNull("srcProfileUrl")) {
                obj.setSrcProfileUrl(null);
            } else {
                obj.setSrcProfileUrl((String) json.getString("srcProfileUrl"));
            }
        }
        if (json.has("srcUserUrl")) {
            if (json.isNull("srcUserUrl")) {
                obj.setSrcUserUrl(null);
            } else {
                obj.setSrcUserUrl((String) json.getString("srcUserUrl"));
            }
        }
        if (json.has("srcWeiboId")) {
            if (json.isNull("srcWeiboId")) {
                obj.setSrcWeiboId(null);
            } else {
                obj.setSrcWeiboId((String) json.getString("srcWeiboId"));
            }
        }
        if (json.has("srcContent")) {
            if (json.isNull("srcContent")) {
                obj.setSrcContent(null);
            } else {
                obj.setSrcContent((String) json.getString("srcContent"));
            }
        }
        if (json.has("srcPublishDate")) {
            if (json.isNull("srcPublishDate")) {
                obj.setSrcPublishDate(null);
            } else {
                obj.setSrcPublishDate((String) json.getString("srcPublishDate"));
            }
        }
        if (json.has("srcRepostNum")) {
            if (json.isNull("srcRepostNum")) {
                obj.setSrcRepostNum(null);
            } else {
                obj.setSrcRepostNum((String) json.getString("srcRepostNum"));
            }
        }
        if (json.has("srcCommentNum")) {
            if (json.isNull("srcCommentNum")) {
                obj.setSrcCommentNum(null);
            } else {
                obj.setSrcCommentNum((String) json.getString("srcCommentNum"));
            }
        }
        if (json.has("srcUrl")) {
            if (json.isNull("srcUrl")) {
                obj.setSrcUrl(null);
            } else {
                obj.setSrcUrl((String) json.getString("srcUrl"));
            }
        }
        if (json.has("srcPicUrls")) {
            if (json.isNull("srcPicUrls")) {
                obj.setSrcPicUrls(null);
            } else {
                obj.setSrcPicUrls((String) json.getString("srcPicUrls"));
            }
        }
        if (json.has("srcPicPaths")) {
            if (json.isNull("srcPicPaths")) {
                obj.setSrcPicPaths(null);
            } else {
                obj.setSrcPicPaths((String) json.getString("srcPicPaths"));
            }
        }
        if (json.has("dealStatus")) {
            if (json.isNull("dealStatus")) {
                obj.setDealStatus(null);
            } else {
                obj.setDealStatus((String) json.getString("dealStatus"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Doc createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Doc obj = realm.createObject(Doc.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("docId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setDocId(null);
                } else {
                    obj.setDocId((String) reader.nextString());
                }
            } else if (name.equals("url")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setUrl(null);
                } else {
                    obj.setUrl((String) reader.nextString());
                }
            } else if (name.equals("topDomain")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTopDomain(null);
                } else {
                    obj.setTopDomain((String) reader.nextString());
                }
            } else if (name.equals("subDomain")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSubDomain(null);
                } else {
                    obj.setSubDomain((String) reader.nextString());
                }
            } else if (name.equals("siteId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSiteId(null);
                } else {
                    obj.setSiteId((String) reader.nextString());
                }
            } else if (name.equals("siteName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSiteName(null);
                } else {
                    obj.setSiteName((String) reader.nextString());
                }
            } else if (name.equals("channelId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setChannelId(null);
                } else {
                    obj.setChannelId((String) reader.nextString());
                }
            } else if (name.equals("channelName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setChannelName(null);
                } else {
                    obj.setChannelName((String) reader.nextString());
                }
            } else if (name.equals("mediaType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setMediaType(null);
                } else {
                    obj.setMediaType((String) reader.nextString());
                }
            } else if (name.equals("subMediaType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSubMediaType(null);
                } else {
                    obj.setSubMediaType((String) reader.nextString());
                }
            } else if (name.equals("trdMediaType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTrdMediaType(null);
                } else {
                    obj.setTrdMediaType((String) reader.nextString());
                }
            } else if (name.equals("contentType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setContentType(null);
                } else {
                    obj.setContentType((String) reader.nextString());
                }
            } else if (name.equals("postUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPostUrl(null);
                } else {
                    obj.setPostUrl((String) reader.nextString());
                }
            } else if (name.equals("postDocId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPostDocId(null);
                } else {
                    obj.setPostDocId((String) reader.nextString());
                }
            } else if (name.equals("replyFloor")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setReplyFloor(null);
                } else {
                    obj.setReplyFloor((String) reader.nextString());
                }
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTitle(null);
                } else {
                    obj.setTitle((String) reader.nextString());
                }
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setContent(null);
                } else {
                    obj.setContent((String) reader.nextString());
                }
            } else if (name.equals("publishDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPublishDate(null);
                } else {
                    obj.setPublishDate((String) reader.nextString());
                }
            } else if (name.equals("author")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setAuthor(null);
                } else {
                    obj.setAuthor((String) reader.nextString());
                }
            } else if (name.equals("replyNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setReplyNum(null);
                } else {
                    obj.setReplyNum((String) reader.nextString());
                }
            } else if (name.equals("clickNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setClickNum(null);
                } else {
                    obj.setClickNum((String) reader.nextString());
                }
            } else if (name.equals("copyFrom")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setCopyFrom(null);
                } else {
                    obj.setCopyFrom((String) reader.nextString());
                }
            } else if (name.equals("isOversea")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setIsOversea(null);
                } else {
                    obj.setIsOversea((String) reader.nextString());
                }
            } else if (name.equals("imageUrls")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setImageUrls(null);
                } else {
                    obj.setImageUrls((String) reader.nextString());
                }
            } else if (name.equals("dataSource")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setDataSource(null);
                } else {
                    obj.setDataSource((String) reader.nextString());
                }
            } else if (name.equals("lastModifyDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setLastModifyDate(null);
                } else {
                    obj.setLastModifyDate((String) reader.nextString());
                }
            } else if (name.equals("fetchDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setFetchDate(null);
                } else {
                    obj.setFetchDate((String) reader.nextString());
                }
            } else if (name.equals("importDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setImportDate(null);
                } else {
                    obj.setImportDate((String) reader.nextString());
                }
            } else if (name.equals("isGarbage")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setIsGarbage(null);
                } else {
                    obj.setIsGarbage((String) reader.nextString());
                }
            } else if (name.equals("vectorWords")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setVectorWords(null);
                } else {
                    obj.setVectorWords((String) reader.nextString());
                }
            } else if (name.equals("summary")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSummary(null);
                } else {
                    obj.setSummary((String) reader.nextString());
                }
            } else if (name.equals("sameDocId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSameDocId(null);
                } else {
                    obj.setSameDocId((String) reader.nextString());
                }
            } else if (name.equals("similarDocId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSimilarDocId(null);
                } else {
                    obj.setSimilarDocId((String) reader.nextString());
                }
            } else if (name.equals("negativeStatus")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setNegativeStatus(null);
                } else {
                    obj.setNegativeStatus((String) reader.nextString());
                }
            } else if (name.equals("negativeWords")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setNegativeWords(null);
                } else {
                    obj.setNegativeWords((String) reader.nextString());
                }
            } else if (name.equals("districtMarks")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setDistrictMarks(null);
                } else {
                    obj.setDistrictMarks((String) reader.nextString());
                }
            } else if (name.equals("realmMarks")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setRealmMarks(null);
                } else {
                    obj.setRealmMarks((String) reader.nextString());
                }
            } else if (name.equals("provinceName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setProvinceName(null);
                } else {
                    obj.setProvinceName((String) reader.nextString());
                }
            } else if (name.equals("repostNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setRepostNum(null);
                } else {
                    obj.setRepostNum((String) reader.nextString());
                }
            } else if (name.equals("imagePaths")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setImagePaths(null);
                } else {
                    obj.setImagePaths((String) reader.nextString());
                }
            } else if (name.equals("profileUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setProfileUrl(null);
                } else {
                    obj.setProfileUrl((String) reader.nextString());
                }
            } else if (name.equals("userUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setUserUrl(null);
                } else {
                    obj.setUserUrl((String) reader.nextString());
                }
            } else if (name.equals("verified")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setVerified(null);
                } else {
                    obj.setVerified((String) reader.nextString());
                }
            } else if (name.equals("nickName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setNickName(null);
                } else {
                    obj.setNickName((String) reader.nextString());
                }
            } else if (name.equals("providerId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setProviderId(null);
                } else {
                    obj.setProviderId((String) reader.nextString());
                }
            } else if (name.equals("userAccount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setUserAccount(null);
                } else {
                    obj.setUserAccount((String) reader.nextString());
                }
            } else if (name.equals("userId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setUserId(null);
                } else {
                    obj.setUserId((String) reader.nextString());
                }
            } else if (name.equals("srcAccount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcAccount(null);
                } else {
                    obj.setSrcAccount((String) reader.nextString());
                }
            } else if (name.equals("srcUserId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcUserId(null);
                } else {
                    obj.setSrcUserId((String) reader.nextString());
                }
            } else if (name.equals("srcNickName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcNickName(null);
                } else {
                    obj.setSrcNickName((String) reader.nextString());
                }
            } else if (name.equals("srcProfileUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcProfileUrl(null);
                } else {
                    obj.setSrcProfileUrl((String) reader.nextString());
                }
            } else if (name.equals("srcUserUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcUserUrl(null);
                } else {
                    obj.setSrcUserUrl((String) reader.nextString());
                }
            } else if (name.equals("srcWeiboId")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcWeiboId(null);
                } else {
                    obj.setSrcWeiboId((String) reader.nextString());
                }
            } else if (name.equals("srcContent")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcContent(null);
                } else {
                    obj.setSrcContent((String) reader.nextString());
                }
            } else if (name.equals("srcPublishDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcPublishDate(null);
                } else {
                    obj.setSrcPublishDate((String) reader.nextString());
                }
            } else if (name.equals("srcRepostNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcRepostNum(null);
                } else {
                    obj.setSrcRepostNum((String) reader.nextString());
                }
            } else if (name.equals("srcCommentNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcCommentNum(null);
                } else {
                    obj.setSrcCommentNum((String) reader.nextString());
                }
            } else if (name.equals("srcUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcUrl(null);
                } else {
                    obj.setSrcUrl((String) reader.nextString());
                }
            } else if (name.equals("srcPicUrls")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcPicUrls(null);
                } else {
                    obj.setSrcPicUrls((String) reader.nextString());
                }
            } else if (name.equals("srcPicPaths")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcPicPaths(null);
                } else {
                    obj.setSrcPicPaths((String) reader.nextString());
                }
            } else if (name.equals("dealStatus")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setDealStatus(null);
                } else {
                    obj.setDealStatus((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Doc copyOrUpdate(Realm realm, Doc object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Doc copy(Realm realm, Doc newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        Doc realmObject = realm.createObject(Doc.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setDocId(newObject.getDocId());
        realmObject.setUrl(newObject.getUrl());
        realmObject.setTopDomain(newObject.getTopDomain());
        realmObject.setSubDomain(newObject.getSubDomain());
        realmObject.setSiteId(newObject.getSiteId());
        realmObject.setSiteName(newObject.getSiteName());
        realmObject.setChannelId(newObject.getChannelId());
        realmObject.setChannelName(newObject.getChannelName());
        realmObject.setMediaType(newObject.getMediaType());
        realmObject.setSubMediaType(newObject.getSubMediaType());
        realmObject.setTrdMediaType(newObject.getTrdMediaType());
        realmObject.setContentType(newObject.getContentType());
        realmObject.setPostUrl(newObject.getPostUrl());
        realmObject.setPostDocId(newObject.getPostDocId());
        realmObject.setReplyFloor(newObject.getReplyFloor());
        realmObject.setTitle(newObject.getTitle());
        realmObject.setContent(newObject.getContent());
        realmObject.setPublishDate(newObject.getPublishDate());
        realmObject.setAuthor(newObject.getAuthor());
        realmObject.setReplyNum(newObject.getReplyNum());
        realmObject.setClickNum(newObject.getClickNum());
        realmObject.setCopyFrom(newObject.getCopyFrom());
        realmObject.setIsOversea(newObject.getIsOversea());
        realmObject.setImageUrls(newObject.getImageUrls());
        realmObject.setDataSource(newObject.getDataSource());
        realmObject.setLastModifyDate(newObject.getLastModifyDate());
        realmObject.setFetchDate(newObject.getFetchDate());
        realmObject.setImportDate(newObject.getImportDate());
        realmObject.setIsGarbage(newObject.getIsGarbage());
        realmObject.setVectorWords(newObject.getVectorWords());
        realmObject.setSummary(newObject.getSummary());
        realmObject.setSameDocId(newObject.getSameDocId());
        realmObject.setSimilarDocId(newObject.getSimilarDocId());
        realmObject.setNegativeStatus(newObject.getNegativeStatus());
        realmObject.setNegativeWords(newObject.getNegativeWords());
        realmObject.setDistrictMarks(newObject.getDistrictMarks());
        realmObject.setRealmMarks(newObject.getRealmMarks());
        realmObject.setProvinceName(newObject.getProvinceName());
        realmObject.setRepostNum(newObject.getRepostNum());
        realmObject.setImagePaths(newObject.getImagePaths());
        realmObject.setProfileUrl(newObject.getProfileUrl());
        realmObject.setUserUrl(newObject.getUserUrl());
        realmObject.setVerified(newObject.getVerified());
        realmObject.setNickName(newObject.getNickName());
        realmObject.setProviderId(newObject.getProviderId());
        realmObject.setUserAccount(newObject.getUserAccount());
        realmObject.setUserId(newObject.getUserId());
        realmObject.setSrcAccount(newObject.getSrcAccount());
        realmObject.setSrcUserId(newObject.getSrcUserId());
        realmObject.setSrcNickName(newObject.getSrcNickName());
        realmObject.setSrcProfileUrl(newObject.getSrcProfileUrl());
        realmObject.setSrcUserUrl(newObject.getSrcUserUrl());
        realmObject.setSrcWeiboId(newObject.getSrcWeiboId());
        realmObject.setSrcContent(newObject.getSrcContent());
        realmObject.setSrcPublishDate(newObject.getSrcPublishDate());
        realmObject.setSrcRepostNum(newObject.getSrcRepostNum());
        realmObject.setSrcCommentNum(newObject.getSrcCommentNum());
        realmObject.setSrcUrl(newObject.getSrcUrl());
        realmObject.setSrcPicUrls(newObject.getSrcPicUrls());
        realmObject.setSrcPicPaths(newObject.getSrcPicPaths());
        realmObject.setDealStatus(newObject.getDealStatus());
        return realmObject;
    }

    public static Doc createDetachedCopy(Doc realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<Doc> cachedObject = (CacheData) cache.get(realmObject);
        Doc standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Doc();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setDocId(realmObject.getDocId());
        standaloneObject.setUrl(realmObject.getUrl());
        standaloneObject.setTopDomain(realmObject.getTopDomain());
        standaloneObject.setSubDomain(realmObject.getSubDomain());
        standaloneObject.setSiteId(realmObject.getSiteId());
        standaloneObject.setSiteName(realmObject.getSiteName());
        standaloneObject.setChannelId(realmObject.getChannelId());
        standaloneObject.setChannelName(realmObject.getChannelName());
        standaloneObject.setMediaType(realmObject.getMediaType());
        standaloneObject.setSubMediaType(realmObject.getSubMediaType());
        standaloneObject.setTrdMediaType(realmObject.getTrdMediaType());
        standaloneObject.setContentType(realmObject.getContentType());
        standaloneObject.setPostUrl(realmObject.getPostUrl());
        standaloneObject.setPostDocId(realmObject.getPostDocId());
        standaloneObject.setReplyFloor(realmObject.getReplyFloor());
        standaloneObject.setTitle(realmObject.getTitle());
        standaloneObject.setContent(realmObject.getContent());
        standaloneObject.setPublishDate(realmObject.getPublishDate());
        standaloneObject.setAuthor(realmObject.getAuthor());
        standaloneObject.setReplyNum(realmObject.getReplyNum());
        standaloneObject.setClickNum(realmObject.getClickNum());
        standaloneObject.setCopyFrom(realmObject.getCopyFrom());
        standaloneObject.setIsOversea(realmObject.getIsOversea());
        standaloneObject.setImageUrls(realmObject.getImageUrls());
        standaloneObject.setDataSource(realmObject.getDataSource());
        standaloneObject.setLastModifyDate(realmObject.getLastModifyDate());
        standaloneObject.setFetchDate(realmObject.getFetchDate());
        standaloneObject.setImportDate(realmObject.getImportDate());
        standaloneObject.setIsGarbage(realmObject.getIsGarbage());
        standaloneObject.setVectorWords(realmObject.getVectorWords());
        standaloneObject.setSummary(realmObject.getSummary());
        standaloneObject.setSameDocId(realmObject.getSameDocId());
        standaloneObject.setSimilarDocId(realmObject.getSimilarDocId());
        standaloneObject.setNegativeStatus(realmObject.getNegativeStatus());
        standaloneObject.setNegativeWords(realmObject.getNegativeWords());
        standaloneObject.setDistrictMarks(realmObject.getDistrictMarks());
        standaloneObject.setRealmMarks(realmObject.getRealmMarks());
        standaloneObject.setProvinceName(realmObject.getProvinceName());
        standaloneObject.setRepostNum(realmObject.getRepostNum());
        standaloneObject.setImagePaths(realmObject.getImagePaths());
        standaloneObject.setProfileUrl(realmObject.getProfileUrl());
        standaloneObject.setUserUrl(realmObject.getUserUrl());
        standaloneObject.setVerified(realmObject.getVerified());
        standaloneObject.setNickName(realmObject.getNickName());
        standaloneObject.setProviderId(realmObject.getProviderId());
        standaloneObject.setUserAccount(realmObject.getUserAccount());
        standaloneObject.setUserId(realmObject.getUserId());
        standaloneObject.setSrcAccount(realmObject.getSrcAccount());
        standaloneObject.setSrcUserId(realmObject.getSrcUserId());
        standaloneObject.setSrcNickName(realmObject.getSrcNickName());
        standaloneObject.setSrcProfileUrl(realmObject.getSrcProfileUrl());
        standaloneObject.setSrcUserUrl(realmObject.getSrcUserUrl());
        standaloneObject.setSrcWeiboId(realmObject.getSrcWeiboId());
        standaloneObject.setSrcContent(realmObject.getSrcContent());
        standaloneObject.setSrcPublishDate(realmObject.getSrcPublishDate());
        standaloneObject.setSrcRepostNum(realmObject.getSrcRepostNum());
        standaloneObject.setSrcCommentNum(realmObject.getSrcCommentNum());
        standaloneObject.setSrcUrl(realmObject.getSrcUrl());
        standaloneObject.setSrcPicUrls(realmObject.getSrcPicUrls());
        standaloneObject.setSrcPicPaths(realmObject.getSrcPicPaths());
        standaloneObject.setDealStatus(realmObject.getDealStatus());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Doc = [");
        stringBuilder.append("{docId:");
        stringBuilder.append(getDocId() != null ? getDocId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url:");
        stringBuilder.append(getUrl() != null ? getUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{topDomain:");
        stringBuilder.append(getTopDomain() != null ? getTopDomain() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{subDomain:");
        stringBuilder.append(getSubDomain() != null ? getSubDomain() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{siteId:");
        stringBuilder.append(getSiteId() != null ? getSiteId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{siteName:");
        stringBuilder.append(getSiteName() != null ? getSiteName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{channelId:");
        stringBuilder.append(getChannelId() != null ? getChannelId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{channelName:");
        stringBuilder.append(getChannelName() != null ? getChannelName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{mediaType:");
        stringBuilder.append(getMediaType() != null ? getMediaType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{subMediaType:");
        stringBuilder.append(getSubMediaType() != null ? getSubMediaType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{trdMediaType:");
        stringBuilder.append(getTrdMediaType() != null ? getTrdMediaType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{contentType:");
        stringBuilder.append(getContentType() != null ? getContentType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{postUrl:");
        stringBuilder.append(getPostUrl() != null ? getPostUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{postDocId:");
        stringBuilder.append(getPostDocId() != null ? getPostDocId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{replyFloor:");
        stringBuilder.append(getReplyFloor() != null ? getReplyFloor() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(getTitle() != null ? getTitle() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(getContent() != null ? getContent() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{publishDate:");
        stringBuilder.append(getPublishDate() != null ? getPublishDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{author:");
        stringBuilder.append(getAuthor() != null ? getAuthor() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{replyNum:");
        stringBuilder.append(getReplyNum() != null ? getReplyNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{clickNum:");
        stringBuilder.append(getClickNum() != null ? getClickNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{copyFrom:");
        stringBuilder.append(getCopyFrom() != null ? getCopyFrom() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isOversea:");
        stringBuilder.append(getIsOversea() != null ? getIsOversea() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{imageUrls:");
        stringBuilder.append(getImageUrls() != null ? getImageUrls() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dataSource:");
        stringBuilder.append(getDataSource() != null ? getDataSource() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lastModifyDate:");
        stringBuilder.append(getLastModifyDate() != null ? getLastModifyDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fetchDate:");
        stringBuilder.append(getFetchDate() != null ? getFetchDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{importDate:");
        stringBuilder.append(getImportDate() != null ? getImportDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{isGarbage:");
        stringBuilder.append(getIsGarbage() != null ? getIsGarbage() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{vectorWords:");
        stringBuilder.append(getVectorWords() != null ? getVectorWords() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{summary:");
        stringBuilder.append(getSummary() != null ? getSummary() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sameDocId:");
        stringBuilder.append(getSameDocId() != null ? getSameDocId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{similarDocId:");
        stringBuilder.append(getSimilarDocId() != null ? getSimilarDocId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{negativeStatus:");
        stringBuilder.append(getNegativeStatus() != null ? getNegativeStatus() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{negativeWords:");
        stringBuilder.append(getNegativeWords() != null ? getNegativeWords() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{districtMarks:");
        stringBuilder.append(getDistrictMarks() != null ? getDistrictMarks() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{realmMarks:");
        stringBuilder.append(getRealmMarks() != null ? getRealmMarks() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{provinceName:");
        stringBuilder.append(getProvinceName() != null ? getProvinceName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{repostNum:");
        stringBuilder.append(getRepostNum() != null ? getRepostNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{imagePaths:");
        stringBuilder.append(getImagePaths() != null ? getImagePaths() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{profileUrl:");
        stringBuilder.append(getProfileUrl() != null ? getProfileUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userUrl:");
        stringBuilder.append(getUserUrl() != null ? getUserUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{verified:");
        stringBuilder.append(getVerified() != null ? getVerified() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nickName:");
        stringBuilder.append(getNickName() != null ? getNickName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{providerId:");
        stringBuilder.append(getProviderId() != null ? getProviderId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userAccount:");
        stringBuilder.append(getUserAccount() != null ? getUserAccount() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userId:");
        stringBuilder.append(getUserId() != null ? getUserId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcAccount:");
        stringBuilder.append(getSrcAccount() != null ? getSrcAccount() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcUserId:");
        stringBuilder.append(getSrcUserId() != null ? getSrcUserId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcNickName:");
        stringBuilder.append(getSrcNickName() != null ? getSrcNickName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcProfileUrl:");
        stringBuilder.append(getSrcProfileUrl() != null ? getSrcProfileUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcUserUrl:");
        stringBuilder.append(getSrcUserUrl() != null ? getSrcUserUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcWeiboId:");
        stringBuilder.append(getSrcWeiboId() != null ? getSrcWeiboId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcContent:");
        stringBuilder.append(getSrcContent() != null ? getSrcContent() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcPublishDate:");
        stringBuilder.append(getSrcPublishDate() != null ? getSrcPublishDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcRepostNum:");
        stringBuilder.append(getSrcRepostNum() != null ? getSrcRepostNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcCommentNum:");
        stringBuilder.append(getSrcCommentNum() != null ? getSrcCommentNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcUrl:");
        stringBuilder.append(getSrcUrl() != null ? getSrcUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcPicUrls:");
        stringBuilder.append(getSrcPicUrls() != null ? getSrcPicUrls() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcPicPaths:");
        stringBuilder.append(getSrcPicPaths() != null ? getSrcPicPaths() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{dealStatus:");
        stringBuilder.append(getDealStatus() != null ? getDealStatus() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocRealmProxy aDoc = (DocRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aDoc.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aDoc.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aDoc.row.getIndex()) return false;

        return true;
    }

}
