package kinds.fields;

import com.ynguyen.system.property.*;

import java.util.List;

public interface CommonFields {
    Property<List<String>> categories = new ListProperty<>("categories");
    Property<Double> price = new DoubleProperty("price");
    Property<Integer> order = new IntegerProperty("order");
    Property<String> author = new StringProperty("author");

}
