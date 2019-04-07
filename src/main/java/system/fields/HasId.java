package system.fields;

import neo4j.LongProperty;
import neo4j.Property;

public interface HasId {
    Property<Long> id = new LongProperty("id");
}
