package system.property;

public class LongProperty extends Property<Long> {
    public LongProperty(String key) {
        super(key);
    }

    @Override
    public Long acceptValue(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        } else if (obj instanceof String) {
            return Long.valueOf((String) obj);
        } else if (obj instanceof Double) {
            return ((Double) obj).longValue();
        } else if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        }
        throw new Error(CANNOT_PARSE);
    }

}
