package system.property;

public abstract class Property<T> {
    public static final String CANNOT_PARSE = "Can not parse value";
    String key;
    String refKind;

    public Property(String key) {
        this.key = key;
    }

    public Property refKind(String refKind) {
        this.refKind = refKind;
        return this;
    }

    public String refKind() {
        return this.refKind;
    }

    public String key() {
        return key;
    }

    /**
     * convert obj to T
     * @param obj value
     * @return T
     */
    public abstract T acceptValue(Object obj);
}