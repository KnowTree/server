package kinds.fields;

import system.property.Property;
import system.property.StringProperty;

public interface HasUrl {
    Property<String> url = new StringProperty("url");
    Property<String> title = new StringProperty("title");
}
