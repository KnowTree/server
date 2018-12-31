package datatype;

public class LongType implements DataType<Long> {
    private static LongType instance;

    public LongType() {
    }

    public static LongType getInstance() {
        if (instance == null) {
            instance = new LongType();
        }

        return instance;
    }

    public Long fromString(String raw) {
        return Long.valueOf(raw);
    }

    public String toString(Long data) {
        return data.toString();
    }
}
