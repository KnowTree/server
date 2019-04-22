package system.configurations;

import system.DataFactory;
import system.acl.ACL;
import system.fields.FieldMap;

public class Configuration {
    SystemConfiguration systemConfiguration;
    FieldMap fieldMap;
    ACL acl;
    DataFactory dataFactory;


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

    public Configuration setACL(ACL acl) {
        this.acl = acl;
        return this;
    }

    public SystemConfiguration getSystemConfiguration() { return systemConfiguration;}

    public FieldMap fieldMap() {
        return this.fieldMap;
    }

    public void setFieldMap(FieldMap fieldMap) {
        this.fieldMap = fieldMap;
    }

    public DataFactory dataFactory() {
        return this.dataFactory;
    }

    public void setDataFactory(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public ACL getAcl() {
        return this.acl;
    }
}
