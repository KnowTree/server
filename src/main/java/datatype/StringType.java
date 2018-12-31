package datatype;

public class StringType implements DataType<String> {
    static StringType instance;

    public StringType() {
    }

    public static StringType getInstance() {
        if (instance == null) {
            instance = new StringType();
        }

        return instance;
    }

    public String fromString(String raw) {
        return raw;
    }

    public String toString(String data) {
        return data;
    }
}