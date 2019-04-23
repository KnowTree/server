package utils;

public class QueryData<T> {
    String field, operator;
    T value;
    public QueryData(String field, String operator, T value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public String getField() {
        return this.field;
    }

    public String getOperator() {
        return this.operator;
    }

    public T getValue() {
        return this.value;
    }

}
