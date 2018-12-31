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

public class Node extends CanBeStoredObject {


    public Node(String kind) {
        super(kind);
    }

    public Node(String kind, long id) {
        super(kind, id);
    }

    @Override
    public Entity toEntity(Record record) {
        return record.get("n").asEntity();
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
        if (this.hasId()) {
            String query = Neo4JQueryFactory.getInstance().updateNodeQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(query);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update node without id");
        }
    }

    public Node delete() {
        if (this.hasId()) {
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

}