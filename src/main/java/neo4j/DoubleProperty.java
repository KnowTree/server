package neo4j;

public class DoubleProperty extends Property<Double> {
    public DoubleProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return ((Double)this.value).toString();
    }
}