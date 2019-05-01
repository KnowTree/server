package system.property;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntegerProperty extends Property<Integer> {
    public IntegerProperty(String key) {
        super(key);
    }

    @Override
    public Integer acceptValue(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else if (obj instanceof String) {
            return Integer.valueOf((String) obj);
        } else if (obj instanceof Long) {
            return ((Long) obj).intValue();
        } else if (obj instanceof Double) {
            return ((Double) obj).intValue();
        }
        throw new Error(CANNOT_PARSE);
    }

    @Override
    public List<String> createLabels(Object value) {
        return Collections.singletonList(key + ":" + acceptValue(value).toString());
    }

}