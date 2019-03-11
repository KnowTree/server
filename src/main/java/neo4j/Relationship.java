package neo4j;

import java.util.List;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.types.Entity;

public class Relationship extends CanBeStoredObject {
    Node from;
    Node to;

    public Relationship(String kind, Node from, Node to) {
        super(kind);
        this.from = from;
        this.to = to;
    }

    public Relationship(String kind) {
        super(kind);
    }

    @Override
    public Entity toEntity(Record record) {
        return record.get("r").asEntity();
    }

    public CanBeStoredObject create() {
        if (from.hasKey() && to.hasKey()) {
            String q = Neo4JQueryFactory.getInstance().createRelationshipQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(q);
            this.fromRecord((Record) records.get(0));
        } else {
            throw new Error("Missing from or to key");
        }
        return this;
    }

    public CanBeStoredObject update() {
        if (this.hasKey()) {
            String q = Neo4JQueryFactory.getInstance().updateRelationshipQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(q);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update relationship without id");
        }
    }

    public CanBeStoredObject delete() {
        if (this.hasKey()) {
            String q = Neo4JQueryFactory.getInstance().deleteRelationshipQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(q);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update relationship without id");
        }
    }

    public CanBeStored get(long id) {
        if (this.hasKey()) {
            String q = Neo4JQueryFactory.getInstance().getRelationshipQuery(id);
            List<Record> records = Neo4JController.getInstance().execute(q);
            if (records.size() > 0) {
                this.fromRecord((Record) records.get(0));
            }
        } else {
            throw new Error("Cannot update relationship without id");
        }
        return this;
    }

    @Override
    public CanBeStored get(String key) {
        return null;
    }
}