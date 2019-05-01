package system.property;

import java.util.ArrayList;
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

    @Override
    public List<String> createLabels(Object value) {
        List<String> result = new ArrayList<>();
        for (V item : acceptValue(value)) {
            result.add(key + ":" + item.toString());
        }
        return result;
    }

}