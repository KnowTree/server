package system.property;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String key) {
        super(key);
    }

    @Override
    public Boolean acceptValue(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else if (obj instanceof String){
            String string = (String) obj;
            if (string.equals("Yes") || string.equals("True")) return true;
            if (string.equals("No") || string.equals("False")) return false;
        }
        throw new Error(CANNOT_PARSE);
    }

    @Override
    public List<String> createLabels(Boolean value) {
        return Collections.singletonList(key + ":" + (value ? "true" : "false"));
    }

}