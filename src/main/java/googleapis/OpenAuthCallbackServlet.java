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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class OpenAuthCallbackServlet extends BaseServlet {
    Logger logger = Logger.getLogger(OpenAuthCallbackServlet.class.getSimpleName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if (error != null) {
            logger.warning("Error while oauth : " + error);
        } else {
            String authorize_code = req.getParameter("code");
            if (authorize_code != null) {
                exchangeToken(authorize_code);
            } else {
                logger.warning("Authorization code is null");
            }
        }
    }

    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }

    private void exchangeToken(String code) throws MalformedURLException {
        URL endpoint = new URL("https://www.googleapis.com/oauth2/v4/token");


    }

    protected void onResult(JSONObject result) {
        logger.info("Result : " + result.toString());
    }

    protected void onError(JSONObject error) {

    }
}
