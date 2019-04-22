import app.KnowTreeACL;
import app.KnowTreeConfiguration;
import app.KnowTreeDataFactory;
import app.KnowTreeFieldMap;
import servlets.RestServlet;

import javax.servlet.ServletException;
import java.util.Arrays;

public class KnowTreeRestServlet extends RestServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        configuration.setSystemConfiguration(new KnowTreeConfiguration());
        configuration.setACL(new KnowTreeACL());
        configuration.setFieldMap(new KnowTreeFieldMap());
        configuration.setDataFactory(new KnowTreeDataFactory());
    }
}
