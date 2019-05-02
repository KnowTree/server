package app;

import com.ynguyen.system.configurations.SystemConfiguration;
import com.ynguyen.datastore.DatastoreController;
import com.ynguyen.system.DatabaseController;

public class KnowTreeSystemConfiguration implements SystemConfiguration {
    DatabaseController databaseController;
    public KnowTreeSystemConfiguration() {
        databaseController = new DatastoreController();
    }

    @Override
    public DatabaseController getDatabaseController() {
        return databaseController;
    }
}
