package app;

import com.ynguyen.system.fields.HasId;
import kinds.User;
import org.json.JSONObject;
import com.ynguyen.servlets.RequestHeaders;
import com.ynguyen.system.Data;
import com.ynguyen.system.acl.ACL;
import com.ynguyen.system.acl.AccessToken;
import com.ynguyen.system.configurations.Configuration;
import com.ynguyen.system.fields.HasCredential;
import com.ynguyen.utils.Commons;
import com.ynguyen.utils.QueryData;
import com.ynguyen.utils.RestApiFormat;
import com.ynguyen.utils.SearchData;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class KnowTreeACL implements ACL {

    public KnowTreeACL() {

    }
    @Override
    public Data retrieveCurrentUserData(AccessToken accessToken) {
        Data currentUser = null;
        try {
            User user = new User();
            user.set(HasId.id, accessToken.getUserId());
            currentUser = user.retrieve(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentUser;
    }

    @Override
    public Data newUser(String username, String password) {
        User user = new User();
        user.set(HasCredential.username, username);
        try {
            String hash = Commons.byteToHex(Commons.hash(password));
            user.set(HasCredential.password, hash);
            user.create();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public Data findUser(String username, String password) {
        String hash;
        try {
            hash = Commons.byteToHex(Commons.hash(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        SearchData searchData = new SearchData();
        searchData.setKind("User");
        QueryData<String> usernameQuery = new QueryData<>(HasCredential.username.key(), "EQUAL", username);
        searchData.setQueryDataList(Arrays.asList(usernameQuery));
        List<Data> users = Configuration.getInstance().getSystemConfiguration().getDatabaseController().search(searchData);
        if (users.size() == 0) {
            return null;
        } else {
            if (hash.equals(users.get(0).getString(HasCredential.password))) {
                return users.get(0);
            } else {
                return null;
            }
        }
    }
}
