package datastore;

import com.google.appengine.api.datastore.*;
import system.Data;
import system.DataFactory;
import system.configurations.Configuration;
import system.fields.CanSearch;
import system.fields.FieldMap;
import system.property.IntegerProperty;
import system.property.LongProperty;
import system.property.Property;
import system.property.StringProperty;
import org.json.JSONObject;
import system.DatabaseController;
import system.fields.HasId;
import utils.SearchData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatastoreController implements DatabaseController {
    DatastoreService datastore;
    public DatastoreController() {
        datastore = DatastoreServiceFactory.getDatastoreService();

    }

    public static Entity buildEntityFromJSONObject(Key key, JSONObject data) {
        Entity entity = new Entity(key);
        for (String fieldName : data.keySet()) {
            Object obj = data.get(fieldName);
            if (obj instanceof String) {
                entity.setProperty(fieldName, data.getString(fieldName));
            } else if (obj instanceof Long) {
                entity.setProperty(fieldName, data.getLong(fieldName));
            }
        }
        return entity;
    }

    public static JSONObject entityToJSONObject(Entity entity, List<Property> fields) {
        JSONObject json = new JSONObject();
        for (Property field : fields) {
            json.put(field.key(), field.acceptValue(entity.getProperty(field.key())));
        }
        json.put(HasId.id.key(), entity.getKey().getId());
        return json;
    }

    @Override
    public JSONObject create(String kind, JSONObject data) {
        Key key = datastore.allocateIds(kind, 1).getStart();
        Entity entity = buildEntityFromJSONObject(key, data);
        datastore.put(entity);
        return data.put(HasId.id.key(), key.getId());
    }

    @Override
    public JSONObject update(String kind, Long id, JSONObject updateData) {
        Key key = KeyFactory.createKey(kind, id);
        Entity entity = buildEntityFromJSONObject(key, updateData);
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

    @Override
    public List<Data> search(SearchData searchApiData) {
        List<SearchData.QueryData> queryDataList = searchApiData.getQueryDataList();
        List<String> labels = new ArrayList<>();
        for (SearchData.QueryData queryData : queryDataList) {
            String ops = queryData.getOperator();
            Query.FilterOperator filterOperator;
            Property field = CanSearch.labels;
            labels.addAll(field.createLabels(queryData.getValue()));
        }

        Query.FilterPredicate filterPredicate =
                new Query.FilterPredicate(searchApiData.getKind(), Query.FilterOperator.EQUAL, labels);

        Query query = new Query(searchApiData.getKind()).setFilter(filterPredicate);

        PreparedQuery preparedQuery = datastore.prepare(query);
        List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withLimit(100));

        List<Data> result = new ArrayList<>();
        for (Entity entity : entities) {
            Data data = Configuration.getInstance().dataFactory().create(searchApiData.getKind());
            data.setJSONObject(entityToJSONObject(entity, Configuration.getInstance().fieldMap().getFields(searchApiData.getKind())));
            result.add(data);
        }
        return result;
    }



}
