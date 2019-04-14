package system.property;

public class IntegerProperty extends Property<Integer> {
    public IntegerProperty(String key) {
        super(key);
    }

    public String toQueryString() {
        return ((Integer)this.value).toString();
    }
}