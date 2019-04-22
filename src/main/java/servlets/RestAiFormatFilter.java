package servlets;


import utils.RestApiFormat;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RestAiFormatFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RestApiFormat restApiFormat = new RestApiFormat((HttpServletRequest) request);
        request.setAttribute(RequestHeaders.REQUEST_URL_DATA, restApiFormat);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
