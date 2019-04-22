package kinds.fields;

import system.fields.HasCredential;
import system.fields.HasEmail;
import system.fields.HasId;
import system.fields.HasName;
import system.property.BooleanProperty;
import system.property.Property;

public interface IsAdmin extends HasId, HasName, HasCredential, HasEmail {
    Property<Boolean> is_admin = new BooleanProperty("is_admin");
    Property<Boolean> is_system = new BooleanProperty("is_system");
}
