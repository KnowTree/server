package system.fields;

import neo4j.Property;
import neo4j.StringProperty;

public interface HasCredential {
    Property username = new StringProperty("username");
    Property password = new StringProperty("password");
}
