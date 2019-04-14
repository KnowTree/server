package system;

import servlets.RestEntity;
import system.configurations.Configuration;
import system.property.Property;
import org.json.JSONObject;
import system.fields.HasId;
import utils.RestApiFormat;

import java.util.ArrayList;
import java.util.List;

public abstract class Data implements RestEntity {
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

    public Boolean getBoolean(Property<Boolean> prop) {return jsonObject.getBoolean(prop.key());}
    public Data set(Property field) {
        jsonObject.put(field.key(), field.getValue());
        return this;
    }

    public Data retrieve(List<Property> props) throws Exception {
        Long id = getLong(HasId.id);
        if (props == null) props = Configuration.getInstance().fieldMap().getFields(getKind());
        jsonObject = dataController.get(getKind(), id, props);
        return this;
    }

    public Data retrieveByGroupName(List<String> fieldGroupName) throws Exception {
        List<Property> fields = new ArrayList<>();
        for (String groupName : fieldGroupName) {
            fields.addAll(Configuration.getInstance().fieldMap().getFields(groupName));
        }
        return retrieve(fields);
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

    public boolean containProperties(Property... props) {
        for (Property prop : props) {
            if (!jsonObject.keySet().contains(prop.key())) return false;
        }
        return true;
    }

    public boolean notContainProperties(Property... props) {
        for (Property prop : props) {
            if (jsonObject.keySet().contains(prop.key())) return false;
        }
        return true;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public Data remove(Property props) {
        jsonObject.remove(props.key());
        return this;
    }

    public Long id() {
        return getLong(HasId.id);
    };
    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
