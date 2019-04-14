package system.fields;

import system.property.Property;
import system.property.StringProperty;

public interface HasEmail {
    Property email = new StringProperty("email");
}
