package googleapis;

import com.ynguyen.system.property.*;

import java.util.Date;
import java.util.List;

public interface HasCredential {
    Property<String> access_token = new StringProperty("access_token");
    Property<String> refresh_token = new StringProperty("refresh_token");
    Property<Long> timeout = new LongProperty("timeout");

    Property<List<String>> scopes = new ListProperty<>("scopes");
}
