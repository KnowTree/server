package kinds;

import kinds.fields.CommonFields;
import kinds.fields.HasUrl;
import kinds.fields.IsAdmin;
import org.json.JSONObject;
import com.ynguyen.system.Data;
import com.ynguyen.system.DatabaseController;
import com.ynguyen.system.configurations.Configuration;
import com.ynguyen.system.fields.CanSearch;
import com.ynguyen.system.fields.HasId;
import com.ynguyen.system.fields.HasTracking;
import com.ynguyen.system.property.LongProperty;
import com.ynguyen.system.property.Property;
import com.ynguyen.utils.RestApiFormat;

import java.util.Arrays;
import java.util.List;

public class KNode extends Data {
    LongProperty courseId = new LongProperty("course_id");

    public KNode() {
        super("Knode");
    }

    @Override
    public boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        return true;
    }

    @Override
    public boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        if (currentUser == null) return false;
        else {
            return !currentData.containProperties(HasTracking.created_by) || currentData.getLong(HasTracking.created_by).equals(currentUser.id());
        }
    }

    @Override
    public boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        if (currentUser != null) {
            data.set(HasTracking.created_by, currentUser.id());
        }

        return currentUser != null;
    }

    @Override
    public boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        return !data.containProperties(HasTracking.created_by) ||  currentUser != null && currentUser.id().equals(data.getLong(HasTracking.created_by));
    }

    public KNode setTitle(String title) {
        set(HasUrl.title, title);
        return this;
    }

    public KNode setUrl(String url) {
        set(HasUrl.url, url);
        return this;
    }

    @Override
    public Property[] fields() {
        return new Property[] {HasId.id,
                HasUrl.url, HasUrl.title, courseId,
                CommonFields.order,
                CanSearch.labels, HasTracking.created_by};
    }

    @Override
    public Property[] labelFields() {
        return new Property[] {HasUrl.title, HasId.id};
    }

    @Override
    public List<String> addCustomLabels() {
        return Arrays.asList("course_id:" + get(courseId));
    }
}
