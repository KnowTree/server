package servlets;

import kinds.User;
import org.json.JSONObject;
import servlets.ErrorCodes;
import servlets.ErrorHandler;
import servlets.RequestHeaders;
import system.Data;
import system.acl.ACL;
import system.acl.AccessToken;
import system.configurations.Configuration;
import system.fields.HasCredential;
import utils.Commons;
import utils.RestApiFormat;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Data currentUser = null;
        ACL acl = Configuration.getInstance().getAcl();
        String token = ((HttpServletRequest) request).getHeader(RequestHeaders.TOKEN);
        if (token == null) {
            if (isLoginPath((HttpServletRequest) request)) {
                new LoginServlet().doPost((HttpServletRequest) request, (HttpServletResponse) response);
            } else if (isRegisterPath((HttpServletRequest) request)) {
                new RegisterServlet().doPost((HttpServletRequest) request, (HttpServletResponse) response);
            } else {
                ErrorHandler.handle(request, response, ErrorCodes.UNAUTHORIZED_ERROR, "Please login to get a valid token");
            }
        } else {
            AccessToken accessToken = new AccessToken(token);
            boolean tokenValid;
            try {
                tokenValid = accessToken.isValid();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                tokenValid = false;
            }


            if (tokenValid) {
                currentUser = acl.retrieveCurrentUserData(accessToken);
                request.setAttribute(RequestHeaders.CURRENT_USER, currentUser);
            } else {
                ErrorHandler.handle(request,response, ErrorCodes.UNAUTHORIZED_ERROR, "Invalid token");
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isLoginPath(HttpServletRequest servletRequest) {
        String path = servletRequest.getRequestURI();
        return path.equals("/api/login");
    }

    private boolean isRegisterPath(HttpServletRequest servletRequest) {
        String path = servletRequest.getRequestURI();
        return path.equals("/api/register");
    }
}
