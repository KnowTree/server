package system.property;

public class StringProperty extends Property<String> {
    public StringProperty(String key) {
        super(key);
    }

    @Override
    public String acceptValue(Object obj) {
        if (obj instanceof String) return (String) obj;
        else return obj.toString();
    }

}
