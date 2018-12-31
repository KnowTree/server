package utils;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class ServletRequestUtils {
    public ServletRequestUtils() {
    }

    public static ArrayList<String> getURIParts(HttpServletRequest req) {
        String path = req.getRequestURI();
        String[] parts = path.split("/");
        ArrayList<String> list = new ArrayList();
        String[] var4 = parts;
        int var5 = parts.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String part = var4[var6];
            if (!part.isEmpty()) {
                list.add(part);
            }
        }

        return list;
    }

    public static String getParameter(HttpServletRequest req, String name) {
        String raw = req.getParameter(name);
        return raw == null || raw.isEmpty() ? null : raw;
    }
}