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
import java.util.UUID;
import java.util.logging.Logger;

public abstract class CanBeStoredObject implements CanBeStored {
    private static final Logger log = Logger.getLogger(Node.class.getName());
    public static String KEY_FIELD = "__key__";
    String kind;
    String key;
    Map<String, Property> properties;

    public static final String KIND_DELIMITER = "@";
    public static final String PROPERTY_START = "{";
    public static final String PROPERTY_END = "}";

    public CanBeStoredObject(String kind) {
        this.properties = new HashMap<>();
        String[] parts = kind.split(KIND_DELIMITER);
        this.kind = parts[0];
        if (parts.length >= 2) {
            key = parts[1];
        } else {
            key = UUID.randomUUID().toString();
        }

    }

    public String getKey() {
        return this.key;
    }


    public CanBeStoredObject setKey(String key) {
        this.key = key;
        property(new StringProperty(KEY_FIELD).setValue(key));
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
        Entity entity = toEntity(record);
        this.key = entity.get(KEY_FIELD).asString();
        Iterable<String> keys = entity.keys();
        keys.forEach((s) -> {
            Value value = entity.get(s);
            Property property = null;
            if (value instanceof StringValue) {
                property = (new StringProperty(s)).setValue(value.asString());
            } else if (value instanceof BooleanValue) {
                property = (new BooleanProperty(s)).setValue(value.asBoolean());
            } else if (value instanceof FloatValue) {
                property = (new DoubleProperty(s)).setValue(value.asDouble());
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

    public abstract Entity toEntity(Record record);

    public CanBeStoredObject fromString(String raw) {
        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap<String, Object>>() {}.getType();
        if (raw == null || raw.isEmpty()) {
            raw = PROPERTY_START + PROPERTY_END;
        }
        Map<String, Object> props = gson.fromJson(raw, gsonType);
        if (props != null) {
            return fromMap(props);
        } else {
            log.warning("Cannot parse props from " + raw);
            return this;
        }

    }

    public CanBeStoredObject fromMap(Map<String, Object> map) {
        for (String key : map.keySet()) {
            Object value = map.get(key);
            Property p = null;
            if (value instanceof String) {
                p = new StringProperty(key).setValue((String) value);
            } else if (value instanceof Float || value instanceof Double) {
                p = new DoubleProperty(key).setValue((Double) value);

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


    public boolean hasKey() {return this.key != null; }

    public String toString() {
        return Neo4JQueryFactory.getPropertyJSON(this.properties);
    }

    public void load() {
        if (hasKey()) {
            get(this.key);
        } else {
            log.warning("Missing key when load()");
        }
    }

    public String getKind() {
        return kind;
    }

}
