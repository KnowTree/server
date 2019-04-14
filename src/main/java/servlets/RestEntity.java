package servlets;

import org.json.JSONObject;
import system.Data;
import utils.RestApiFormat;

public interface RestEntity {

    boolean canGet(Data currentUser, Data data, RestApiFormat urlParser);

    boolean canUpdate(Data currentUser, Data currentData, JSONObject updateData, RestApiFormat restApiFormat);

    boolean canCreate(Data currentUser, Data data, RestApiFormat urlParser);

    boolean canDelete(Data currentUser, Data data, RestApiFormat urlParser);

}
