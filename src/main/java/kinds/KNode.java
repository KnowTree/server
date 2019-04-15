package kinds;

import kinds.fields.HasUrl;
import kinds.fields.IsAdmin;
import org.json.JSONObject;
import system.Data;
import system.DatabaseController;
import system.configurations.Configuration;
import system.fields.HasId;
import system.fields.HasTracking;
import system.property.Property;
import utils.RestApiFormat;

import java.util.Arrays;
import java.util.List;

public class KNode extends Data {

    public KNode() {
        super("Knode", Configuration.getInstance().getSystemConfiguration().getDatabaseController());
    }

    @Override
    public boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        return true;
    }

    @Override
    public boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        if (currentUser == null) return false;
        else {
            return currentData.getLong(HasTracking.created_by).equals(currentUser.id());
        }
    }

    @Override
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        return currentUser != null;
    }

    @Override
    public boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        return currentUser != null && currentUser.id().equals(data.getLong(HasTracking.created_by));
    }

    public KNode setTitle(String title) {
        set(HasUrl.title, title);
        return this;
    }

    public KNode setUrl(String url) {
        set(HasUrl.url, url);
        return this;
    }
}
