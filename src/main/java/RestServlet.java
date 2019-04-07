import app.KnowTreeConfiguration;
import com.google.gson.JsonObject;
import configurations.Configuration;
import neo4j.LongProperty;
import org.json.JSONObject;
import system.Data;
import system.DataFactory;
import system.fields.HasCredential;
import system.fields.HasEmail;
import system.fields.HasId;
import system.fields.HasName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class RestServlet extends HttpServlet {
    Configuration configuration;

    @Override
    public void init() throws ServletException {
        super.init();
        configuration = Configuration.getInstance();
        configuration.setSystemConfiguration(new KnowTreeConfiguration());
        configuration.fieldMap().addFields("User",
                Arrays.asList(HasName.first_name, HasName.middle_name, HasName.last_name, HasCredential.username, HasCredential.password, HasEmail.email));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
       Data data = DataFactory.getInstance().create(urlParser.getKind());
       data.set(HasId.id.setValue(urlParser.getId()));
        try {
            data.retrieve();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }
        resp.getWriter().write(data.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
        String payloadRaw = urlParser.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        Data data = DataFactory.getInstance().create(urlParser.getKind());
        data.setJSONObject(jsonObject);
        try {
            data.update();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }
        resp.getWriter().write(data.toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
        String payloadRaw = urlParser.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        Data data = DataFactory.getInstance().create(urlParser.getKind());
        data.setJSONObject(jsonObject);
        try {
            data.create();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }
        resp.getWriter().write(data.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
        Data data = DataFactory.getInstance().create(urlParser.getKind());
        data.set(HasId.id.setValue(urlParser.getId()));
        try {
            data.delete();
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }

    }

}
