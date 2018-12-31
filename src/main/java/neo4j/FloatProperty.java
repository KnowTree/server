package neo4j;

public class FloatProperty extends Property<Float> {
    public FloatProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return ((Float)this.value).toString();
    }
}