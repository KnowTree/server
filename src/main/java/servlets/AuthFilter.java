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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Data currentUser = null;

        String token = (String) request.getAttribute(RequestHeaders.TOKEN);
        if (token == null) {
            request.setAttribute(RequestHeaders.CURRENT_USER, null);
            if (isLoginPath((HttpServletRequest) request)) {
                String username = request.getParameter(HasCredential.username.key());
                String password = request.getParameter(HasCredential.password.key());
                if (username != null && password != null) {
                } else {
                    ErrorHandler.handle(request, response, ErrorCodes.MISSING_PARAM, "Invalid username or password");
                }
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

            ACL acl = Configuration.getInstance().getAcl();

            if (tokenValid) {
                currentUser = acl.retrieveCurrentUserData(accessToken);
                request.setAttribute(RequestHeaders.CURRENT_USER, currentUser);
            } else {
                ErrorHandler.handle(request,response, ErrorCodes.UNAUTHORIZED_ERROR, "Invalid token");
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean isLoginPath(HttpServletRequest servletRequest) {
        String path = servletRequest.getRequestURI();
        return path.equals("/api/login");
    }

    private User getLoginUser(String username, String password) {
        return null;
    }
}
