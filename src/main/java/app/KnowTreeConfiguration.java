package app;

import com.ynguyen.system.configurations.Configuration;

public class KnowTreeConfiguration extends Configuration {

    public static Configuration getInstance() {
        if (instance == null) instance = new KnowTreeConfiguration();
        return instance;
    }

    private KnowTreeConfiguration() {
        super();
        setAppName("KnowTree");
        setSystemConfiguration(new KnowTreeSystemConfiguration());
        setACL(new KnowTreeACL());
        setFieldMap(new KnowTreeFieldMap());
        setDataFactory(new KnowTreeDataFactory());
    }
}
