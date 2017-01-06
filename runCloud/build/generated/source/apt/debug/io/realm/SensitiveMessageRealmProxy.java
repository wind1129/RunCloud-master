package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.DataList;
import com.example.runcloud.entity.SensitiveMessage;
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

public class SensitiveMessageRealmProxy extends SensitiveMessage
    implements RealmObjectProxy {

    static final class SensitiveMessageColumnInfo extends ColumnInfo {

        public final long resultCodeIndex;
        public final long sensitiveListIndex;
        public final long messageIndex;

        SensitiveMessageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.resultCodeIndex = getValidColumnIndex(path, table, "SensitiveMessage", "resultCode");
            indicesMap.put("resultCode", this.resultCodeIndex);

            this.sensitiveListIndex = getValidColumnIndex(path, table, "SensitiveMessage", "sensitiveList");
            indicesMap.put("sensitiveList", this.sensitiveListIndex);

            this.messageIndex = getValidColumnIndex(path, table, "SensitiveMessage", "message");
            indicesMap.put("message", this.messageIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final SensitiveMessageColumnInfo columnInfo;
    private RealmList<DataList> sensitiveListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("resultCode");
        fieldNames.add("sensitiveList");
        fieldNames.add("message");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SensitiveMessageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (SensitiveMessageColumnInfo) columnInfo;
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
    public RealmList<DataList> getSensitiveList() {
        realm.checkIfValid();
        // use the cached value if available
        if (sensitiveListRealmList != null) {
            return sensitiveListRealmList;
        } else {
            LinkView linkView = row.getLinkList(columnInfo.sensitiveListIndex);
            sensitiveListRealmList = new RealmList<DataList>(DataList.class, linkView, realm);
            return sensitiveListRealmList;
        }
    }

    @Override
    public void setSensitiveList(RealmList<DataList> value) {
        realm.checkIfValid();
        LinkView links = row.getLinkList(columnInfo.sensitiveListIndex);
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
        if (!transaction.hasTable("class_SensitiveMessage")) {
            Table table = transaction.getTable("class_SensitiveMessage");
            table.addColumn(RealmFieldType.STRING, "resultCode", Table.NULLABLE);
            if (!transaction.hasTable("class_DataList")) {
                DataListRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "sensitiveList", transaction.getTable("class_DataList"));
            table.addColumn(RealmFieldType.STRING, "message", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_SensitiveMessage");
    }

    public static SensitiveMessageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_SensitiveMessage")) {
            Table table = transaction.getTable("class_SensitiveMessage");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final SensitiveMessageColumnInfo columnInfo = new SensitiveMessageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("resultCode")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'resultCode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("resultCode") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'resultCode' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.resultCodeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'resultCode' is required. Either set @Required to field 'resultCode' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("sensitiveList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'sensitiveList'");
            }
            if (columnTypes.get("sensitiveList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'DataList' for field 'sensitiveList'");
            }
            if (!transaction.hasTable("class_DataList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_DataList' for field 'sensitiveList'");
            }
            Table table_1 = transaction.getTable("class_DataList");
            if (!table.getLinkTarget(columnInfo.sensitiveListIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'sensitiveList': '" + table.getLinkTarget(columnInfo.sensitiveListIndex).getName() + "' expected - was '" + table_1.getName() + "'");
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
            throw new RealmMigrationNeededException(transaction.getPath(), "The SensitiveMessage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_SensitiveMessage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static SensitiveMessage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        SensitiveMessage obj = realm.createObject(SensitiveMessage.class);
        if (json.has("resultCode")) {
            if (json.isNull("resultCode")) {
                obj.setResultCode(null);
            } else {
                obj.setResultCode((String) json.getString("resultCode"));
            }
        }
        if (json.has("sensitiveList")) {
            if (json.isNull("sensitiveList")) {
                obj.setSensitiveList(null);
            } else {
                obj.getSensitiveList().clear();
                JSONArray array = json.getJSONArray("sensitiveList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.runcloud.entity.DataList item = DataListRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    obj.getSensitiveList().add(item);
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
    public static SensitiveMessage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        SensitiveMessage obj = realm.createObject(SensitiveMessage.class);
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
            } else if (name.equals("sensitiveList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSensitiveList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.runcloud.entity.DataList item = DataListRealmProxy.createUsingJsonStream(realm, reader);
                        obj.getSensitiveList().add(item);
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

    public static SensitiveMessage copyOrUpdate(Realm realm, SensitiveMessage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static SensitiveMessage copy(Realm realm, SensitiveMessage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        SensitiveMessage realmObject = realm.createObject(SensitiveMessage.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setResultCode(newObject.getResultCode());

        RealmList<DataList> sensitiveListList = newObject.getSensitiveList();
        if (sensitiveListList != null) {
            RealmList<DataList> sensitiveListRealmList = realmObject.getSensitiveList();
            for (int i = 0; i < sensitiveListList.size(); i++) {
                DataList sensitiveListItem = sensitiveListList.get(i);
                DataList cachesensitiveList = (DataList) cache.get(sensitiveListItem);
                if (cachesensitiveList != null) {
                    sensitiveListRealmList.add(cachesensitiveList);
                } else {
                    sensitiveListRealmList.add(DataListRealmProxy.copyOrUpdate(realm, sensitiveListList.get(i), update, cache));
                }
            }
        }

        realmObject.setMessage(newObject.getMessage());
        return realmObject;
    }

    public static SensitiveMessage createDetachedCopy(SensitiveMessage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<SensitiveMessage> cachedObject = (CacheData) cache.get(realmObject);
        SensitiveMessage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new SensitiveMessage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setResultCode(realmObject.getResultCode());

        // Deep copy of sensitiveList
        if (currentDepth == maxDepth) {
            standaloneObject.setSensitiveList(null);
        } else {
            RealmList<DataList> managedsensitiveListList = realmObject.getSensitiveList();
            RealmList<DataList> standalonesensitiveListList = new RealmList<DataList>();
            standaloneObject.setSensitiveList(standalonesensitiveListList);
            int nextDepth = currentDepth + 1;
            int size = managedsensitiveListList.size();
            for (int i = 0; i < size; i++) {
                DataList item = DataListRealmProxy.createDetachedCopy(managedsensitiveListList.get(i), nextDepth, maxDepth, cache);
                standalonesensitiveListList.add(item);
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
        StringBuilder stringBuilder = new StringBuilder("SensitiveMessage = [");
        stringBuilder.append("{resultCode:");
        stringBuilder.append(getResultCode() != null ? getResultCode() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sensitiveList:");
        stringBuilder.append("RealmList<DataList>[").append(getSensitiveList().size()).append("]");
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
        SensitiveMessageRealmProxy aSensitiveMessage = (SensitiveMessageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aSensitiveMessage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aSensitiveMessage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aSensitiveMessage.row.getIndex()) return false;

        return true;
    }

}
