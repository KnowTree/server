package servlets;

import org.json.JSONObject;
import system.Data;
import system.acl.ACL;
import system.acl.AccessToken;
import system.configurations.Configuration;
import system.fields.HasCredential;
import utils.RestApiFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class LoginServlet extends HttpServlet {
    Long expireTime = 1 * 24 * 60 * 60L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat restApiFormat = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
        String payloadRaw = restApiFormat.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        String username = jsonObject.getString(HasCredential.username.key());
        String password = jsonObject.getString(HasCredential.password.key());

        if (username != null && password != null) {
            ACL acl = Configuration.getInstance().getAcl();
            Data user = acl.findUser(username, password);
            if (user != null) {
                Long userId = user.id();
                LocalDateTime date = LocalDateTime.now().plusDays(1);
                ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());
                try {
                    AccessToken accessToken = new AccessToken(userId, zonedDateTime.toInstant().toEpochMilli());
                    String token = accessToken.getTokenString();
                    JSONObject result = user.getJsonObject();
                    result.remove(HasCredential.password.key());
                    result.put(HasCredential.token.key(), token);
                    resp.getWriter().write(result.toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    ErrorHandler.handle(req, resp, ErrorCodes.UNEXPECT_ERROR, e.getMessage());
                }
            } else {
                ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ERROR, "User not found");
            }
        } else {
            ErrorHandler.handle(req, resp, ErrorCodes.MISSING_PARAM, "Missing username or password");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
