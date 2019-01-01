import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neo4j.CanBeStored;
import neo4j.CanBeStoredObject;
import neo4j.Node;
import neo4j.Relationship;
import utils.ServletRequestUtils;

public class ApiServlet extends HttpServlet {
    private int ERROR_CODE = 400;

    public ApiServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            CanBeStoredObject node = apiFormat.isRelationship() ?
                    new Relationship(apiFormat.getKind()) :
                    new Node(apiFormat.getKind());
            node.get(apiFormat.getId());
            resp.getWriter().write(node.toString());
        } else {
            resp.getWriter().write(ErrorHandler.createErrorMessage(apiFormat.getError()));
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            CanBeStoredObject entity = apiFormat.isRelationship() ?
                    new Relationship(apiFormat.getKind()) :
                    new Node(apiFormat.getKind());
            entity.setId(apiFormat.getId(), false);
            entity.fromString(apiFormat.getPayload());
            entity.update();
            resp.getWriter().write(entity.toString());

        } else {
            resp.getWriter().write(ErrorHandler.createErrorMessage(apiFormat.getError()));
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            if (apiFormat.isRelationship()) {
                String from = ServletRequestUtils.getParameter(req, "from");
                String to = ServletRequestUtils.getParameter(req, "to");
                if (from == null || to == null) {
                    resp.sendError(ERROR_CODE, "Missing from or to param");
                } else {
                    Node fromNode = new Node(from);
                    Node toNode = new Node(to);
                    Relationship relationship = new Relationship(apiFormat.getKind(), fromNode, toNode);
                    relationship.fromString(apiFormat.getPayload());
                    relationship.create();
                    resp.getWriter().write(relationship.toString());
                }
            } else {
                CanBeStoredObject entity = new Node(apiFormat.getKind());
                entity.fromString(apiFormat.getPayload());
                entity.create();
                resp.getWriter().write(entity.toString());
            }
        } else {
            resp.getWriter().write(ErrorHandler.createErrorMessage(apiFormat.getError()));
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            CanBeStoredObject node = apiFormat.isRelationship() ?
                    new Relationship(apiFormat.getKind()) :
                    new Node(apiFormat.getKind());
            node.setId(apiFormat.getId(), false);
            node.delete();
            resp.getWriter().write(node.toString());
        } else {
            resp.getWriter().write(ErrorHandler.createErrorMessage(apiFormat.getError()));
        }
    }

    public void init() throws ServletException {
        super.init();
    }
}