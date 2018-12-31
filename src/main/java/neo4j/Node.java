package neo4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.neo4j.driver.internal.value.BooleanValue;
import org.neo4j.driver.internal.value.FloatValue;
import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.internal.value.NullValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Entity;

public class Node implements CanBeStored {
    private static final Logger log = Logger.getLogger(Node.class.getName());
    String kind;
    Long id;
    Map<String, Property> properties;

    public Node(String kind) {
        this.properties = new HashMap();
        this.kind = kind;
    }

    public Node(String kind, long id) {
        this(kind);
        this.get(id);
    }

    public Node property(Property property) {
        this.properties.put(property.key, property);
        return this;
    }

    public Property property(String name) {
        return (Property)this.properties.get(name);
    }

    public Node fromRecord(Record record) {
        Entity entity = record.get("n").asEntity();
        this.id = entity.id();
        Iterable<String> keys = entity.keys();
        keys.forEach((s) -> {
            Value value = entity.get(s);
            Property property = null;
            if (value instanceof StringValue) {
                property = (new StringProperty(s)).setValue(value.asString());
            } else if (value instanceof BooleanValue) {
                property = (new BooleanProperty(s)).setValue(value.asBoolean());
            } else if (value instanceof FloatValue) {
                property = (new FloatProperty(s)).setValue(value.asFloat());
            } else if (value instanceof IntegerValue) {
                property = (new IntegerProperty(s)).setValue(value.asInt());
            } else if (value instanceof NullValue) {
                property = (new StringProperty(s)).setValue(null);
            } else if (value instanceof ListValue) {
                property = (new ListProperty(s)).setValue(value.asList());
            }

            if (property != null) {
                this.property(property);
            } else {
                log.warning("Cannot update property " + s + " with value " + value.toString());
            }

        });
        return this;
    }

    public boolean hasId() {
        return this.id != null;
    }

    public Node create() {
        if (this.hasId()) {
            throw new Error("Cannot create existing node id = " + this.id);
        } else {
            Neo4JController controller = Neo4JController.getInstance();
            Neo4JQueryFactory queryFactory = Neo4JQueryFactory.getInstance();
            String query = queryFactory.createNodeQuery(this);
            List<Record> recordList = controller.execute(query);
            this.fromRecord((Record)recordList.get(0));
            return this;
        }
    }

    public Node update() {
        if (!this.hasId()) {
            String query = Neo4JQueryFactory.getInstance().updateNodeQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(query);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update node without id");
        }
    }

    public Node delete() {
        if (!this.hasId()) {
            String query = Neo4JQueryFactory.getInstance().deleteNodeQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(query);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot delete node without id");
        }
    }

    public CanBeStored get(long id) {
        String q = Neo4JQueryFactory.getInstance().getNodeQuery(id);
        List<Record> records = Neo4JController.getInstance().execute(q);
        if (records.size() == 0) {
            throw new Error("Cannot find node " + this.kind + "with id " + id);
        } else {
            this.fromRecord((Record)records.get(0));
            return this;
        }
    }

    public String toString() {
        return Neo4JQueryFactory.getPropertyJSON(this.properties);
    }
}