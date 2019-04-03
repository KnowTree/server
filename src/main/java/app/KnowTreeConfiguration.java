package app;

import configurations.SystemConfiguration;
import datastore.DatastoreController;
import system.DatabaseController;

public class KnowTreeConfiguration implements SystemConfiguration {
    DatabaseController databaseController;
    public KnowTreeConfiguration() {
        databaseController = new DatastoreController();
    }

    @Override
    public DatabaseController getDatabaseController() {
        return databaseController;
    }
}
