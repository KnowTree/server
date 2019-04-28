import app.KnowTreeConfiguration;
import servlets.AuthFilter;
import system.configurations.Configuration;

public class KnowTreeAuthFilter extends AuthFilter {
    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
