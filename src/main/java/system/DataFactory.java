package system;

import kinds.KNode;
import kinds.Token;
import kinds.User;

public abstract class DataFactory {
    protected DataFactory() {

    }

    public abstract Data create(String kind);
}
