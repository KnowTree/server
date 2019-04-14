package app;

import org.json.JSONObject;
import servlets.RequestHeaders;
import system.Data;
import system.acl.ACL;
import system.acl.AccessToken;
import utils.RestApiFormat;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
}
