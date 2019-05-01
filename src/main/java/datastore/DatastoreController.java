package datastore;

import com.google.appengine.api.datastore.*;
import org.json.JSONArray;
import system.Data;
import system.DataFactory;
import system.configurations.Configuration;
import system.fields.CanSearch;
import system.fields.FieldMap;
import system.property.*;
import org.json.JSONObject;
import system.DatabaseController;
import system.fields.HasId;
import utils.QueryData;
import utils.SearchData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DatastoreController implements DatabaseController {
    DatastoreService datastore;
    public DatastoreController() {
        datastore = DatastoreServiceFactory.getDatastoreService();

    }

    public static Entity buildEntityFromJSONObject(Key key, String kind, JSONObject data) {
        Entity entity = new Entity(key);
        for (String fieldName : data.keySet()) {
            Property property = Configuration.getInstance().fieldMap().get(kind, fieldName);
            if (data.has(fieldName)) {
                entity.setProperty(fieldName, property.acceptValue(data.get(fieldName)));
            }
        }
        return entity;
    }

    public static JSONObject entityToJSONObject(Entity entity, List<Property> fields) {
        JSONObject json = new JSONObject();
        for (Property field : fields) {
            if (entity.hasProperty(field.key())) {
                json.put(field.key(), field.acceptValue(entity.getProperty(field.key())));
            }
        }
        json.put(HasId.id.key(), entity.getKey().getId());
        return json;
    }

    @Override
    public JSONObject create(String kind, JSONObject data) {
        Key key = datastore.allocateIds(kind, 1).getStart();
        Entity entity = buildEntityFromJSONObject(key,kind, data);
        datastore.put(entity);
        return data.put(HasId.id.key(), key.getId());
    }

    @Override
    public JSONObject update(String kind, Long id, JSONObject updateData) {
        Key key = KeyFactory.createKey(kind, id);
        Entity entity = buildEntityFromJSONObject(key, kind, updateData);
        datastore.put(entity);
        return updateData;
    }

    @Override
    public boolean delete(String kind, Long id) {
        Key key = KeyFactory.createKey(kind, id);
        datastore.delete(key);
        return true;
    }

    @Override
    public JSONObject get(String kind, Long id, List<Property> props) throws EntityNotFoundException {
        Key key = KeyFactory.createKey(kind, id);
        Entity entity = datastore.get(key);
        return entityToJSONObject(entity, props);
    }

    @Override
    public JSONObject get(String key, List<Property> props) throws EntityNotFoundException {
        Key datastoreKey = KeyFactory.stringToKey(key);
        Entity entity = datastore.get(datastoreKey);
        return entityToJSONObject(entity, props);
    }

    /**
     *
     * @param searchApiData search by labels field , use all equal filters & cursor
     *
     * @return
     */
    @Override
    public List<Data> search(SearchData searchApiData) {
        List<QueryData> queryDataList = searchApiData.getQueryDataList();

        List<Query.Filter> filterPredicates = new ArrayList<>();
        for (QueryData queryData : queryDataList) {
            Property field = Configuration.getInstance().fieldMap().get(searchApiData.getKind(), queryData.getField());
            String value = (String) field.createLabels(queryData.getValue()).get(0);
            Query.FilterPredicate filterPredicate =
                    new Query.FilterPredicate(CanSearch.labels.key(), Query.FilterOperator.EQUAL, value);
            filterPredicates.add(filterPredicate);
        }

        Query query;
        if (filterPredicates.size() == 1) {
            query = new Query(searchApiData.getKind()).setFilter(filterPredicates.get(0));
        } else if (filterPredicates.size() > 1){
            Query.CompositeFilter compositeFilter = new Query.CompositeFilter(Query.CompositeFilterOperator.AND, filterPredicates);
            query = new Query(searchApiData.getKind()).setFilter(compositeFilter);
        } else {
            query = new Query(searchApiData.getKind());
        }

        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(searchApiData.getLength());
        if (searchApiData.getCursor() != null) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(searchApiData.getCursor()));
        } else {
            fetchOptions.offset(searchApiData.getStart());
        }

        PreparedQuery preparedQuery = datastore.prepare(query);
        QueryResultList<Entity> queryResultList = preparedQuery.asQueryResultList(fetchOptions);
        searchApiData.setCursor(queryResultList.getCursor().toWebSafeString());

        List<Data> result = new ArrayList<>();
        for (Entity entity : queryResultList) {
            Data data = Configuration.getInstance().dataFactory().create(searchApiData.getKind());
            data.setJSONObject(entityToJSONObject(entity, Configuration.getInstance().fieldMap().getFields(searchApiData.getKind())));
            result.add(data);
        }
        return result;
    }



}
