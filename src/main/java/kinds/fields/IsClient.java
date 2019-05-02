package kinds.fields;

import com.ynguyen.system.fields.HasCredential;
import com.ynguyen.system.fields.HasEmail;
import com.ynguyen.system.fields.HasId;
import com.ynguyen.system.fields.HasName;
import com.ynguyen.system.property.BooleanProperty;
import com.ynguyen.system.property.Property;

public interface IsClient extends HasId, HasEmail, HasName, HasCredential {
    Property<Boolean> is_client = new BooleanProperty("is_client");
}
