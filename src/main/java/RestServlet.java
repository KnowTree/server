import app.KnowTreeConfiguration;
import com.google.cloud.datastore.*;
import com.google.gson.JsonObject;
import configurations.Configuration;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestServlet extends HttpServlet {
    Configuration configuration;

    @Override
    public void init() throws ServletException {
        super.init();
        configuration = Configuration.getInstance();
        configuration.setSystemConfiguration(new KnowTreeConfiguration());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Key key = datastore.newKeyFactory().setKind(urlParser.getKind()).newKey(urlParser.getEntityKey());
        Entity entity = datastore.get(key);
        if (entity != null) {

        } else {
            entity.toString();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UrlParser urlParser = new UrlParser(req);
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(urlParser.getKind());
        Key taskKey = datastore.allocateId(keyFactory.newKey());
        Entity.Builder builder = Entity.newBuilder(taskKey);

        String payloadRaw = urlParser.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);

        for (String field : jsonObject.keySet()) {

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
