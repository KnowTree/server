package utils;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Query;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import system.configurations.Configuration;
import system.fields.FieldMap;
import system.property.Property;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class SearchData {
    String kind;
    int length = 10, start = 0;
    String orderByField;
    boolean asc;
    String cursor;
    List<QueryData> queryDataList;
    List<Property> selectFields;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public void setQueryDataList(List<QueryData> queryDataList) {
        this.queryDataList = queryDataList;
    }

    public List<Property> getSelectFields() {
        return selectFields;
    }

    public void setSelectFields(List<Property> selectFields) {
        this.selectFields = selectFields;
    }

    public SearchData(HttpServletRequest request) throws IOException {
        String rawPayload = IOUtils.toString(request.getReader());
        JSONArray json = new JSONArray(rawPayload);
        kind = request.getParameter("kind");
        length = request.getParameter("l") != null ? Integer.valueOf(request.getParameter("l")) : 10;
        start = request.getParameter("s") != null ? Integer.valueOf(request.getParameter("s")) : 0;
        String order = request.getParameter("o");
        if (order != null) {
            asc = !order.startsWith("-");
            if (asc) {
                orderByField = order;
            } else {
                orderByField = order.substring(1);
            }
        }
        cursor = request.getParameter("cursor");
        queryDataList = parseQueryData(json);

        String selectFieldsString = request.getParameter("fields");
        if (selectFieldsString != null) {
            String[] fieldNames = selectFieldsString.split(",");
            for (String fieldname : fieldNames) {
                selectFields.add(Configuration.getInstance().fieldMap().get(fieldname));
            }
        }

    }

    public SearchData() {

    }

    private List<QueryData> parseQueryData(JSONArray array) {
        List<QueryData> result = new ArrayList<>();
        Iterator<Object> ite = array.iterator();
        while (ite.hasNext()) {
            JSONObject object = (JSONObject) ite.next();
            String field = object.getString("field");
            //TODO : TypeSystem to extract correct value for field
            String operator = object.getString("ops");
            Object value = object.get("value");
            result.add(new QueryData(field, operator, value));
        }
        return result;
    }

    public SearchData setCursor(String cursor) {
        this.cursor = cursor;
        return this;
    }

    public String getCursor() {
        return this.cursor;
    }

    public List<QueryData> getQueryDataList() {
        return this.queryDataList;
    }

    public String getKind() {
        return kind;
    }

}
