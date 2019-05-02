import app.*;
import com.ynguyen.servlets.RestServlet;
import com.ynguyen.system.configurations.Configuration;

import javax.servlet.ServletException;

public class KnowTreeRestServlet extends RestServlet {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
