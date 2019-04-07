package system.fields;

import neo4j.Property;
import neo4j.StringProperty;

public interface HasName {

    Property first_name = new StringProperty("first_name");
    Property last_name = new StringProperty("last_name");
    Property middle_name = new StringProperty("middle_name");
    Property other_name = new StringProperty("other_name");
}
