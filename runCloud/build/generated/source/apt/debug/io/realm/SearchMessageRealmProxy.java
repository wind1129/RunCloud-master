package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.runcloud.entity.SearchData;
import com.example.runcloud.entity.SearchMessage;
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

public class SearchMessageRealmProxy extends SearchMessage
    implements RealmObjectProxy {

    static final class SearchMessageColumnInfo extends ColumnInfo {

        public final long resultCodeIndex;
        public final long searchDataListIndex;
        public final long messageIndex;

        SearchMessageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.resultCodeIndex = getValidColumnIndex(path, table, "SearchMessage", "resultCode");
            indicesMap.put("resultCode", this.resultCodeIndex);

            this.searchDataListIndex = getValidColumnIndex(path, table, "SearchMessage", "searchDataList");
            indicesMap.put("searchDataList", this.searchDataListIndex);

            this.messageIndex = getValidColumnIndex(path, table, "SearchMessage", "message");
            indicesMap.put("message", this.messageIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final SearchMessageColumnInfo columnInfo;
    private RealmList<SearchData> searchDataListRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("resultCode");
        fieldNames.add("searchDataList");
        fieldNames.add("message");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SearchMessageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (SearchMessageColumnInfo) columnInfo;
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
    public RealmList<SearchData> getSearchDataList() {
        realm.checkIfValid();
        // use the cached value if available
        if (searchDataListRealmList != null) {
            return searchDataListRealmList;
        } else {
            LinkView linkView = row.getLinkList(columnInfo.searchDataListIndex);
            searchDataListRealmList = new RealmList<SearchData>(SearchData.class, linkView, realm);
            return searchDataListRealmList;
        }
    }

    @Override
    public void setSearchDataList(RealmList<SearchData> value) {
        realm.checkIfValid();
        LinkView links = row.getLinkList(columnInfo.searchDataListIndex);
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
        if (!transaction.hasTable("class_SearchMessage")) {
            Table table = transaction.getTable("class_SearchMessage");
            table.addColumn(RealmFieldType.STRING, "resultCode", Table.NULLABLE);
            if (!transaction.hasTable("class_SearchData")) {
                SearchDataRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "searchDataList", transaction.getTable("class_SearchData"));
            table.addColumn(RealmFieldType.STRING, "message", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_SearchMessage");
    }

    public static SearchMessageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_SearchMessage")) {
            Table table = transaction.getTable("class_SearchMessage");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final SearchMessageColumnInfo columnInfo = new SearchMessageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("resultCode")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'resultCode' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("resultCode") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'resultCode' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.resultCodeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'resultCode' is required. Either set @Required to field 'resultCode' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("searchDataList")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'searchDataList'");
            }
            if (columnTypes.get("searchDataList") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'SearchData' for field 'searchDataList'");
            }
            if (!transaction.hasTable("class_SearchData")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_SearchData' for field 'searchDataList'");
            }
            Table table_1 = transaction.getTable("class_SearchData");
            if (!table.getLinkTarget(columnInfo.searchDataListIndex).hasSameSchema(table_1)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'searchDataList': '" + table.getLinkTarget(columnInfo.searchDataListIndex).getName() + "' expected - was '" + table_1.getName() + "'");
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
            throw new RealmMigrationNeededException(transaction.getPath(), "The SearchMessage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_SearchMessage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static SearchMessage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        SearchMessage obj = realm.createObject(SearchMessage.class);
        if (json.has("resultCode")) {
            if (json.isNull("resultCode")) {
                obj.setResultCode(null);
            } else {
                obj.setResultCode((String) json.getString("resultCode"));
            }
        }
        if (json.has("searchDataList")) {
            if (json.isNull("searchDataList")) {
                obj.setSearchDataList(null);
            } else {
                obj.getSearchDataList().clear();
                JSONArray array = json.getJSONArray("searchDataList");
                for (int i = 0; i < array.length(); i++) {
                    com.example.runcloud.entity.SearchData item = SearchDataRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    obj.getSearchDataList().add(item);
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
    public static SearchMessage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        SearchMessage obj = realm.createObject(SearchMessage.class);
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
            } else if (name.equals("searchDataList")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSearchDataList(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.example.runcloud.entity.SearchData item = SearchDataRealmProxy.createUsingJsonStream(realm, reader);
                        obj.getSearchDataList().add(item);
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

    public static SearchMessage copyOrUpdate(Realm realm, SearchMessage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static SearchMessage copy(Realm realm, SearchMessage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        SearchMessage realmObject = realm.createObject(SearchMessage.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setResultCode(newObject.getResultCode());

        RealmList<SearchData> searchDataListList = newObject.getSearchDataList();
        if (searchDataListList != null) {
            RealmList<SearchData> searchDataListRealmList = realmObject.getSearchDataList();
            for (int i = 0; i < searchDataListList.size(); i++) {
                SearchData searchDataListItem = searchDataListList.get(i);
                SearchData cachesearchDataList = (SearchData) cache.get(searchDataListItem);
                if (cachesearchDataList != null) {
                    searchDataListRealmList.add(cachesearchDataList);
                } else {
                    searchDataListRealmList.add(SearchDataRealmProxy.copyOrUpdate(realm, searchDataListList.get(i), update, cache));
                }
            }
        }

        realmObject.setMessage(newObject.getMessage());
        return realmObject;
    }

    public static SearchMessage createDetachedCopy(SearchMessage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<SearchMessage> cachedObject = (CacheData) cache.get(realmObject);
        SearchMessage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new SearchMessage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setResultCode(realmObject.getResultCode());

        // Deep copy of searchDataList
        if (currentDepth == maxDepth) {
            standaloneObject.setSearchDataList(null);
        } else {
            RealmList<SearchData> managedsearchDataListList = realmObject.getSearchDataList();
            RealmList<SearchData> standalonesearchDataListList = new RealmList<SearchData>();
            standaloneObject.setSearchDataList(standalonesearchDataListList);
            int nextDepth = currentDepth + 1;
            int size = managedsearchDataListList.size();
            for (int i = 0; i < size; i++) {
                SearchData item = SearchDataRealmProxy.createDetachedCopy(managedsearchDataListList.get(i), nextDepth, maxDepth, cache);
                standalonesearchDataListList.add(item);
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
        StringBuilder stringBuilder = new StringBuilder("SearchMessage = [");
        stringBuilder.append("{resultCode:");
        stringBuilder.append(getResultCode() != null ? getResultCode() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{searchDataList:");
        stringBuilder.append("RealmList<SearchData>[").append(getSearchDataList().size()).append("]");
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
        SearchMessageRealmProxy aSearchMessage = (SearchMessageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aSearchMessage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aSearchMessage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aSearchMessage.row.getIndex()) return false;

        return true;
    }

}
