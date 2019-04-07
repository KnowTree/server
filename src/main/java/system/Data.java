package system;

import configurations.Configuration;
import neo4j.LongProperty;
import neo4j.Property;
import org.json.JSONObject;
import system.fields.HasId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    String kind;
    JSONObject jsonObject;
    DatabaseController dataController;
    public Data(String kind, DatabaseController dataController) {
        this.dataController = dataController;
        jsonObject = new JSONObject();
        this.kind = kind;
    }

    public String getKind() {
        return this.kind;
    }

    public Object get(Property field) {
        String fieldName = field.key();
        return jsonObject.get(fieldName);
    }

    public Long getLong(Property<Long> property) {
        return jsonObject.getLong(property.key());
    }

    public String getString(Property<String> property) {
        return jsonObject.getString(property.key());
    }

    public Data set(Property field) {
        jsonObject.put(field.key(), field.getValue());
        return this;
    }

    public Data retrieve() throws Exception {
        Long id = getLong(HasId.id);
        List<Property> fields = Configuration.getInstance().fieldMap().getFields(getKind());
        if (fields == null) throw new Error("Unidentified kind " + getKind());
        jsonObject = dataController.get(getKind(), id, fields);
        return this;
    }

    public Data create() throws Exception {
        JSONObject result = dataController.create(getKind(), jsonObject);
        jsonObject = result;
        return this;
    }

    public Data update() throws Exception {
        Long id = getLong(HasId.id);
        JSONObject result = dataController.update(getKind(), id, jsonObject);
        jsonObject = result;
        return this;
    }

    public boolean delete() throws Exception {
        Long id = getLong(HasId.id);
        return dataController.delete(getKind(), id);
    }

    public Data setJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
