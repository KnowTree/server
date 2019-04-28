package servlets;

import system.configurations.Configuration;
import org.json.JSONObject;
import system.Data;
import system.DataFactory;
import system.fields.HasCredential;
import system.fields.HasEmail;
import system.fields.HasId;
import system.fields.HasName;
import utils.RestApiFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public abstract class RestServlet extends BaseServlet {
    protected Configuration configuration;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat urlParser = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
       Data data = Configuration.getInstance().dataFactory().create(urlParser.getKind());
       data.set(HasId.id, urlParser.getId());
        try {
            data.retrieve(null);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }
        if (canGet((Data) req.getAttribute(RequestHeaders.CURRENT_USER), data, urlParser)) {
            resp.getWriter().write(data.toString());
        } else {
            ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ACTION, "Not allow to get");
        }

    }

    protected boolean canGet(Data currentUser, Data data, RestApiFormat urlParser) {
        return data.canGet(currentUser, data, urlParser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat urlParser = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
        String payloadRaw = urlParser.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        Data data = Configuration.getInstance().dataFactory().create(urlParser.getKind());
        data.set(HasId.id, urlParser.getId());
        Data currentUser = (Data) req.getAttribute(RequestHeaders.CURRENT_USER);
        try {
            data.retrieve(null);
            if (canUpdate(currentUser, data, jsonObject, urlParser)) {
                data.copyFromJSON(jsonObject);
                data.update();
                data.canGet(currentUser, data, urlParser);
                resp.getWriter().write(data.toString());
            } else {
                ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ACTION, "Not allow to update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }
    }

    protected boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat) {
        return currentData.canUpdate(currentUser, currentData, updateData, restApiFormat);

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat urlParser = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
        String payloadRaw = urlParser.getPayload();
        JSONObject jsonObject = new JSONObject(payloadRaw);
        Data data = Configuration.getInstance().dataFactory().create(urlParser.getKind());
        data.setJSONObject(jsonObject);
        if (canCreate((Data) req.getAttribute(RequestHeaders.CURRENT_USER), data, urlParser)) {
            try {
                data.create();
            } catch (Exception e) {
                e.printStackTrace();
                ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
            }
            resp.getWriter().write(data.toString());
        } else {
            ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ACTION, "Not allow to create");
        }
    }

    protected boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser) {
        return data.canCreate(currentUser, data, urlParser);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestApiFormat urlParser = (RestApiFormat) req.getAttribute(RequestHeaders.REQUEST_URL_DATA);
        Data data = Configuration.getInstance().dataFactory().create(urlParser.getKind());
        data.set(HasId.id, urlParser.getId());

        try {
            data.retrieve(null);
            if (canDelete((Data) req.getAttribute(RequestHeaders.CURRENT_USER), data, urlParser)) {
                data.delete();
                resp.getWriter().write(data.toString());
            } else {
                ErrorHandler.handle(req, resp, ErrorCodes.UNAUTHORIZED_ACTION, "Not allow to delete");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandler.handle(req, resp, ErrorHandler.ERROR, e.getMessage());
        }

    }

    protected boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser) {
        return data.canDelete(currentUser, data, urlParser);
    }

}
