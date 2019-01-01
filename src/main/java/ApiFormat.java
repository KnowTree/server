import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import utils.ServletRequestUtils;

public class ApiFormat {
    private String error;
    private String version;
    private String kind;
    private String method;
    private Long id;
    private ApiType apiType;
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

        if (uriParts.size() >=4) {
            version = uriParts.get(1);
            String type = uriParts.get(2);
            switch (type) {
                case "n" :
                    apiType = ApiType.Node;
                    break;
                case "r" :
                    apiType = ApiType.Relationship;
                    break;
                default :
                    error = INVALID_FORMAT;
            }
            kind = uriParts.get(3);
        } else {
            this.error = INVALID_FORMAT;
        }

        if (uriParts.size() == 5) {
            id = Long.valueOf(uriParts.get(4));
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

    public ApiType getApiType() {
        return apiType;
    }

    public String getPayload() {
        return payload;
    }

    public String getMethod() {
        return method;
    }

    public boolean isRelationship() {
        return ApiType.Relationship == apiType;
    }

    enum ApiType {
        Node, Relationship
    }
}
