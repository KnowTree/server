package system;

import neo4j.Property;
import org.json.JSONObject;
import system.fields.HasId;

import java.util.HashMap;
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
        field.setValue(jsonObject.get(fieldName));
        return field.getValue();
    }

    public Data retrieve() {
        Long id = (Long) this.get(HasId.id);
        jsonObject = dataController.get(id);
        return this;
    }

    public Data create() {
        JSONObject result = dataController.create(jsonObject);
        jsonObject = result;
        return this;
    }

    public Data update() {
        Long id = (Long) get(HasId.id);
        JSONObject result = dataController.update(id, jsonObject);
        jsonObject = result;
        return this;
    }

    public boolean delete() {
        Long id = (Long) get(HasId.id);
        return dataController.delete(id);
    }

}
