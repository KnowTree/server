package kinds;

import com.ynguyen.system.fields.HasId;
import com.ynguyen.utils.Commons;
import kinds.fields.CommonFields;
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
    public Course() {
        super("Course");
    }

    @Override
    public Property[] fields() {
        return new Property[] {
                HasId.id, CanSearch.labels,
                HasUrl.title, CommonFields.author,
                HasTracking.created_by,
                CommonFields.categories, CommonFields.price};
    }

    @Override
    public Property[] labelFields() {
        return new Property[] {HasUrl.title, CommonFields.author, CommonFields.categories};
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
            return !currentData.containProperties(HasTracking.created_by) || currentData.get(HasTracking.created_by).equals(currentUser.id());
        }
    }

    @Override
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        if (currentUser != null) {
            currentUser.set(HasTracking.created_by, currentUser.id());
        }
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
