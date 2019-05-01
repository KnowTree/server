package app;

import kinds.Course;
import kinds.KNode;
import kinds.User;
import system.Data;
import system.fields.FieldMap;
import system.property.Property;

public class KnowTreeFieldMap extends FieldMap {
    public static FieldMap instance = null;
    public static FieldMap getInstance() {
        if (instance == null) instance = new KnowTreeFieldMap();
        return instance;
    }
    public KnowTreeFieldMap() {
        super();
        registerKind(new User());
        registerKind(new KNode());
        registerKind(new Course());

    }

    public void registerKind(Data sample) {
        for (Property property : sample.fields()) {
            String key = sample.getKind() + "." + property.key();
            add(key, property, sample.getKind());
        }
    }
}
