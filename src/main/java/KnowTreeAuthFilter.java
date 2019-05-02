import app.KnowTreeConfiguration;
import com.ynguyen.servlets.AuthFilter;
import com.ynguyen.system.configurations.Configuration;

public class KnowTreeAuthFilter extends AuthFilter {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
