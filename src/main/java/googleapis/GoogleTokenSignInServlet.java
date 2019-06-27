package googleapis;

import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson2.JacksonFactory;
import com.ynguyen.servlets.BaseServlet;
import com.ynguyen.servlets.ErrorCodes;
import com.ynguyen.servlets.ErrorHandler;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleTokenSignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoogleIdTokenVerifier verifier =  new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("1027853989608-b34fgfle7abug9sn3ch2it5oussd54hn.apps.googleusercontent.com"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();
        JSONObject body = new JSONObject(IOUtils.toString(req.getInputStream(), "UTF-8"));
        String idTokenString = body.getString("id_token");
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorCodes.UNEXPECT_ERROR, e.getMessage());
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", "comming soon");
            jsonObject.put("email", email);
            resp.getWriter().write( jsonObject.toString());
        } else {
            ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ERROR, "Invalid google token id");
        }
    }
}
