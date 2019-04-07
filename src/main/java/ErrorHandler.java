import org.json.JSONObject;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler {
    public static final int ERROR = 0;
    public ErrorHandler() {
    }

    public static String createErrorMessage(String msg) {
        return "{ error : \"" + msg + "\"}";
    }

    public static void handle(HttpServletRequest req, HttpServletResponse resp, int errorCode, String message) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        json.put("error_code", errorCode);
        json.put("message", message);
        resp.getWriter().write(json.toString());
    }
}
