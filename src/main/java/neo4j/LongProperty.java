package neo4j;

public class LongProperty extends Property<Long> {
    public LongProperty(String key) {
        super(key);
    }

    @Override
    public String toQueryString() {
        return "" + value;
    }
}
