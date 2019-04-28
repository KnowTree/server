import app.*;
import servlets.RestServlet;
import system.configurations.Configuration;

import javax.servlet.ServletException;

public class KnowTreeRestServlet extends RestServlet {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
