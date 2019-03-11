package acl;

import neo4j.CanBeStoredObject;
import neo4j.Neo4JController;
import neo4j.Node;
import org.neo4j.driver.v1.Record;

import java.util.List;

public class AccessControlList {
    private static AccessControlList instance = null;
    public static AccessControlList getInstance() {
        if(instance == null) instance = new AccessControlList();
        return instance;
    }

    private AccessControlList() {

    }

    public CanBeStoredObject read(Node user, Node data) {
        String q = "MATCH (data)<-[r2:HAS_DATA]-(datagroup:DataGroup)<-[r:READ]-(usergroup:UserGroup)-[r1:HAS_MEMBER]->(user:User) WHERE data.__key__ = \"%s\"" +
                " AND user.__key__ = \"%s\" RETURN data";
        String query = String.format(q, data.getKey(), user.getKey());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return data.fromRecord(records.get(0));
        } else {
            return null;
        }
    }

    public CanBeStoredObject write(Node user, Node targetNode, Node updateData) {
        String q = "MATCH (data)<-[r2:HAS_DATA]-(datagroup:DataGroup)<-[r:WRITE]-(usergroup:UserGroup)-[r1:HAS_MEMBER]->(user:User) WHERE data.__key__ = \"%s\"" +
                " AND user.__key__ = \"%s\" " +
                " SET data += \"%s\"" +
                " RETURN data";
        String query = String.format(q, targetNode.getKey(), user.getKey(), updateData.toString());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return targetNode.fromRecord(records.get(0));
        } else {
            return null;
        }
    }
    public CanBeStoredObject addData(Node user, Node data, Node dataGroup) {
        if (data.hasKey()) {
            throw new Error("Cannot add data that already has id");
        }
        if (!user.hasKey() || !dataGroup.hasKey()) {
            throw new Error("Missing id for user & data group when add data");
        }
        String dataKind = data.getKind();
        String q = "CREATE (data:" + dataKind + ")" +
                " SET data += \"%s\"" +
                " MATCH (datagroup:DataGroup) WHERE datagroup.__key__ = \"%s\"" +
                " MATCH (user:User) WHERE user.__key__ = \"%s\"" +
                " CREATE (data)<-[r:HAS_DATA]-(datagroup)" +
                " CREATE (data)<-[r2:CREATE]-(user)" +
                " RETURN data";
        String query = String.format(q, user.getKey(), dataGroup.getKey(), user.getKey(), data.toString());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return data.fromRecord(records.get(0));
        }
        return null;
    }

    public CanBeStoredObject addUser(Node user, Node userGroup) {
        if (user.hasKey()) {
            throw new Error("Cannot add user that already has id");
        }
        if (!userGroup.hasKey()) {
            throw new Error("Missing id for user group when add user");
        }
        String q = "CREATE (user:User %s )" +
                " MATCH (usergroup:UserGroup) WHERE usergroup.__key__ = \"%s\"" +
                " CREATE (user)<-[r:HAS_MEMBER]-(usergroup)" +
                " RETURN user";
        String query = String.format(q, user.toString(),userGroup.getKey());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return user.fromRecord(records.get(0));
        }
        return null;
    }

    public CanBeStoredObject deleteData(Node user, Node data) {
        String q = "MATCH (data:%s) WHERE data.__key__ = %s RETURN data";
        String query = String.format(q, data.getKind(), data.getKey());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return data.fromRecord(records.get(0));
        }
        return null;
    }

    public CanBeStoredObject deleteUser(Node user) {
        String q = "MATCH (user:User) WHERE user.__key__ = %s RETURN user";
        String query = String.format(q, user.getKey());
        List<Record> records = Neo4JController.getInstance().execute(query);
        if (records.size() > 0) {
            return user.fromRecord(records.get(0));
        }
        return null;
    }



}
