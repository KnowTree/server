import app.KnowTreeACL;
import app.KnowTreeConfiguration;
import kinds.User;
import org.json.JSONObject;
import servlets.RestServlet;
import system.Data;
import system.fields.HasCredential;
import system.fields.HasTracking;
import utils.RestApiFormat;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnowTreeRestServlet extends RestServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        configuration.setSystemConfiguration(new KnowTreeConfiguration());
        configuration.setACL(new KnowTreeACL());
    }
}
