package neo4j;

import acl.Policy;
import acl.PublicPolicy;

import java.util.Iterator;
import java.util.Map;

class Neo4JQueryFactory {
    private static Neo4JQueryFactory instance;

    static Neo4JQueryFactory getInstance() {
        if (instance == null) {
            instance = new Neo4JQueryFactory();
        }

        return instance;
    }

    private Neo4JQueryFactory() {
    }

    public static String getPropertyJSON(Map<String, Property> properties) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;

        String key;
        for(Iterator var3 = properties.keySet().iterator(); var3.hasNext(); sb.append(key).append(":").append(((Property)properties.get(key)).toQueryString())) {
            key = (String)var3.next();
            if (!first) {
                sb.append(",");
            } else {
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    String createNodeQuery(Node node) {
        return "CREATE (n:" + node.kind + getPropertyJSON(node.properties) + ") RETURN n";
    }

    String updateNodeQuery(Node node) {
        return "MATCH (n:" + node.kind + ") WHERE n.__key__ = " + node.key + " SET n += " + getPropertyJSON(node.properties) + " RETURN n";
    }

    String deleteNodeQuery(Node node) {
        return "MATCH (n:" + node.kind + ") WHERE n.__key__ = " + node.key + " DELETE n RETURN n";
    }

    String createRelationshipQuery(Relationship relationship) {
        return "MATCH (n:" + relationship.from.kind + "),(m:" + relationship.to.kind + ") WHERE n.__key__ = " + relationship.from.key + " AND m.__key__ = " + relationship.to.key + " CREATE (n)-[r:" + relationship.kind + getPropertyJSON(relationship.properties) + "]->(m) RETURN r";
    }

    String updateRelationshipQuery(Relationship relationship) {
        return "MATCH (n)-[r:" + relationship.kind + "]->(m) WHERE r.__key__ = " + relationship.key + " SET r += " + getPropertyJSON(relationship.properties) + " RETURN r";
    }

    String deleteRelationshipQuery(Relationship relationship) {
        return "MATCH (n)-[r:" + relationship.kind + "]->(m) WHERE r.__key__ = " + relationship.key + " DELETE r RETURN r";
    }

    public String getNodeQuery(long id) {
        return "MATCH (n) WHERE ID(n) = " + id + " RETURN n";
    }

    String getNodeByKeyQuery(String key) {
        return "MATCH (n) WHERE n." + CanBeStoredObject.KEY_FIELD + " = \"" + key + "\" RETURN n";
    }

    public String getRelationshipQuery(long id) {
        return "MATCH (n)-[r]->(m) WHERE ID(r) = " + id + " RETURN r";
    }

    public String checkGetPermission(Node user, Node data) {
        return  "MATCH (n)-[r]->(m:"+ Policy.KIND +") " +
                "WHERE m." + CanBeStoredObject.KEY_FIELD + " = \"" + PublicPolicy.NAME + "\" " +
                "AND m.__key__ = " + data.key + " " +
                "RETURN n";
    }

}