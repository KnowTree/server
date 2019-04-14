package kinds.fields;

import system.fields.HasCredential;
import system.fields.HasEmail;
import system.fields.HasId;
import system.fields.HasName;
import system.property.BooleanProperty;
import system.property.Property;

public interface IsClient extends HasId, HasEmail, HasName, HasCredential {
    Property<Boolean> is_client = new BooleanProperty("is_client");
}
