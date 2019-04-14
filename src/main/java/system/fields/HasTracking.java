package system.fields;

import system.property.DateProperty;
import system.property.LongProperty;
import system.property.Property;

import java.util.Date;

public interface HasTracking {
    Property<Long> created_by = new LongProperty("created_by");
    Property<Date> created_date = new DateProperty("created_date");
    Property<Date> updated_date = new DateProperty("updated_date");
}
