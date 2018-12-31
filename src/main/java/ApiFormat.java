import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import utils.ServletRequestUtils;

public class ApiFormat {
    private String error;
    private String version;
    private String kind;
    private String method;
    private Long id;
    private EntityType entityType;
    private String payload;
    final String INVALID_FORMAT = "Invalid format";

    public ApiFormat(HttpServletRequest request) {
        this.check(request);
    }

    private void check(HttpServletRequest request) {
        ArrayList<String> uriParts = ServletRequestUtils.getURIParts(request);
        if (!((String)uriParts.get(0)).equals("api")) {
            this.error = "Invalid format";
        }

        this.method = request.getMethod();

        if (uriParts.size() == 5) {
            version = uriParts.get(1);
            String type = uriParts.get(2);
            switch (type) {
                case "n" :
                    entityType = EntityType.Node;
                    break;
                case "r" :
                    entityType = EntityType.Relationship;
                    break;
                default :
                    error = INVALID_FORMAT;
            }
            kind = uriParts.get(3);
            id = Long.valueOf(uriParts.get(4));
        } else {
            this.error = INVALID_FORMAT;
        }

        if (method.equals("POST") || method.equals("PUT")) {
            try {
                payload = IOUtils.toString(request.getReader());
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            }
        }



    }

    public boolean isValid() {
        return this.error == null;
    }

    public String getVersion() {
        return this.version;
    }

    public String getKind() {
        return this.kind;
    }

    public Long getId() {
        return this.id;
    }

    public String getError() {
        return this.error;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public String getPayload() {
        return payload;
    }

    public String getMethod() {
        return method;
    }

    public boolean isRelationship() {
        return EntityType.Relationship == entityType;
    }

    enum EntityType {
        Node, Relationship
    }
}
