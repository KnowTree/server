package system;

import kinds.KNode;
import kinds.Token;
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
            case "user" :
                return new User();
            case "knode" :
                return new KNode();
            case "token" :
                return new Token();
            default: throw new Error("Undefined kind " + kind);
        }
    }
}
