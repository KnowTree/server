package configurations;

import neo4j.Property;
import system.fields.FieldMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    SystemConfiguration systemConfiguration;
    FieldMap fieldMap;


    public static Configuration instance;
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    private Configuration() {
        fieldMap = new FieldMap();
    }

    public Configuration setSystemConfiguration(SystemConfiguration cf) {
        this.systemConfiguration = cf;
        return this;
    }

    public SystemConfiguration getSystemConfiguration() { return systemConfiguration;}

    public FieldMap fieldMap() {
        return this.fieldMap;
    }
}
