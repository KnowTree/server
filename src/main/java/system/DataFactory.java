package system;

import kinds.User;

public class DataFactory {
    public static DataFactory instance;
    public static DataFactory getInstance() {
        if (instance == null) instance = new DataFactory();
        return instance;
    }
    private DataFactory() {

    }

    public Data create(String kind) {
        switch (kind) {
            case "User" :
                return new User();
            default: throw new Error("Undefined kind " + kind);
        }
    }
}
