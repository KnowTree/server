package kinds;

import org.json.JSONObject;
import system.Data;
import system.DatabaseController;
import system.configurations.Configuration;
import system.fields.HasCredential;
import system.property.Property;
import utils.RestApiFormat;

import java.util.List;

public class Token extends Data {
    public Token() {
        super("Token");
    }

    @Override //login
    public boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        boolean hasCredential = data.containProperties(HasCredential.username, HasCredential.password);
        return currentUser == null && hasCredential;
    }

    @Override
    public boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        return false;
    }

    @Override // register
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        boolean hasCredential = data.containProperties(HasCredential.username, HasCredential.password);
        return currentUser == null && hasCredential;
    }

    @Override
    public boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        return false;
    }

    @Override
    public Property[] fields() {
        return new Property[0];
    }

    @Override
    public Property[] labelFields() {
        return new Property[0];
    }

    @Override
    public List<String> addCustomLabels() {
        return null;
    }
}
