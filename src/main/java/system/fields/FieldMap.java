package system.fields;

import configurations.Configuration;
import neo4j.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FieldMap {
    HashMap<String, ArrayList<Property>> map;

    public FieldMap() {
        map = new HashMap<>();
    }

    public FieldMap addFields(String groupName, List<Property> fields) {
        if (!map.containsKey(groupName)) {
            map.put(groupName, new ArrayList<>());
        }
        map.get(groupName).addAll(fields);
        return this;
    }

    public List<Property> getFields(String groupName) {
        return map.get(groupName);
    }
}
