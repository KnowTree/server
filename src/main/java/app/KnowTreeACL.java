package app;

import kinds.User;
import org.json.JSONObject;
import servlets.RequestHeaders;
import system.Data;
import system.acl.ACL;
import system.acl.AccessToken;
import system.configurations.Configuration;
import system.fields.HasCredential;
import utils.Commons;
import utils.QueryData;
import utils.RestApiFormat;
import utils.SearchData;

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
            currentUser = accessToken.loadUser();
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
        QueryData<String> passwordQuery = new QueryData<>(HasCredential.password.key(), "EQUAL", hash);
        searchData.setQueryDataList(Arrays.asList(usernameQuery, passwordQuery));
        List<Data> users = Configuration.getInstance().getSystemConfiguration().getDatabaseController().search(searchData);
        if (users.size() == 0) {
            return null;
        } else return users.get(0);
    }
}
