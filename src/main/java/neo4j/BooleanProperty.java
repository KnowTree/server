package neo4j;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return (Boolean)this.value ? "true" : "false";
    }
}