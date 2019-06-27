package googleapis;

import app.KnowTreeConfiguration;
import com.ynguyen.servlets.BaseServlet;
import com.ynguyen.system.configurations.Configuration;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetAuthorizationUrlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authorize_url", getUrl());
        resp.getWriter().write(jsonObject.toString());
    }

    protected String getScopes() {
        return "https://www.googleapis.com/auth/userinfo.email";
    }

    protected String getUrl() {
        return "https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=" + getScopes() +
                "&access_type=offline" +
                "&include_granted_scopes=true" +
                "&redirect_uri=" + getRediectUri() +
                "&response_type=code" +
                "&client_id=" + getClientId();
    }

    protected String getRediectUri() {
        return "";
    }

    protected String getClientId() {
        return "";
    }
 }
