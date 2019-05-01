package app;

import kinds.Course;
import kinds.KNode;
import kinds.Token;
import kinds.User;
import system.Data;
import system.DataFactory;

public class KnowTreeDataFactory extends DataFactory {

    public KnowTreeDataFactory() {
        super();
    }

    @Override
    public Data create(String kind) {
        switch (kind.toLowerCase()) {
            case "user" :
                return new User();
            case "knode" :
                return new KNode();
            case "token" :
                return new Token();
            case "course" :
                return new Course();
            default: throw new Error("Undefined kind " + kind);
        }
    }
}
