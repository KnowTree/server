package acl;

import neo4j.CanBeStoredObject;
import neo4j.Node;
import neo4j.Property;
import neo4j.Relationship;

public class Policy {
    private Node node;
    public static String KIND = "Policy";
    public static String USE_POLICY = "USE_POLICY";
    public Policy(String name) {
        node = new Node(KIND);
        node.setKey(name);
    }

    /*
        Add an entity to a policy
     */
    public CanBeStoredObject attachTo(Node entity) {
        Relationship relationship = new Relationship(USE_POLICY, entity, node);
        return relationship.create();

    }

    /*
        Remove entity from a policy
     */
    public CanBeStoredObject detachFrom(Node entity) {
        Relationship relationship = new Relationship(USE_POLICY, entity, node);
        return relationship.delete();
    }

    /*
        Each policy have settings, call this to get those values
     */

    public void loadPolicySetting() {
        node.load();
    }

    public String getPolicyName() {
        return (String) node.property("name").getValue();
    }


}
