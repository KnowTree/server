package system.property;

import java.util.List;

public class ListProperty<V> extends Property<List<V>> {
    public ListProperty(String key) {
        super(key);
    }

    @Override
    public List<V> acceptValue(Object obj) {
        if (obj instanceof List) {
            return (List<V>) obj;
        }
        throw new Error(CANNOT_PARSE);
    }

}