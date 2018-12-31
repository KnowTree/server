package neo4j;

import java.util.List;

public class ListProperty<V> extends Property<List<V>> {
    public ListProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return null;
    }
}