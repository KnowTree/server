import app.KnowTreeConfiguration;
import com.ynguyen.servlets.WebAppServlet;
import com.ynguyen.system.configurations.Configuration;

public class KnowTreeWebAppServlet extends WebAppServlet {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
