package neo4j;

import java.util.List;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.TransactionWork;

public class Neo4JController implements AutoCloseable {
    public static final String HOST = "neo4j_host";
    public static final String USERNAME = "neo4j_username";
    public static final String PASSWORD = "neo4j_password";
    public static Neo4JController instance;
    private final Driver driver;

    public static Neo4JController getInstance() {
        if (instance == null) {
            instance = new Neo4JController();
        }

        return instance;
    }

    public Neo4JController() {
        String url = System.getenv("neo4j_host");
        if (url != null && !url.isEmpty()) {
            this.driver = GraphDatabase.driver(url, AuthTokens.basic(System.getenv("neo4j_username"), System.getenv("neo4j_password")));
        } else {
            throw new Error("Missing neo4j_host");
        }
    }

    public List<Record> execute(String query) {
        Session session = this.driver.session();
        Throwable var3 = null;

        List var4;
        try {
            var4 = (List)session.writeTransaction((transaction) -> {
                StatementResult statementResult = transaction.run(query);
                return statementResult.list();
            });
        } catch (Throwable var13) {
            var3 = var13;
            throw var13;
        } finally {
            if (session != null) {
                if (var3 != null) {
                    try {
                        session.close();
                    } catch (Throwable var12) {
                        var3.addSuppressed(var12);
                    }
                } else {
                    session.close();
                }
            }

        }

        return var4;
    }

    public void close() throws Exception {
        this.driver.close();
    }
}