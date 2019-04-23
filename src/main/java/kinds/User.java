package kinds;

import kinds.fields.IsAdmin;
import kinds.fields.IsClient;
import org.json.JSONObject;
import servlets.ErrorHandler;
import system.configurations.Configuration;
import system.Data;
import system.fields.*;
import system.property.Property;
import utils.Commons;
import utils.RestApiFormat;

import java.security.NoSuchAlgorithmException;
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
        try {
            String hash = Commons.byteToHex(Commons.hash(data.getString(HasCredential.password)));
            data.set(HasCredential.password, hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
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

    @Override
    public Property[] fields() {
        return new Property[] {HasCredential.username, HasCredential.password, HasEmail.email, HasId.id,
                HasTracking.created_date, HasName.first_name, HasName.last_name, HasName.middle_name,
        IsAdmin.is_admin, IsClient.is_client, IsAdmin.is_system, CanSearch.labels};
    }

    @Override
    public Property[] labelFields() {
        return new Property[] {HasEmail.email, HasId.id, HasName.first_name, HasName.last_name, HasName.middle_name,
        IsAdmin.is_admin, IsClient.is_client, IsAdmin.is_system, HasCredential.username};
    }

    @Override
    public List<String> addCustomLabels() {
        return null;
    }
}
