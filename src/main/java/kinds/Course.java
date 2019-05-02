package kinds;

import kinds.fields.HasUrl;
import org.json.JSONObject;
import com.ynguyen.system.Data;
import com.ynguyen.system.fields.CanSearch;
import com.ynguyen.system.fields.HasTracking;
import com.ynguyen.system.property.Property;
import com.ynguyen.system.property.StringProperty;
import com.ynguyen.utils.RestApiFormat;

import java.util.List;

public class Course extends Data {
    Property<String> author = new StringProperty("author");
    public Course() {
        super("Course");
    }

    @Override
    public Property[] fields() {
        return new Property[] {HasUrl.title, author, CanSearch.labels};
    }

    @Override
    public Property[] labelFields() {
        return new Property[] {HasUrl.title, author};
    }

    @Override
    public List<String> addCustomLabels() {
        return null;
    }

    @Override
    public boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        return true;
    }

    @Override
    public boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        if (currentUser == null) return false;
        else {
            return currentData.get(HasTracking.created_by).equals(currentUser.id());
        }
    }

    @Override
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        return currentUser != null;
    }

    @Override
    public boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        if (currentUser == null) return false;
        else {
            return data.get(HasTracking.created_by).equals(currentUser.id());
        }
    }
}
