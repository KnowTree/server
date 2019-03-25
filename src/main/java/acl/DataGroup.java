package acl;

import neo4j.CanBeStoredObject;
import neo4j.Node;
import neo4j.Property;
import neo4j.Relationship;

public class DataGroup extends Node{
    public static String KIND = "DataGroup";
    public static String PUBLIC = "Public";
    public DataGroup() {
        super(KIND);
    }

    public static DataGroup getPublicDataGroup() {
        return (DataGroup) new DataGroup().setKey(PUBLIC);
    }

    public static DataGroup getDataGroup(String name) {
        return (DataGroup) new DataGroup().setKey(name);
    }
}
