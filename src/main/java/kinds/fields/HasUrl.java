package kinds.fields;

import com.ynguyen.system.property.Property;
import com.ynguyen.system.property.StringProperty;

public interface HasUrl {
    Property<String> url = new StringProperty("url");
    Property<String> title = new StringProperty("title");
}
