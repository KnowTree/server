import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neo4j.Node;

public class ApiServlet extends HttpServlet {
    private int ERROR_CODE = 400;

    public ApiServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            Node node = new Node(apiFormat.getKind(), apiFormat.getId());
            resp.getWriter().write(node.toString());
        } else {
            resp.sendError(this.ERROR_CODE, apiFormat.getError());
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doPost(req, resp);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    public void init() throws ServletException {
        super.init();
    }
}