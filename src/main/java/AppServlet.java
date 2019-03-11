import acl.AccessControlList;
import acl.AccessToken;
import neo4j.Node;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/*/
    Main servlet, include ACL
 */
public class AppServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = ensureValidApiFormat(req, resp);
        AccessToken accessToken = new AccessToken(apiFormat.getAccessToken());
        try {
            if (accessToken.isValid()) {
                Node node = (Node) AccessControlList.getInstance().read(accessToken.getUser(), apiFormat.getDataNode());
                resp.getWriter().write(node.toString());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiFormat apiFormat = ensureValidApiFormat(req, resp);
        AccessToken accessToken = new AccessToken(apiFormat.getAccessToken());
        try {
            if (accessToken.isValid()) {
                Node updateData = new Node(apiFormat.getKind());
                updateData.fromString(apiFormat.getPayload());
                Node node = (Node) AccessControlList.getInstance().write(
                        accessToken.getUser(),
                        apiFormat.getDataNode(),
                        updateData
                        );
                resp.getWriter().write(node.toString());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private ApiFormat ensureValidApiFormat( HttpServletRequest req, HttpServletResponse response) throws IOException {
        ApiFormat apiFormat = new ApiFormat(req);
        if (apiFormat.isValid()) {
            return apiFormat;
        } else {
            response.getWriter().write(ErrorHandler.createErrorMessage(apiFormat.getError()));
        }
        return null;
    }

}
