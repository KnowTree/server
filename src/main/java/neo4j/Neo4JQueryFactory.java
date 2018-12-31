package neo4j;

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
        return "MATCH (n:" + node.kind + ") WHERE ID(n) = " + node.id + " SET n += " + getPropertyJSON(node.properties) + " RETURN n";
    }

    String deleteNodeQuery(Node node) {
        return "MATCH (n:" + node.kind + ") WHERE ID(n) = " + node.id + " DELETE n RETURN n";
    }

    String createRelationshipQuery(Relationship relationship) {
        return "MATCH (n:" + relationship.from.kind + "),(m:" + relationship.to.kind + ") WHERE ID(n) = " + relationship.from.id + " AND ID(m) = " + relationship.to.id + " CREATE (n)-[r:" + relationship.kind + getPropertyJSON(relationship.properties) + "]->(m) RETURN r";
    }

    String updateRelationshipQuery(Relationship relationship) {
        return "MATCH (n)-[r:" + relationship.kind + "]->(m) WHERE ID(r) = " + relationship.id + " SET r += " + getPropertyJSON(relationship.properties) + " RETURN r";
    }

    String deleteRelationshipQuery(Relationship relationship) {
        return "MATCH (n)-[r:" + relationship.kind + "]->(m) WHERE ID(r) = " + relationship.id + " DELETE r RETURN r";
    }

    public String getNodeQuery(long id) {
        return "MATCH (n) WHERE ID(n) = " + id + " RETURN n";
    }

    public String getRelationshipQuery(long id) {
        return "MATCH (n)-[r]->(m) WHERE ID(r) = " + id + " RETURN r";
    }
}