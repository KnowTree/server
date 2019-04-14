package system.property;

public abstract class Property<T> {
    String key;
    T value;
    String refKind;

    public Property(String key) {
        this.key = key;
    }

    public Property<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public Property refKind(String refKind) {
        this.refKind = refKind;
        return this;
    }

    public String refKind() {
        return this.refKind;
    }

    public T getValue() {
        return value;
    }

    public String key() {
        return key;
    }

    public abstract String toQueryString();
}