package system.fields;

import system.property.ListProperty;
import system.property.Property;

import java.util.List;

public interface CanSearch {
    Property<List<String>> labels = new ListProperty<>("_labels");
}
