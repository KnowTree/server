package datastore;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.json.JSONObject;
import system.Data;
import system.DatabaseController;

public class DatastoreController implements DatabaseController {
    Datastore datastore;

    public DatastoreController() {
        datastore = DatastoreOptions.getDefaultInstance().getService();
    }

    @Override
    public JSONObject create(JSONObject data) {
        return null;
    }

    @Override
    public JSONObject update(Long id, JSONObject updateData) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return true;
    }

    @Override
    public JSONObject get(Long id) {
        return null;
    }

    @Override
    public JSONObject get(String key) {
        return null;
    }
}
