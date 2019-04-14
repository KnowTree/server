package system.fields;

import system.property.LongProperty;
import system.property.Property;

public interface HasId {
    Property<Long> id = new LongProperty("id");
}
