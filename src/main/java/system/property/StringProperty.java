package system.property;

public class StringProperty extends Property<String> {
    public StringProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return "\"" + (String)this.value + "\"";
    }
}
