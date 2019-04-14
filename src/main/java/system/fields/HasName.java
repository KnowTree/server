package system.fields;

import system.property.Property;
import system.property.StringProperty;

public interface HasName {

    Property first_name = new StringProperty("first_name");
    Property last_name = new StringProperty("last_name");
    Property middle_name = new StringProperty("middle_name");
    Property other_name = new StringProperty("other_name");
}
