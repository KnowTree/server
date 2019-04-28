package app;

import system.configurations.SystemConfiguration;
import datastore.DatastoreController;
import system.DatabaseController;

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
