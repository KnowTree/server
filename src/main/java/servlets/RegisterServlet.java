package servlets;

import org.json.JSONObject;
import system.Data;
import system.DatabaseController;
import system.acl.ACL;
import system.configurations.Configuration;
import system.fields.HasCredential;
import utils.RestApiFormat;
import utils.SearchData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat restApiFormat = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
        String payloadRaw = restApiFormat.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        String username = jsonObject.getString(HasCredential.username.key());
        String password = jsonObject.getString(HasCredential.password.key());

        ACL acl = Configuration.getInstance().getAcl();
        Data newUser = acl.newUser(username, password);
        if (newUser != null) {
            resp.getWriter().write(new JSONObject().put("message", "ok").toString());
        } else {
            ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ERROR, "Can not create new user");
        }
    }
}
