import app.KnowTreeConfiguration;
import servlets.WebAppServlet;
import system.configurations.Configuration;

public class KnowTreeWebAppServlet extends WebAppServlet {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
