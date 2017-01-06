package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.NetizenData;
import com.example.runcloud.entity.NetizenMessage;
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

public class NetizenMessageRealmProxy extends NetizenMessage
    implements RealmObjectProxy {

    static final class NetizenMessageColumnInfo extends ColumnInfo {

        public final long resultCodeIndex;
        public final long netizenDataListIndex;
        public final long messageIndex;

        NetizenMessageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.resultCodeIndex = getValidColumnIndex(path, table, "NetizenMessage", "resultCode");
            indicesMap.put("resultCode", this.resultCodeIndex);

            this.netizenDataListIndex = getValidColumnIndex(path, table, "NetizenMessage", "netizenDataList");
            indicesMap.put("netizenDataList", this.netizenDataListIndex);

            this.messageIndex = getValidColumnIndex(path, table, "NetizenMessage", "message");
            indicesMap.put("message", this.messageIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final NetizenMessageColumnInfo columnInfo;
    private RealmList<NetizenData> netizenDataListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("resultCode");
        fieldNames.add("netizenDataList");
        fieldNames.add("message");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    NetizenMessageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (NetizenMessageColumnInfo) columnInfo;
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
    public RealmList<NetizenData> getNetizenDataList() {
        realm.checkIfValid();
        // use the cached value if available
        if (netizenDataListRealmList != null) {
            return netizenDataListRealmList;
        } else {
            LinkView linkView = row.getLinkList(columnInfo.netizenDataListIndex);
            netizenDataListRealmList = new RealmList<NetizenData>(NetizenData.class, linkView, realm);
            return netizenDataListRealmList;
        }
    }

    @Override
    public void setNetizenDataList(RealmList<NetizenData> value) {
        realm.checkIfValid();
        LinkView links = row.getLinkList(columnInfo.netizenDataListIndex);
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
        if (!transaction.hasTable("class_NetizenMessage")) {
            Table table = transaction.getTable("class_NetizenMessage");
            table.addColumn(RealmFieldType.STRING, "resultCode", Table.NULLABLE);
            if (!transaction.hasTable("class_NetizenData")) {
                NetizenDataRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "netizenDataList", transaction.getTable("class_NetizenData"));
            table.addColumn(RealmFieldType.STRING, "message", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_NetizenMessage");
    }

    public static NetizenMessageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_NetizenMessage")) {
            Table table = transaction.getTable("class_NetizenMessage");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final NetizenMessageColumnInfo columnInfo = new NetizenMessageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("resultCode")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'resultCode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("resultCode") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'resultCode' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.resultCodeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'resultCode' is required. Either set @Required to field 'resultCode' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("netizenDataList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'netizenDataList'");
            }
            if (columnTypes.get("netizenDataList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'NetizenData' for field 'netizenDataList'");
            }
            if (!transaction.hasTable("class_NetizenData")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_NetizenData' for field 'netizenDataList'");
            }
            Table table_1 = transaction.getTable("class_NetizenData");
            if (!table.getLinkTarget(columnInfo.netizenDataListIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'netizenDataList': '" + table.getLinkTarget(columnInfo.netizenDataListIndex).getName() + "' expected - was '" + table_1.getName() + "'");
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
            throw new RealmMigrationNeededException(transaction.getPath(), "The NetizenMessage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_NetizenMessage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static NetizenMessage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        NetizenMessage obj = realm.createObject(NetizenMessage.class);
        if (json.has("resultCode")) {
            if (json.isNull("resultCode")) {
                obj.setResultCode(null);
            } else {
                obj.setResultCode((String) json.getString("resultCode"));
            }
        }
        if (json.has("netizenDataList")) {
            if (json.isNull("netizenDataList")) {
                obj.setNetizenDataList(null);
            } else {
                obj.getNetizenDataList().clear();
                JSONArray array = json.getJSONArray("netizenDataList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.runcloud.entity.NetizenData item = NetizenDataRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    obj.getNetizenDataList().add(item);
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
    public static NetizenMessage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        NetizenMessage obj = realm.createObject(NetizenMessage.class);
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
            } else if (name.equals("netizenDataList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setNetizenDataList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.runcloud.entity.NetizenData item = NetizenDataRealmProxy.createUsingJsonStream(realm, reader);
                        obj.getNetizenDataList().add(item);
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

    public static NetizenMessage copyOrUpdate(Realm realm, NetizenMessage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static NetizenMessage copy(Realm realm, NetizenMessage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        NetizenMessage realmObject = realm.createObject(NetizenMessage.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setResultCode(newObject.getResultCode());

        RealmList<NetizenData> netizenDataListList = newObject.getNetizenDataList();
        if (netizenDataListList != null) {
            RealmList<NetizenData> netizenDataListRealmList = realmObject.getNetizenDataList();
            for (int i = 0; i < netizenDataListList.size(); i++) {
                NetizenData netizenDataListItem = netizenDataListList.get(i);
                NetizenData cachenetizenDataList = (NetizenData) cache.get(netizenDataListItem);
                if (cachenetizenDataList != null) {
                    netizenDataListRealmList.add(cachenetizenDataList);
                } else {
                    netizenDataListRealmList.add(NetizenDataRealmProxy.copyOrUpdate(realm, netizenDataListList.get(i), update, cache));
                }
            }
        }

        realmObject.setMessage(newObject.getMessage());
        return realmObject;
    }

    public static NetizenMessage createDetachedCopy(NetizenMessage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<NetizenMessage> cachedObject = (CacheData) cache.get(realmObject);
        NetizenMessage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new NetizenMessage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setResultCode(realmObject.getResultCode());

        // Deep copy of netizenDataList
        if (currentDepth == maxDepth) {
            standaloneObject.setNetizenDataList(null);
        } else {
            RealmList<NetizenData> managednetizenDataListList = realmObject.getNetizenDataList();
            RealmList<NetizenData> standalonenetizenDataListList = new RealmList<NetizenData>();
            standaloneObject.setNetizenDataList(standalonenetizenDataListList);
            int nextDepth = currentDepth + 1;
            int size = managednetizenDataListList.size();
            for (int i = 0; i < size; i++) {
                NetizenData item = NetizenDataRealmProxy.createDetachedCopy(managednetizenDataListList.get(i), nextDepth, maxDepth, cache);
                standalonenetizenDataListList.add(item);
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
        StringBuilder stringBuilder = new StringBuilder("NetizenMessage = [");
        stringBuilder.append("{resultCode:");
        stringBuilder.append(getResultCode() != null ? getResultCode() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{netizenDataList:");
        stringBuilder.append("RealmList<NetizenData>[").append(getNetizenDataList().size()).append("]");
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
        NetizenMessageRealmProxy aNetizenMessage = (NetizenMessageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aNetizenMessage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aNetizenMessage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aNetizenMessage.row.getIndex()) return false;

        return true;
    }

}
