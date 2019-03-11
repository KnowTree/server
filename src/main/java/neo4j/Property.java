package neo4j;

public abstract class Property<T> {
    String key;
    T value;

    public Property(String key) {
        this.key = key;
    }

    public Property<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public T getValue() {
        return value;
    }

    public abstract String toQueryString();
}