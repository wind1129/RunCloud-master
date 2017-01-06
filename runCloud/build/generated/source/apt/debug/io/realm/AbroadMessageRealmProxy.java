package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.AbroadData;
import com.example.runcloud.entity.AbroadMessage;
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

public class AbroadMessageRealmProxy extends AbroadMessage
    implements RealmObjectProxy {

    static final class AbroadMessageColumnInfo extends ColumnInfo {

        public final long resultCodeIndex;
        public final long abroadDataListIndex;
        public final long messageIndex;

        AbroadMessageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.resultCodeIndex = getValidColumnIndex(path, table, "AbroadMessage", "resultCode");
            indicesMap.put("resultCode", this.resultCodeIndex);

            this.abroadDataListIndex = getValidColumnIndex(path, table, "AbroadMessage", "abroadDataList");
            indicesMap.put("abroadDataList", this.abroadDataListIndex);

            this.messageIndex = getValidColumnIndex(path, table, "AbroadMessage", "message");
            indicesMap.put("message", this.messageIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final AbroadMessageColumnInfo columnInfo;
    private RealmList<AbroadData> abroadDataListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("resultCode");
        fieldNames.add("abroadDataList");
        fieldNames.add("message");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    AbroadMessageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (AbroadMessageColumnInfo) columnInfo;
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
    public RealmList<AbroadData> getAbroadDataList() {
        realm.checkIfValid();
        // use the cached value if available
        if (abroadDataListRealmList != null) {
            return abroadDataListRealmList;
        } else {
            LinkView linkView = row.getLinkList(columnInfo.abroadDataListIndex);
            abroadDataListRealmList = new RealmList<AbroadData>(AbroadData.class, linkView, realm);
            return abroadDataListRealmList;
        }
    }

    @Override
    public void setAbroadDataList(RealmList<AbroadData> value) {
        realm.checkIfValid();
        LinkView links = row.getLinkList(columnInfo.abroadDataListIndex);
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
        if (!transaction.hasTable("class_AbroadMessage")) {
            Table table = transaction.getTable("class_AbroadMessage");
            table.addColumn(RealmFieldType.STRING, "resultCode", Table.NULLABLE);
            if (!transaction.hasTable("class_AbroadData")) {
                AbroadDataRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "abroadDataList", transaction.getTable("class_AbroadData"));
            table.addColumn(RealmFieldType.STRING, "message", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_AbroadMessage");
    }

    public static AbroadMessageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_AbroadMessage")) {
            Table table = transaction.getTable("class_AbroadMessage");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final AbroadMessageColumnInfo columnInfo = new AbroadMessageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("resultCode")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'resultCode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("resultCode") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'resultCode' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.resultCodeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'resultCode' is required. Either set @Required to field 'resultCode' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("abroadDataList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'abroadDataList'");
            }
            if (columnTypes.get("abroadDataList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'AbroadData' for field 'abroadDataList'");
            }
            if (!transaction.hasTable("class_AbroadData")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_AbroadData' for field 'abroadDataList'");
            }
            Table table_1 = transaction.getTable("class_AbroadData");
            if (!table.getLinkTarget(columnInfo.abroadDataListIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'abroadDataList': '" + table.getLinkTarget(columnInfo.abroadDataListIndex).getName() + "' expected - was '" + table_1.getName() + "'");
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
            throw new RealmMigrationNeededException(transaction.getPath(), "The AbroadMessage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_AbroadMessage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static AbroadMessage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        AbroadMessage obj = realm.createObject(AbroadMessage.class);
        if (json.has("resultCode")) {
            if (json.isNull("resultCode")) {
                obj.setResultCode(null);
            } else {
                obj.setResultCode((String) json.getString("resultCode"));
            }
        }
        if (json.has("abroadDataList")) {
            if (json.isNull("abroadDataList")) {
                obj.setAbroadDataList(null);
            } else {
                obj.getAbroadDataList().clear();
                JSONArray array = json.getJSONArray("abroadDataList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.runcloud.entity.AbroadData item = AbroadDataRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    obj.getAbroadDataList().add(item);
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
    public static AbroadMessage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        AbroadMessage obj = realm.createObject(AbroadMessage.class);
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
            } else if (name.equals("abroadDataList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setAbroadDataList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.runcloud.entity.AbroadData item = AbroadDataRealmProxy.createUsingJsonStream(realm, reader);
                        obj.getAbroadDataList().add(item);
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

    public static AbroadMessage copyOrUpdate(Realm realm, AbroadMessage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static AbroadMessage copy(Realm realm, AbroadMessage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        AbroadMessage realmObject = realm.createObject(AbroadMessage.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setResultCode(newObject.getResultCode());

        RealmList<AbroadData> abroadDataListList = newObject.getAbroadDataList();
        if (abroadDataListList != null) {
            RealmList<AbroadData> abroadDataListRealmList = realmObject.getAbroadDataList();
            for (int i = 0; i < abroadDataListList.size(); i++) {
                AbroadData abroadDataListItem = abroadDataListList.get(i);
                AbroadData cacheabroadDataList = (AbroadData) cache.get(abroadDataListItem);
                if (cacheabroadDataList != null) {
                    abroadDataListRealmList.add(cacheabroadDataList);
                } else {
                    abroadDataListRealmList.add(AbroadDataRealmProxy.copyOrUpdate(realm, abroadDataListList.get(i), update, cache));
                }
            }
        }

        realmObject.setMessage(newObject.getMessage());
        return realmObject;
    }

    public static AbroadMessage createDetachedCopy(AbroadMessage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<AbroadMessage> cachedObject = (CacheData) cache.get(realmObject);
        AbroadMessage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new AbroadMessage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setResultCode(realmObject.getResultCode());

        // Deep copy of abroadDataList
        if (currentDepth == maxDepth) {
            standaloneObject.setAbroadDataList(null);
        } else {
            RealmList<AbroadData> managedabroadDataListList = realmObject.getAbroadDataList();
            RealmList<AbroadData> standaloneabroadDataListList = new RealmList<AbroadData>();
            standaloneObject.setAbroadDataList(standaloneabroadDataListList);
            int nextDepth = currentDepth + 1;
            int size = managedabroadDataListList.size();
            for (int i = 0; i < size; i++) {
                AbroadData item = AbroadDataRealmProxy.createDetachedCopy(managedabroadDataListList.get(i), nextDepth, maxDepth, cache);
                standaloneabroadDataListList.add(item);
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
        StringBuilder stringBuilder = new StringBuilder("AbroadMessage = [");
        stringBuilder.append("{resultCode:");
        stringBuilder.append(getResultCode() != null ? getResultCode() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{abroadDataList:");
        stringBuilder.append("RealmList<AbroadData>[").append(getAbroadDataList().size()).append("]");
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
        AbroadMessageRealmProxy aAbroadMessage = (AbroadMessageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aAbroadMessage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aAbroadMessage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aAbroadMessage.row.getIndex()) return false;

        return true;
    }

}
