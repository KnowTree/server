import app.*;
import com.ynguyen.servlets.SearchServlet;
import com.ynguyen.system.configurations.Configuration;

import javax.servlet.ServletException;

public class KnowTreeSearchServlet extends SearchServlet {

    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
