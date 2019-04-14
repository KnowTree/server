package kinds;

import kinds.fields.IsAdmin;
import kinds.fields.IsClient;
import org.json.JSONObject;
import system.configurations.Configuration;
import system.Data;
import system.fields.FieldMap;
import system.fields.HasCredential;
import system.fields.HasId;
import utils.RestApiFormat;

import java.util.Arrays;
import java.util.List;

public class User extends Data {
    public User() {
        super("User", Configuration.getInstance().getSystemConfiguration().getDatabaseController());
    }

    public boolean isAdmin() {
        return getBoolean(IsAdmin.is_admin);
    }

    public boolean isClient() {
        return getBoolean(IsClient.is_client);
    }

    public Long getId() {
        return getLong(HasId.id);
    }


    @Override
    public boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        data.remove(HasCredential.password);
        return true;
    }

    @Override
    public boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        if (currentUser == null) return false;
        else {
            return currentUser.get(HasCredential.username).equals(currentData.get(HasCredential.username));
        }
    }

    @Override
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        boolean hasCredential = data.containProperties(HasCredential.username, HasCredential.password);
        if (currentUser == null) return hasCredential;
        else {
            User user = (User) currentUser;
            return user.isAdmin();
        }
    }

    @Override
    public boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        if (currentUser == null) return false;
        else {
            return ((User) currentUser).isAdmin() && !data.id().equals(currentUser.id());
        }
    }
}
