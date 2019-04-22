import org.json.JSONObject;
import servlets.RequestHeaders;
import system.Data;
import system.configurations.Configuration;
import system.fields.HasCursor;
import utils.SearchData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchData searchData = new SearchData(req);
        List<Data> items = Configuration.getInstance().getSystemConfiguration().getDatabaseController().search(searchData);
        List<String> filteredItems = items.stream().filter(new Predicate<Data>() {
            @Override
            public boolean test(Data data) {
                return data.canGet((Data) req.getAttribute(RequestHeaders.CURRENT_USER), data, null);
            }
        }).map(Data::toString).collect(Collectors.toList());

        JSONObject result = new JSONObject();
        result.put("data", filteredItems);
        resp.getWriter().write(result.toString());
    }
}
