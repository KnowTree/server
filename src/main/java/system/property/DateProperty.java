package system.property;

import java.util.Date;

public class DateProperty extends Property<Date> {
    public DateProperty(String key) {
        super(key);
    }

    @Override
    public String toQueryString() {
        return null;
    }
}
