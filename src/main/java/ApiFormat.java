import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import utils.ServletRequestUtils;

public class ApiFormat {
    private String error;
    private String version;
    private String kind;
    private String method;
    private Long id;
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
        if (uriParts.size() >= 2) {
            this.version = (String)uriParts.get(1);
        } else {
            this.error = "Invalid format";
        }

        if (uriParts.size() >= 3) {
            this.kind = (String)uriParts.get(2);
        } else {
            this.error = "Invalid format";
        }

        if (uriParts.size() == 4) {
            this.id = Long.valueOf((String)uriParts.get(3));
        } else {
            this.error = "Invalid format";
        }

        Map<String, String> parameter = request.getParameterMap();
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
}
