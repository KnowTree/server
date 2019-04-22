package system.fields;

import system.property.Property;
import system.property.StringProperty;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FieldMap {
    HashMap<String, Property> map;
    HashMap<String, ArrayList<Property>> groups;

    protected FieldMap() {
        map = new HashMap<>();
        groups = new HashMap<>();
    }

    public FieldMap addGroup(String groupName, List<Property> fields) {
        if (!groups.containsKey(groupName)) {
            groups.put(groupName, new ArrayList<>());
        }
        groups.get(groupName).addAll(fields);
        return this;
    }

    public List<Property> getFields(String groupName) {
        return groups.get(groupName);
    }

    public void add(String key, Property property, String... groups) {
        if (map.containsKey(key)) {
            Logger.getLogger(FieldMap.class.getSimpleName()).log(Level.WARNING, "Duplicate field " + key);
        } else {
            map.put(key, property);
            for (String group : groups) {
                addGroup(group, Collections.singletonList(property));
            }
        }
    }

    public Property get(String key) {
        return map.get(key);
    }
}
