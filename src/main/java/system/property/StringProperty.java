package system.property;

import java.util.Arrays;
import java.util.List;

public class StringProperty extends Property<String> {
    public StringProperty(String key) {
        super(key);
    }

    @Override
    public String acceptValue(Object obj) {
        if (obj instanceof String) return (String) obj;
        else return obj.toString();
    }

    @Override
    public List<String> createLabels(Object value) {
        return Arrays.asList(key + ":" + acceptValue(value));
    }

}
