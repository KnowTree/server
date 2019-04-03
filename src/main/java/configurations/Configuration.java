package configurations;

public class Configuration {
    SystemConfiguration systemConfiguration;

    public static Configuration instance;
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    private Configuration() {

    }

    public Configuration setSystemConfiguration(SystemConfiguration cf) {
        this.systemConfiguration = cf;
        return this;
    }

    public SystemConfiguration getSystemConfiguration() { return systemConfiguration;}
}
