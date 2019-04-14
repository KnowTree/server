package system.acl;

import org.json.JSONObject;
import system.Data;
import system.property.Property;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

public interface ACL {

    Data retrieveCurrentUserData(AccessToken accessToken);
}
