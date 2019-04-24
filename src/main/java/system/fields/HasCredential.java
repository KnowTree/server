package system.fields;

import system.property.Property;
import system.property.StringProperty;

public interface HasCredential {
    Property username = new StringProperty("username");
    Property password = new StringProperty("password");
    Property token = new StringProperty("token");
}
