import app.KnowTreeConfiguration;
import com.ynguyen.servlets.WebAppServlet;
import com.ynguyen.system.configurations.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class KnowTreeWebAppServlet extends WebAppServlet {
    public static String HOST_PAGE =
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
            "    <link rel=\"stylesheet\" href=\"/css/style.css\">\n" +
            "    <title>Dashboard</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<section id=\"root\"></section>\n" +
            "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n" +
            "<script src=\"/js/bundle.js\"></script>\n" +
            "</body>\n" +
            "</html>";

    @Override
    protected String getContent() {
        return HOST_PAGE;
    }

    @Override
    protected String getContentType() {
        return "text/html";
    }

    @Override
    public Configuration getConfiguration() {
        return KnowTreeConfiguration.getInstance();
    }
}
