package acl;

import neo4j.Node;

public class UserGroup extends Node {
    public static String KIND = "UserGroup";
    public static String DEFAULT_GROUP_NAME = "All";
    public UserGroup() {
        super(KIND);
    }

    public static UserGroup getDefaultUserGroup() {
        return (UserGroup) new UserGroup().setKey(DEFAULT_GROUP_NAME);
    }
}
