package system.fields;

import neo4j.Property;
import neo4j.StringProperty;

public interface HasEmail {
    Property email = new StringProperty("email");
}
