package system;

import servlets.RestEntity;
import system.configurations.Configuration;
import system.fields.CanSearch;
import system.fields.FieldMap;
import system.property.Property;
import org.json.JSONObject;
import system.fields.HasId;

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

    public abstract Property[] fields();

    public String getKind() {
        return this.kind;
    }

    public Object get(Property field) {
        String fieldName = field.key();
        return jsonObject.has(fieldName) ? jsonObject.get(fieldName) : null;
    }

    public Long getLong(Property<Long> property) {
        return jsonObject.getLong(property.key());
    }

    public String getString(Property<String> property) {
        return jsonObject.getString(property.key());
    }

    public Boolean getBoolean(Property<Boolean> prop) {return jsonObject.getBoolean(prop.key());}

    public Data set(Property field, Object value) {
        jsonObject.put(field.key(), field.acceptValue(value));
        return this;
    }

    public final Data retrieve(List<Property> props) throws Exception {
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

    public final Data create() throws Exception {
        createLabel();
        JSONObject result = dataController.create(getKind(), jsonObject);
        jsonObject = result;
        return this;
    }

    public final Data update() throws Exception {
        Long id = getLong(HasId.id);
        JSONObject result = dataController.update(getKind(), id, jsonObject);
        jsonObject = result;
        return this;
    }

    public final boolean delete() throws Exception {
        Long id = getLong(HasId.id);
        return dataController.delete(getKind(), id);
    }

    public Data setJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    public Data copyFromJSON(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            this.jsonObject.put(key, jsonObject.get(key));
        }
        createLabel();
        return this;
    }

    public JSONObject jsonObject() {
        return jsonObject;
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

    public final void createLabel() {
        List<String> result = new ArrayList<>();
        Property[] labelFields = labelFields();
        for (Property field : labelFields) {
            Object value = get(field);
            if (value != null) {
                result.addAll(field.createLabels(value));
            }
        }
        List<String> morelabels = addCustomLabels();
        if (morelabels != null) {
            result.addAll(morelabels);
        }
        set(CanSearch.labels, result);
    }

    public abstract Property[] labelFields();

    public abstract List<String> addCustomLabels();
    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
