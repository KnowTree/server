package neo4j;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.neo4j.driver.internal.value.*;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Entity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class CanBeStoredObject implements CanBeStored {
    private static final Logger log = Logger.getLogger(Node.class.getName());
    String kind;
    Long id;
    Map<String, Property> properties;

    public static final String KIND_DELIMITER = "@";

    public CanBeStoredObject(String kind) {
        this.properties = new HashMap<>();
        String[] parts = kind.split(KIND_DELIMITER);
        this.kind = parts[0];
        if (parts.length >= 2) {
            id = Long.valueOf(parts[1]);
        }

    }

    public CanBeStoredObject(String kind, long id) {
        this(kind);
        this.get(id);
    }

    public CanBeStoredObject setId(Long id, boolean loadRemote) {
        this.id = id;
        if (loadRemote) {
            get(id);
        }
        return this;
    }

    public CanBeStoredObject property(Property property) {
        this.properties.put(property.key, property);
        return this;
    }

    public Property property(String name) {
        return (Property) this.properties.get(name);
    }

    public CanBeStoredObject fromRecord(Record record) {
        Entity entity = record.get("n").asEntity();
        this.id = entity.id();
        Iterable<String> keys = entity.keys();
        keys.forEach((s) -> {
            Value value = entity.get(s);
            Property property = null;
            if (value instanceof StringValue) {
                property = (new StringProperty(s)).setValue(value.asString());
            } else if (value instanceof BooleanValue) {
                property = (new BooleanProperty(s)).setValue(value.asBoolean());
            } else if (value instanceof FloatValue) {
                property = (new FloatProperty(s)).setValue(value.asFloat());
            } else if (value instanceof IntegerValue) {
                property = (new IntegerProperty(s)).setValue(value.asInt());
            } else if (value instanceof NullValue) {
                property = (new StringProperty(s)).setValue(null);
            } else if (value instanceof ListValue) {
                property = (new ListProperty(s)).setValue(value.asList());
            }

            if (property != null) {
                this.property(property);
            } else {
                log.warning("Cannot update property " + s + " with value " + value.toString());
            }

        });
        return this;
    }

    public CanBeStoredObject fromString(String raw) {
        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        Map<String, Object> props = gson.fromJson(raw, gsonType);
        return fromMap(props);

    }

    public CanBeStoredObject fromMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            Object value = map.get(key);
            Property p = null;
            if (value instanceof String) {
                p = new StringProperty(key).setValue((String) value);
            } else if (value instanceof Float) {
                p = new FloatProperty(key).setValue((Float) value);

            } else if (value instanceof Integer) {
                p = new IntegerProperty(key).setValue((Integer) value);

            } else if (value instanceof List) {

                p = new ListProperty(key).setValue((List) value);

            } else if (value instanceof Boolean) {
                p = new BooleanProperty(key).setValue((Boolean) value);
            } else if (value == null) {
                p = new StringProperty(key).setValue(null);
            }

            if (p != null) {

                property(p);
            } else {
                log.warning("Cannot update property " + key + " with value " + value.toString());
            }

        }

        return this;
    }

    public boolean hasId() {
        return this.id != null;
    }

    public String toString() {
        return Neo4JQueryFactory.getPropertyJSON(this.properties);
    }

}
