package acl;

import neo4j.Node;

public class User extends Node {
    public static String KIND = "User";
    public User() {
        super(KIND);
    }

}
