import app.*;
import servlets.SearchServlet;
import system.configurations.Configuration;

import javax.servlet.ServletException;

public class KnowTreeSearchServlet extends SearchServlet {

    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
