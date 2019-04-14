package utils;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import utils.ServletRequestUtils;

public class RestApiFormat {
    private String request_kind;
    private String error;
    private String version;
    private String kind;
    private String method;
    private String id;
    private String payload;
    private String accessToken;
    final String INVALID_FORMAT = "Invalid format";
    final String ACCESS_TOKEN = "access_token";

    public RestApiFormat(HttpServletRequest request) {
        this.check(request);
    }

    private void check(HttpServletRequest request) {
        ArrayList<String> uriParts = ServletRequestUtils.getURIParts(request);
        request_kind = uriParts.get(0);

        this.method = request.getMethod();

        if (uriParts.size() >= 3) {
            version = uriParts.get(1);
            kind = uriParts.get(2);
        } else {
            this.error = INVALID_FORMAT;
        }

        if (uriParts.size() == 4) {
            id = uriParts.get(3);
        }

        if (method.equals("POST") || method.equals("PUT")) {
            try {
                payload = IOUtils.toString(request.getReader());
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            }
        }

        this.accessToken = request.getHeader(ACCESS_TOKEN);

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

    public String getError() {
        return this.error;
    }

    public String getPayload() {
        return payload;
    }

    public String getMethod() {
        return method;
    }

    public String getAccessToken() {
        return this.accessToken != null ? this.accessToken : "";
    }

    public Long getId() {
        return Long.valueOf(id);
    }

}
