package servlets;

import system.configurations.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet implements HasConfiguration{

    @Override
    public void init() throws ServletException {
        super.init();
        Configuration.setInstance(getConfiguration());
    }

}
