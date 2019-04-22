package system.property;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoubleProperty extends Property<Double> {
    public DoubleProperty(String key) {
        super(key);
    }

    @Override
    public Double acceptValue(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        } else if (obj instanceof String) {
            return Double.valueOf((String) obj);
        } else if (obj instanceof Long) {
            return ((Long) obj).doubleValue();
        } else if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        }
        throw new Error(CANNOT_PARSE);
    }

    @Override
    public List<String> createLabels(Double value) {
        return Collections.singletonList(key + ":" + value.toString());
    }
}