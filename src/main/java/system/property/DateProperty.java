package system.property;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProperty extends Property<Date> {
    private String format = "dd/MM/yyyy";
    public DateProperty(String key) {
        super(key);
    }

    @Override
    public Date acceptValue(Object obj) {
        if (obj instanceof Date) return (Date) obj;
        else if (obj instanceof Long) {
            return new Date((Long) obj);
        } else if (obj instanceof String) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            try {
                return simpleDateFormat.parse((String) obj);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new Error(CANNOT_PARSE);
            }
        }
        throw new Error(CANNOT_PARSE);
    }

    public DateProperty format(String format) {
        this.format = format;
        return this;
    }

}
