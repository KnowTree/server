package servlets;

import system.configurations.Configuration;

import javax.servlet.*;
import java.io.IOException;

public abstract class BaseFilter implements Filter, HasConfiguration{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Configuration.setInstance(getConfiguration());
    }

}
