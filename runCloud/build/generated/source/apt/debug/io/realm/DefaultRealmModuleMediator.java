package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.runcloud.entity.AbroadData;
import com.example.runcloud.entity.AbroadMessage;
import com.example.runcloud.entity.DataList;
import com.example.runcloud.entity.Doc;
import com.example.runcloud.entity.NetizenData;
import com.example.runcloud.entity.NetizenMessage;
import com.example.runcloud.entity.SearchData;
import com.example.runcloud.entity.SearchMessage;
import com.example.runcloud.entity.SensitiveMessage;
import com.example.runcloud.entity.WechatData;
import com.example.runcloud.entity.WechatMessage;
import com.example.runcloud.mvp.model.RealmString;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmObject>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmObject>> modelClasses = new HashSet<Class<? extends RealmObject>>();
        modelClasses.add(DataList.class);
        modelClasses.add(NetizenData.class);
        modelClasses.add(WechatMessage.class);
        modelClasses.add(Doc.class);
        modelClasses.add(SearchData.class);
        modelClasses.add(SensitiveMessage.class);
        modelClasses.add(RealmString.class);
        modelClasses.add(AbroadMessage.class);
        modelClasses.add(SearchMessage.class);
        modelClasses.add(WechatData.class);
        modelClasses.add(AbroadData.class);
        modelClasses.add(NetizenMessage.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return DataListRealmProxy.initTable(transaction);
        } else if (clazz.equals(NetizenData.class)) {
            return NetizenDataRealmProxy.initTable(transaction);
        } else if (clazz.equals(WechatMessage.class)) {
            return WechatMessageRealmProxy.initTable(transaction);
        } else if (clazz.equals(Doc.class)) {
            return DocRealmProxy.initTable(transaction);
        } else if (clazz.equals(SearchData.class)) {
            return SearchDataRealmProxy.initTable(transaction);
        } else if (clazz.equals(SensitiveMessage.class)) {
            return SensitiveMessageRealmProxy.initTable(transaction);
        } else if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.initTable(transaction);
        } else if (clazz.equals(AbroadMessage.class)) {
            return AbroadMessageRealmProxy.initTable(transaction);
        } else if (clazz.equals(SearchMessage.class)) {
            return SearchMessageRealmProxy.initTable(transaction);
        } else if (clazz.equals(WechatData.class)) {
            return WechatDataRealmProxy.initTable(transaction);
        } else if (clazz.equals(AbroadData.class)) {
            return AbroadDataRealmProxy.initTable(transaction);
        } else if (clazz.equals(NetizenMessage.class)) {
            return NetizenMessageRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return DataListRealmProxy.validateTable(transaction);
        } else if (clazz.equals(NetizenData.class)) {
            return NetizenDataRealmProxy.validateTable(transaction);
        } else if (clazz.equals(WechatMessage.class)) {
            return WechatMessageRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Doc.class)) {
            return DocRealmProxy.validateTable(transaction);
        } else if (clazz.equals(SearchData.class)) {
            return SearchDataRealmProxy.validateTable(transaction);
        } else if (clazz.equals(SensitiveMessage.class)) {
            return SensitiveMessageRealmProxy.validateTable(transaction);
        } else if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.validateTable(transaction);
        } else if (clazz.equals(AbroadMessage.class)) {
            return AbroadMessageRealmProxy.validateTable(transaction);
        } else if (clazz.equals(SearchMessage.class)) {
            return SearchMessageRealmProxy.validateTable(transaction);
        } else if (clazz.equals(WechatData.class)) {
            return WechatDataRealmProxy.validateTable(transaction);
        } else if (clazz.equals(AbroadData.class)) {
            return AbroadDataRealmProxy.validateTable(transaction);
        } else if (clazz.equals(NetizenMessage.class)) {
            return NetizenMessageRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return DataListRealmProxy.getFieldNames();
        } else if (clazz.equals(NetizenData.class)) {
            return NetizenDataRealmProxy.getFieldNames();
        } else if (clazz.equals(WechatMessage.class)) {
            return WechatMessageRealmProxy.getFieldNames();
        } else if (clazz.equals(Doc.class)) {
            return DocRealmProxy.getFieldNames();
        } else if (clazz.equals(SearchData.class)) {
            return SearchDataRealmProxy.getFieldNames();
        } else if (clazz.equals(SensitiveMessage.class)) {
            return SensitiveMessageRealmProxy.getFieldNames();
        } else if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getFieldNames();
        } else if (clazz.equals(AbroadMessage.class)) {
            return AbroadMessageRealmProxy.getFieldNames();
        } else if (clazz.equals(SearchMessage.class)) {
            return SearchMessageRealmProxy.getFieldNames();
        } else if (clazz.equals(WechatData.class)) {
            return WechatDataRealmProxy.getFieldNames();
        } else if (clazz.equals(AbroadData.class)) {
            return AbroadDataRealmProxy.getFieldNames();
        } else if (clazz.equals(NetizenMessage.class)) {
            return NetizenMessageRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return DataListRealmProxy.getTableName();
        } else if (clazz.equals(NetizenData.class)) {
            return NetizenDataRealmProxy.getTableName();
        } else if (clazz.equals(WechatMessage.class)) {
            return WechatMessageRealmProxy.getTableName();
        } else if (clazz.equals(Doc.class)) {
            return DocRealmProxy.getTableName();
        } else if (clazz.equals(SearchData.class)) {
            return SearchDataRealmProxy.getTableName();
        } else if (clazz.equals(SensitiveMessage.class)) {
            return SensitiveMessageRealmProxy.getTableName();
        } else if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getTableName();
        } else if (clazz.equals(AbroadMessage.class)) {
            return AbroadMessageRealmProxy.getTableName();
        } else if (clazz.equals(SearchMessage.class)) {
            return SearchMessageRealmProxy.getTableName();
        } else if (clazz.equals(WechatData.class)) {
            return WechatDataRealmProxy.getTableName();
        } else if (clazz.equals(AbroadData.class)) {
            return AbroadDataRealmProxy.getTableName();
        } else if (clazz.equals(NetizenMessage.class)) {
            return NetizenMessageRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return clazz.cast(new DataListRealmProxy(columnInfo));
        } else if (clazz.equals(NetizenData.class)) {
            return clazz.cast(new NetizenDataRealmProxy(columnInfo));
        } else if (clazz.equals(WechatMessage.class)) {
            return clazz.cast(new WechatMessageRealmProxy(columnInfo));
        } else if (clazz.equals(Doc.class)) {
            return clazz.cast(new DocRealmProxy(columnInfo));
        } else if (clazz.equals(SearchData.class)) {
            return clazz.cast(new SearchDataRealmProxy(columnInfo));
        } else if (clazz.equals(SensitiveMessage.class)) {
            return clazz.cast(new SensitiveMessageRealmProxy(columnInfo));
        } else if (clazz.equals(RealmString.class)) {
            return clazz.cast(new RealmStringRealmProxy(columnInfo));
        } else if (clazz.equals(AbroadMessage.class)) {
            return clazz.cast(new AbroadMessageRealmProxy(columnInfo));
        } else if (clazz.equals(SearchMessage.class)) {
            return clazz.cast(new SearchMessageRealmProxy(columnInfo));
        } else if (clazz.equals(WechatData.class)) {
            return clazz.cast(new WechatDataRealmProxy(columnInfo));
        } else if (clazz.equals(AbroadData.class)) {
            return clazz.cast(new AbroadDataRealmProxy(columnInfo));
        } else if (clazz.equals(NetizenMessage.class)) {
            return clazz.cast(new NetizenMessageRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmObject>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmObject> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmObject, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(DataList.class)) {
            return clazz.cast(DataListRealmProxy.copyOrUpdate(realm, (DataList) obj, update, cache));
        } else if (clazz.equals(NetizenData.class)) {
            return clazz.cast(NetizenDataRealmProxy.copyOrUpdate(realm, (NetizenData) obj, update, cache));
        } else if (clazz.equals(WechatMessage.class)) {
            return clazz.cast(WechatMessageRealmProxy.copyOrUpdate(realm, (WechatMessage) obj, update, cache));
        } else if (clazz.equals(Doc.class)) {
            return clazz.cast(DocRealmProxy.copyOrUpdate(realm, (Doc) obj, update, cache));
        } else if (clazz.equals(SearchData.class)) {
            return clazz.cast(SearchDataRealmProxy.copyOrUpdate(realm, (SearchData) obj, update, cache));
        } else if (clazz.equals(SensitiveMessage.class)) {
            return clazz.cast(SensitiveMessageRealmProxy.copyOrUpdate(realm, (SensitiveMessage) obj, update, cache));
        } else if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.copyOrUpdate(realm, (RealmString) obj, update, cache));
        } else if (clazz.equals(AbroadMessage.class)) {
            return clazz.cast(AbroadMessageRealmProxy.copyOrUpdate(realm, (AbroadMessage) obj, update, cache));
        } else if (clazz.equals(SearchMessage.class)) {
            return clazz.cast(SearchMessageRealmProxy.copyOrUpdate(realm, (SearchMessage) obj, update, cache));
        } else if (clazz.equals(WechatData.class)) {
            return clazz.cast(WechatDataRealmProxy.copyOrUpdate(realm, (WechatData) obj, update, cache));
        } else if (clazz.equals(AbroadData.class)) {
            return clazz.cast(AbroadDataRealmProxy.copyOrUpdate(realm, (AbroadData) obj, update, cache));
        } else if (clazz.equals(NetizenMessage.class)) {
            return clazz.cast(NetizenMessageRealmProxy.copyOrUpdate(realm, (NetizenMessage) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return clazz.cast(DataListRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(NetizenData.class)) {
            return clazz.cast(NetizenDataRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(WechatMessage.class)) {
            return clazz.cast(WechatMessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Doc.class)) {
            return clazz.cast(DocRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(SearchData.class)) {
            return clazz.cast(SearchDataRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(SensitiveMessage.class)) {
            return clazz.cast(SensitiveMessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(AbroadMessage.class)) {
            return clazz.cast(AbroadMessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(SearchMessage.class)) {
            return clazz.cast(SearchMessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(WechatData.class)) {
            return clazz.cast(WechatDataRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(AbroadData.class)) {
            return clazz.cast(AbroadDataRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(NetizenMessage.class)) {
            return clazz.cast(NetizenMessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(DataList.class)) {
            return clazz.cast(DataListRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(NetizenData.class)) {
            return clazz.cast(NetizenDataRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(WechatMessage.class)) {
            return clazz.cast(WechatMessageRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Doc.class)) {
            return clazz.cast(DocRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(SearchData.class)) {
            return clazz.cast(SearchDataRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(SensitiveMessage.class)) {
            return clazz.cast(SensitiveMessageRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(AbroadMessage.class)) {
            return clazz.cast(AbroadMessageRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(SearchMessage.class)) {
            return clazz.cast(SearchMessageRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(WechatData.class)) {
            return clazz.cast(WechatDataRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(AbroadData.class)) {
            return clazz.cast(AbroadDataRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(NetizenMessage.class)) {
            return clazz.cast(NetizenMessageRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmObject, RealmObjectProxy.CacheData<RealmObject>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(DataList.class)) {
            return clazz.cast(DataListRealmProxy.createDetachedCopy((DataList) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(NetizenData.class)) {
            return clazz.cast(NetizenDataRealmProxy.createDetachedCopy((NetizenData) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(WechatMessage.class)) {
            return clazz.cast(WechatMessageRealmProxy.createDetachedCopy((WechatMessage) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Doc.class)) {
            return clazz.cast(DocRealmProxy.createDetachedCopy((Doc) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(SearchData.class)) {
            return clazz.cast(SearchDataRealmProxy.createDetachedCopy((SearchData) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(SensitiveMessage.class)) {
            return clazz.cast(SensitiveMessageRealmProxy.createDetachedCopy((SensitiveMessage) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(RealmString.class)) {
            return clazz.cast(RealmStringRealmProxy.createDetachedCopy((RealmString) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(AbroadMessage.class)) {
            return clazz.cast(AbroadMessageRealmProxy.createDetachedCopy((AbroadMessage) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(SearchMessage.class)) {
            return clazz.cast(SearchMessageRealmProxy.createDetachedCopy((SearchMessage) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(WechatData.class)) {
            return clazz.cast(WechatDataRealmProxy.createDetachedCopy((WechatData) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(AbroadData.class)) {
            return clazz.cast(AbroadDataRealmProxy.createDetachedCopy((AbroadData) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(NetizenMessage.class)) {
            return clazz.cast(NetizenMessageRealmProxy.createDetachedCopy((NetizenMessage) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
