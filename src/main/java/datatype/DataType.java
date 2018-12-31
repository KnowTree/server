package datatype;

public interface DataType<T> {
    T fromString(String var1);

    String toString(T var1);
}