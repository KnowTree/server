package neo4j;

import java.util.List;
import org.neo4j.driver.v1.Record;

public class Relationship extends Node {
    Node from;
    Node to;

    public Relationship(String kind, Node from, Node to) {
        super(kind);
        this.from = from;
        this.to = to;
    }

    public Node create() {
        String q = Neo4JQueryFactory.getInstance().createRelationshipQuery(this);
        List<Record> records = Neo4JController.getInstance().execute(q);
        this.fromRecord((Record)records.get(0));
        return this;
    }

    public Node update() {
        if (this.hasId()) {
            String q = Neo4JQueryFactory.getInstance().updateRelationshipQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(q);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update relationship without id");
        }
    }

    public Node delete() {
        if (this.hasId()) {
            String q = Neo4JQueryFactory.getInstance().deleteRelationshipQuery(this);
            List<Record> records = Neo4JController.getInstance().execute(q);
            this.fromRecord((Record)records.get(0));
            return this;
        } else {
            throw new Error("Cannot update relationship without id");
        }
    }

    public CanBeStored get(long id) {
        String q = Neo4JQueryFactory.getInstance().getRelationshipQuery(id);
        List<Record> records = Neo4JController.getInstance().execute(q);
        this.fromRecord((Record)records.get(0));
        return this;
    }
}