package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.AbroadData;
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

public class AbroadDataRealmProxy extends AbroadData
    implements RealmObjectProxy {

    static final class AbroadDataColumnInfo extends ColumnInfo {

        public final long docIdIndex;
        public final long urlIndex;
        public final long siteNameIndex;
        public final long channelNameIndex;
        public final long mediaTypeIndex;
        public final long titleIndex;
        public final long contentIndex;
        public final long publishDateIndex;
        public final long authorIndex;
        public final long replyNumIndex;
        public final long clickNumIndex;
        public final long copyFromIndex;
        public final long isAbroadIndex;
        public final long isOverseaIndex;
        public final long imageUrlsIndex;
        public final long summaryIndex;
        public final long negativeStatusIndex;
        public final long repostNumIndex;
        public final long profileUrlIndex;
        public final long nickNameIndex;
        public final long providerIdIndex;
        public final long srcNickNameIndex;
        public final long srcContentIndex;
        public final long srcPublishDateIndex;
        public final long srcRepostNumIndex;
        public final long srcCommentNumIndex;
        public final long srcPicUrlsIndex;
        public final long srcPicPathsIndex;
        public final long logoIndex;

        AbroadDataColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(29);
            this.docIdIndex = getValidColumnIndex(path, table, "AbroadData", "docId");
            indicesMap.put("docId", this.docIdIndex);

            this.urlIndex = getValidColumnIndex(path, table, "AbroadData", "url");
            indicesMap.put("url", this.urlIndex);

            this.siteNameIndex = getValidColumnIndex(path, table, "AbroadData", "siteName");
            indicesMap.put("siteName", this.siteNameIndex);

            this.channelNameIndex = getValidColumnIndex(path, table, "AbroadData", "channelName");
            indicesMap.put("channelName", this.channelNameIndex);

            this.mediaTypeIndex = getValidColumnIndex(path, table, "AbroadData", "mediaType");
            indicesMap.put("mediaType", this.mediaTypeIndex);

            this.titleIndex = getValidColumnIndex(path, table, "AbroadData", "title");
            indicesMap.put("title", this.titleIndex);

            this.contentIndex = getValidColumnIndex(path, table, "AbroadData", "content");
            indicesMap.put("content", this.contentIndex);

            this.publishDateIndex = getValidColumnIndex(path, table, "AbroadData", "publishDate");
            indicesMap.put("publishDate", this.publishDateIndex);

            this.authorIndex = getValidColumnIndex(path, table, "AbroadData", "author");
            indicesMap.put("author", this.authorIndex);

            this.replyNumIndex = getValidColumnIndex(path, table, "AbroadData", "replyNum");
            indicesMap.put("replyNum", this.replyNumIndex);

            this.clickNumIndex = getValidColumnIndex(path, table, "AbroadData", "clickNum");
            indicesMap.put("clickNum", this.clickNumIndex);

            this.copyFromIndex = getValidColumnIndex(path, table, "AbroadData", "copyFrom");
            indicesMap.put("copyFrom", this.copyFromIndex);

            this.isAbroadIndex = getValidColumnIndex(path, table, "AbroadData", "isAbroad");
            indicesMap.put("isAbroad", this.isAbroadIndex);

            this.isOverseaIndex = getValidColumnIndex(path, table, "AbroadData", "isOversea");
            indicesMap.put("isOversea", this.isOverseaIndex);

            this.imageUrlsIndex = getValidColumnIndex(path, table, "AbroadData", "imageUrls");
            indicesMap.put("imageUrls", this.imageUrlsIndex);

            this.summaryIndex = getValidColumnIndex(path, table, "AbroadData", "summary");
            indicesMap.put("summary", this.summaryIndex);

            this.negativeStatusIndex = getValidColumnIndex(path, table, "AbroadData", "negativeStatus");
            indicesMap.put("negativeStatus", this.negativeStatusIndex);

            this.repostNumIndex = getValidColumnIndex(path, table, "AbroadData", "repostNum");
            indicesMap.put("repostNum", this.repostNumIndex);

            this.profileUrlIndex = getValidColumnIndex(path, table, "AbroadData", "profileUrl");
            indicesMap.put("profileUrl", this.profileUrlIndex);

            this.nickNameIndex = getValidColumnIndex(path, table, "AbroadData", "nickName");
            indicesMap.put("nickName", this.nickNameIndex);

            this.providerIdIndex = getValidColumnIndex(path, table, "AbroadData", "providerId");
            indicesMap.put("providerId", this.providerIdIndex);

            this.srcNickNameIndex = getValidColumnIndex(path, table, "AbroadData", "srcNickName");
            indicesMap.put("srcNickName", this.srcNickNameIndex);

            this.srcContentIndex = getValidColumnIndex(path, table, "AbroadData", "srcContent");
            indicesMap.put("srcContent", this.srcContentIndex);

            this.srcPublishDateIndex = getValidColumnIndex(path, table, "AbroadData", "srcPublishDate");
            indicesMap.put("srcPublishDate", this.srcPublishDateIndex);

            this.srcRepostNumIndex = getValidColumnIndex(path, table, "AbroadData", "srcRepostNum");
            indicesMap.put("srcRepostNum", this.srcRepostNumIndex);

            this.srcCommentNumIndex = getValidColumnIndex(path, table, "AbroadData", "srcCommentNum");
            indicesMap.put("srcCommentNum", this.srcCommentNumIndex);

            this.srcPicUrlsIndex = getValidColumnIndex(path, table, "AbroadData", "srcPicUrls");
            indicesMap.put("srcPicUrls", this.srcPicUrlsIndex);

            this.srcPicPathsIndex = getValidColumnIndex(path, table, "AbroadData", "srcPicPaths");
            indicesMap.put("srcPicPaths", this.srcPicPathsIndex);

            this.logoIndex = getValidColumnIndex(path, table, "AbroadData", "logo");
            indicesMap.put("logo", this.logoIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final AbroadDataColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("docId");
        fieldNames.add("url");
        fieldNames.add("siteName");
        fieldNames.add("channelName");
        fieldNames.add("mediaType");
        fieldNames.add("title");
        fieldNames.add("content");
        fieldNames.add("publishDate");
        fieldNames.add("author");
        fieldNames.add("replyNum");
        fieldNames.add("clickNum");
        fieldNames.add("copyFrom");
        fieldNames.add("isAbroad");
        fieldNames.add("isOversea");
        fieldNames.add("imageUrls");
        fieldNames.add("summary");
        fieldNames.add("negativeStatus");
        fieldNames.add("repostNum");
        fieldNames.add("profileUrl");
        fieldNames.add("nickName");
        fieldNames.add("providerId");
        fieldNames.add("srcNickName");
        fieldNames.add("srcContent");
        fieldNames.add("srcPublishDate");
        fieldNames.add("srcRepostNum");
        fieldNames.add("srcCommentNum");
        fieldNames.add("srcPicUrls");
        fieldNames.add("srcPicPaths");
        fieldNames.add("logo");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AbroadDataRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (AbroadDataColumnInfo) columnInfo;
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
    public String getIsAbroad() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.isAbroadIndex);
    }

    @Override
    public void setIsAbroad(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.isAbroadIndex);
            return;
        }
        row.setString(columnInfo.isAbroadIndex, value);
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
    public int getNegativeStatus() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.negativeStatusIndex);
    }

    @Override
    public void setNegativeStatus(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.negativeStatusIndex, value);
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
    public String getLogo() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.logoIndex);
    }

    @Override
    public void setLogo(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.logoIndex);
            return;
        }
        row.setString(columnInfo.logoIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_AbroadData")) {
            Table table = transaction.getTable("class_AbroadData");
            table.addColumn(RealmFieldType.STRING, "docId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "url", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "siteName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "channelName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "mediaType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "publishDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "author", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "replyNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "clickNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "copyFrom", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "isAbroad", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "isOversea", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "imageUrls", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "summary", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "negativeStatus", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "repostNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "profileUrl", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "nickName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "providerId", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcNickName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcContent", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPublishDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcRepostNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcCommentNum", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPicUrls", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "srcPicPaths", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "logo", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_AbroadData");
    }

    public static AbroadDataColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_AbroadData")) {
            Table table = transaction.getTable("class_AbroadData");
            if (table.getColumnCount() != 29) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 29 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 29; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final AbroadDataColumnInfo columnInfo = new AbroadDataColumnInfo(transaction.getPath(), table);

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
            if (!columnTypes.containsKey("siteName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'siteName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("siteName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'siteName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.siteNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'siteName' is required. Either set @Required to field 'siteName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
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
            if (!columnTypes.containsKey("isAbroad")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'isAbroad' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("isAbroad") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'isAbroad' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.isAbroadIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'isAbroad' is required. Either set @Required to field 'isAbroad' or migrate using io.realm.internal.Table.convertColumnToNullable().");
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
            if (!columnTypes.containsKey("summary")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'summary' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("summary") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'summary' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.summaryIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'summary' is required. Either set @Required to field 'summary' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("negativeStatus")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'negativeStatus' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("negativeStatus") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'negativeStatus' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.negativeStatusIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'negativeStatus' does support null values in the existing Realm file. Use corresponding boxed type for field 'negativeStatus' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
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
            if (!columnTypes.containsKey("profileUrl")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'profileUrl' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("profileUrl") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'profileUrl' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.profileUrlIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'profileUrl' is required. Either set @Required to field 'profileUrl' or migrate using io.realm.internal.Table.convertColumnToNullable().");
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
            if (!columnTypes.containsKey("srcNickName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'srcNickName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("srcNickName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'srcNickName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.srcNickNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'srcNickName' is required. Either set @Required to field 'srcNickName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
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
            if (!columnTypes.containsKey("logo")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'logo' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("logo") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'logo' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.logoIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'logo' is required. Either set @Required to field 'logo' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The AbroadData class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_AbroadData";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static AbroadData createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        AbroadData obj = realm.createObject(AbroadData.class);
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
        if (json.has("siteName")) {
            if (json.isNull("siteName")) {
                obj.setSiteName(null);
            } else {
                obj.setSiteName((String) json.getString("siteName"));
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
        if (json.has("isAbroad")) {
            if (json.isNull("isAbroad")) {
                obj.setIsAbroad(null);
            } else {
                obj.setIsAbroad((String) json.getString("isAbroad"));
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
        if (json.has("summary")) {
            if (json.isNull("summary")) {
                obj.setSummary(null);
            } else {
                obj.setSummary((String) json.getString("summary"));
            }
        }
        if (json.has("negativeStatus")) {
            if (json.isNull("negativeStatus")) {
                throw new IllegalArgumentException("Trying to set non-nullable field negativeStatus to null.");
            } else {
                obj.setNegativeStatus((int) json.getInt("negativeStatus"));
            }
        }
        if (json.has("repostNum")) {
            if (json.isNull("repostNum")) {
                obj.setRepostNum(null);
            } else {
                obj.setRepostNum((String) json.getString("repostNum"));
            }
        }
        if (json.has("profileUrl")) {
            if (json.isNull("profileUrl")) {
                obj.setProfileUrl(null);
            } else {
                obj.setProfileUrl((String) json.getString("profileUrl"));
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
        if (json.has("srcNickName")) {
            if (json.isNull("srcNickName")) {
                obj.setSrcNickName(null);
            } else {
                obj.setSrcNickName((String) json.getString("srcNickName"));
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
        if (json.has("logo")) {
            if (json.isNull("logo")) {
                obj.setLogo(null);
            } else {
                obj.setLogo((String) json.getString("logo"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static AbroadData createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        AbroadData obj = realm.createObject(AbroadData.class);
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
            } else if (name.equals("siteName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSiteName(null);
                } else {
                    obj.setSiteName((String) reader.nextString());
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
            } else if (name.equals("isAbroad")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setIsAbroad(null);
                } else {
                    obj.setIsAbroad((String) reader.nextString());
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
            } else if (name.equals("summary")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSummary(null);
                } else {
                    obj.setSummary((String) reader.nextString());
                }
            } else if (name.equals("negativeStatus")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field negativeStatus to null.");
                } else {
                    obj.setNegativeStatus((int) reader.nextInt());
                }
            } else if (name.equals("repostNum")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setRepostNum(null);
                } else {
                    obj.setRepostNum((String) reader.nextString());
                }
            } else if (name.equals("profileUrl")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setProfileUrl(null);
                } else {
                    obj.setProfileUrl((String) reader.nextString());
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
            } else if (name.equals("srcNickName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSrcNickName(null);
                } else {
                    obj.setSrcNickName((String) reader.nextString());
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
            } else if (name.equals("logo")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setLogo(null);
                } else {
                    obj.setLogo((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static AbroadData copyOrUpdate(Realm realm, AbroadData object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static AbroadData copy(Realm realm, AbroadData newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        AbroadData realmObject = realm.createObject(AbroadData.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setDocId(newObject.getDocId());
        realmObject.setUrl(newObject.getUrl());
        realmObject.setSiteName(newObject.getSiteName());
        realmObject.setChannelName(newObject.getChannelName());
        realmObject.setMediaType(newObject.getMediaType());
        realmObject.setTitle(newObject.getTitle());
        realmObject.setContent(newObject.getContent());
        realmObject.setPublishDate(newObject.getPublishDate());
        realmObject.setAuthor(newObject.getAuthor());
        realmObject.setReplyNum(newObject.getReplyNum());
        realmObject.setClickNum(newObject.getClickNum());
        realmObject.setCopyFrom(newObject.getCopyFrom());
        realmObject.setIsAbroad(newObject.getIsAbroad());
        realmObject.setIsOversea(newObject.getIsOversea());
        realmObject.setImageUrls(newObject.getImageUrls());
        realmObject.setSummary(newObject.getSummary());
        realmObject.setNegativeStatus(newObject.getNegativeStatus());
        realmObject.setRepostNum(newObject.getRepostNum());
        realmObject.setProfileUrl(newObject.getProfileUrl());
        realmObject.setNickName(newObject.getNickName());
        realmObject.setProviderId(newObject.getProviderId());
        realmObject.setSrcNickName(newObject.getSrcNickName());
        realmObject.setSrcContent(newObject.getSrcContent());
        realmObject.setSrcPublishDate(newObject.getSrcPublishDate());
        realmObject.setSrcRepostNum(newObject.getSrcRepostNum());
        realmObject.setSrcCommentNum(newObject.getSrcCommentNum());
        realmObject.setSrcPicUrls(newObject.getSrcPicUrls());
        realmObject.setSrcPicPaths(newObject.getSrcPicPaths());
        realmObject.setLogo(newObject.getLogo());
        return realmObject;
    }

    public static AbroadData createDetachedCopy(AbroadData realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<AbroadData> cachedObject = (CacheData) cache.get(realmObject);
        AbroadData standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new AbroadData();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setDocId(realmObject.getDocId());
        standaloneObject.setUrl(realmObject.getUrl());
        standaloneObject.setSiteName(realmObject.getSiteName());
        standaloneObject.setChannelName(realmObject.getChannelName());
        standaloneObject.setMediaType(realmObject.getMediaType());
        standaloneObject.setTitle(realmObject.getTitle());
        standaloneObject.setContent(realmObject.getContent());
        standaloneObject.setPublishDate(realmObject.getPublishDate());
        standaloneObject.setAuthor(realmObject.getAuthor());
        standaloneObject.setReplyNum(realmObject.getReplyNum());
        standaloneObject.setClickNum(realmObject.getClickNum());
        standaloneObject.setCopyFrom(realmObject.getCopyFrom());
        standaloneObject.setIsAbroad(realmObject.getIsAbroad());
        standaloneObject.setIsOversea(realmObject.getIsOversea());
        standaloneObject.setImageUrls(realmObject.getImageUrls());
        standaloneObject.setSummary(realmObject.getSummary());
        standaloneObject.setNegativeStatus(realmObject.getNegativeStatus());
        standaloneObject.setRepostNum(realmObject.getRepostNum());
        standaloneObject.setProfileUrl(realmObject.getProfileUrl());
        standaloneObject.setNickName(realmObject.getNickName());
        standaloneObject.setProviderId(realmObject.getProviderId());
        standaloneObject.setSrcNickName(realmObject.getSrcNickName());
        standaloneObject.setSrcContent(realmObject.getSrcContent());
        standaloneObject.setSrcPublishDate(realmObject.getSrcPublishDate());
        standaloneObject.setSrcRepostNum(realmObject.getSrcRepostNum());
        standaloneObject.setSrcCommentNum(realmObject.getSrcCommentNum());
        standaloneObject.setSrcPicUrls(realmObject.getSrcPicUrls());
        standaloneObject.setSrcPicPaths(realmObject.getSrcPicPaths());
        standaloneObject.setLogo(realmObject.getLogo());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("AbroadData = [");
        stringBuilder.append("{docId:");
        stringBuilder.append(getDocId() != null ? getDocId() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{url:");
        stringBuilder.append(getUrl() != null ? getUrl() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{siteName:");
        stringBuilder.append(getSiteName() != null ? getSiteName() : "null");
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
        stringBuilder.append("{isAbroad:");
        stringBuilder.append(getIsAbroad() != null ? getIsAbroad() : "null");
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
        stringBuilder.append("{summary:");
        stringBuilder.append(getSummary() != null ? getSummary() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{negativeStatus:");
        stringBuilder.append(getNegativeStatus());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{repostNum:");
        stringBuilder.append(getRepostNum() != null ? getRepostNum() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{profileUrl:");
        stringBuilder.append(getProfileUrl() != null ? getProfileUrl() : "null");
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
        stringBuilder.append("{srcNickName:");
        stringBuilder.append(getSrcNickName() != null ? getSrcNickName() : "null");
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
        stringBuilder.append("{srcPicUrls:");
        stringBuilder.append(getSrcPicUrls() != null ? getSrcPicUrls() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{srcPicPaths:");
        stringBuilder.append(getSrcPicPaths() != null ? getSrcPicPaths() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{logo:");
        stringBuilder.append(getLogo() != null ? getLogo() : "null");
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
        AbroadDataRealmProxy aAbroadData = (AbroadDataRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aAbroadData.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aAbroadData.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aAbroadData.row.getIndex()) return false;

        return true;
    }

}
