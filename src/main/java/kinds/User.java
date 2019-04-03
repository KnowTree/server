package kinds;

import configurations.Configuration;
import configurations.SystemConfiguration;
import system.Data;
import system.DatabaseController;

public class User extends Data {
    public User() {
        super("User", Configuration.getInstance().getSystemConfiguration().getDatabaseController());
    }


}
