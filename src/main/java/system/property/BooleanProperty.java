package system.property;

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
}