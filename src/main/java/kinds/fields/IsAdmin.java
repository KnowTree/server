package kinds.fields;

import com.ynguyen.system.fields.HasCredential;
import com.ynguyen.system.fields.HasEmail;
import com.ynguyen.system.fields.HasId;
import com.ynguyen.system.fields.HasName;
import com.ynguyen.system.property.BooleanProperty;
import com.ynguyen.system.property.Property;

public interface IsAdmin extends HasId, HasName, HasCredential, HasEmail {
    Property<Boolean> is_admin = new BooleanProperty("is_admin");
    Property<Boolean> is_system = new BooleanProperty("is_system");
}
