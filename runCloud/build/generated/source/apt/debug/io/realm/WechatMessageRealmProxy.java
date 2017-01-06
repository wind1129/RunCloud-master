package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.WechatData;
import com.example.runcloud.entity.WechatMessage;
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

public class WechatMessageRealmProxy extends WechatMessage
    implements RealmObjectProxy {

    static final class WechatMessageColumnInfo extends ColumnInfo {

        public final long resultCodeIndex;
        public final long wechatDataListIndex;
        public final long messageIndex;

        WechatMessageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.resultCodeIndex = getValidColumnIndex(path, table, "WechatMessage", "resultCode");
            indicesMap.put("resultCode", this.resultCodeIndex);

            this.wechatDataListIndex = getValidColumnIndex(path, table, "WechatMessage", "wechatDataList");
            indicesMap.put("wechatDataList", this.wechatDataListIndex);

            this.messageIndex = getValidColumnIndex(path, table, "WechatMessage", "message");
            indicesMap.put("message", this.messageIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final WechatMessageColumnInfo columnInfo;
    private RealmList<WechatData> wechatDataListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("resultCode");
        fieldNames.add("wechatDataList");
        fieldNames.add("message");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    WechatMessageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (WechatMessageColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public String getResultCode() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.resultCodeIndex);
    }

    @Override
    public void setResultCode(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.resultCodeIndex);
            return;
        }
        row.setString(columnInfo.resultCodeIndex, value);
    }

    @Override
    public RealmList<WechatData> getWechatDataList() {
        realm.checkIfValid();
        // use the cached value if available
        if (wechatDataListRealmList != null) {
            return wechatDataListRealmList;
        } else {
            LinkView linkView = row.getLinkList(columnInfo.wechatDataListIndex);
            wechatDataListRealmList = new RealmList<WechatData>(WechatData.class, linkView, realm);
            return wechatDataListRealmList;
        }
    }

    @Override
    public void setWechatDataList(RealmList<WechatData> value) {
        realm.checkIfValid();
        LinkView links = row.getLinkList(columnInfo.wechatDataListIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmObject linkedObject : (RealmList<? extends RealmObject>) value) {
            if (!linkedObject.isValid()) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (linkedObject.realm != this.realm) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(linkedObject.row.getIndex());
        }
    }

    @Override
    @SuppressWarnings("cast")
    public String getMessage() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.messageIndex);
    }

    @Override
    public void setMessage(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.messageIndex);
            return;
        }
        row.setString(columnInfo.messageIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_WechatMessage")) {
            Table table = transaction.getTable("class_WechatMessage");
            table.addColumn(RealmFieldType.STRING, "resultCode", Table.NULLABLE);
            if (!transaction.hasTable("class_WechatData")) {
                WechatDataRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "wechatDataList", transaction.getTable("class_WechatData"));
            table.addColumn(RealmFieldType.STRING, "message", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_WechatMessage");
    }

    public static WechatMessageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_WechatMessage")) {
            Table table = transaction.getTable("class_WechatMessage");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final WechatMessageColumnInfo columnInfo = new WechatMessageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("resultCode")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'resultCode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("resultCode") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'resultCode' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.resultCodeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'resultCode' is required. Either set @Required to field 'resultCode' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("wechatDataList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'wechatDataList'");
            }
            if (columnTypes.get("wechatDataList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'WechatData' for field 'wechatDataList'");
            }
            if (!transaction.hasTable("class_WechatData")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_WechatData' for field 'wechatDataList'");
            }
            Table table_1 = transaction.getTable("class_WechatData");
            if (!table.getLinkTarget(columnInfo.wechatDataListIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'wechatDataList': '" + table.getLinkTarget(columnInfo.wechatDataListIndex).getName() + "' expected - was '" + table_1.getName() + "'");
            }
            if (!columnTypes.containsKey("message")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'message' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("message") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'message' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.messageIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'message' is required. Either set @Required to field 'message' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The WechatMessage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_WechatMessage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static WechatMessage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        WechatMessage obj = realm.createObject(WechatMessage.class);
        if (json.has("resultCode")) {
            if (json.isNull("resultCode")) {
                obj.setResultCode(null);
            } else {
                obj.setResultCode((String) json.getString("resultCode"));
            }
        }
        if (json.has("wechatDataList")) {
            if (json.isNull("wechatDataList")) {
                obj.setWechatDataList(null);
            } else {
                obj.getWechatDataList().clear();
                JSONArray array = json.getJSONArray("wechatDataList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.runcloud.entity.WechatData item = WechatDataRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    obj.getWechatDataList().add(item);
                }
            }
        }
        if (json.has("message")) {
            if (json.isNull("message")) {
                obj.setMessage(null);
            } else {
                obj.setMessage((String) json.getString("message"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static WechatMessage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        WechatMessage obj = realm.createObject(WechatMessage.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resultCode")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setResultCode(null);
                } else {
                    obj.setResultCode((String) reader.nextString());
                }
            } else if (name.equals("wechatDataList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setWechatDataList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.runcloud.entity.WechatData item = WechatDataRealmProxy.createUsingJsonStream(realm, reader);
                        obj.getWechatDataList().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("message")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setMessage(null);
                } else {
                    obj.setMessage((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static WechatMessage copyOrUpdate(Realm realm, WechatMessage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static WechatMessage copy(Realm realm, WechatMessage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        WechatMessage realmObject = realm.createObject(WechatMessage.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setResultCode(newObject.getResultCode());

        RealmList<WechatData> wechatDataListList = newObject.getWechatDataList();
        if (wechatDataListList != null) {
            RealmList<WechatData> wechatDataListRealmList = realmObject.getWechatDataList();
            for (int i = 0; i < wechatDataListList.size(); i++) {
                WechatData wechatDataListItem = wechatDataListList.get(i);
                WechatData cachewechatDataList = (WechatData) cache.get(wechatDataListItem);
                if (cachewechatDataList != null) {
                    wechatDataListRealmList.add(cachewechatDataList);
                } else {
                    wechatDataListRealmList.add(WechatDataRealmProxy.copyOrUpdate(realm, wechatDataListList.get(i), update, cache));
                }
            }
        }

        realmObject.setMessage(newObject.getMessage());
        return realmObject;
    }

    public static WechatMessage createDetachedCopy(WechatMessage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<WechatMessage> cachedObject = (CacheData) cache.get(realmObject);
        WechatMessage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new WechatMessage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setResultCode(realmObject.getResultCode());

        // Deep copy of wechatDataList
        if (currentDepth == maxDepth) {
            standaloneObject.setWechatDataList(null);
        } else {
            RealmList<WechatData> managedwechatDataListList = realmObject.getWechatDataList();
            RealmList<WechatData> standalonewechatDataListList = new RealmList<WechatData>();
            standaloneObject.setWechatDataList(standalonewechatDataListList);
            int nextDepth = currentDepth + 1;
            int size = managedwechatDataListList.size();
            for (int i = 0; i < size; i++) {
                WechatData item = WechatDataRealmProxy.createDetachedCopy(managedwechatDataListList.get(i), nextDepth, maxDepth, cache);
                standalonewechatDataListList.add(item);
            }
        }
        standaloneObject.setMessage(realmObject.getMessage());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("WechatMessage = [");
        stringBuilder.append("{resultCode:");
        stringBuilder.append(getResultCode() != null ? getResultCode() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{wechatDataList:");
        stringBuilder.append("RealmList<WechatData>[").append(getWechatDataList().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{message:");
        stringBuilder.append(getMessage() != null ? getMessage() : "null");
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
        WechatMessageRealmProxy aWechatMessage = (WechatMessageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aWechatMessage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aWechatMessage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aWechatMessage.row.getIndex()) return false;

        return true;
    }

}
