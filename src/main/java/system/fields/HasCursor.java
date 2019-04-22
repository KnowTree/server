package system.fields;

import system.property.Property;
import system.property.StringProperty;

public interface HasCursor {
    Property<String> cursor = new StringProperty("cursor");
}
