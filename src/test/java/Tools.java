import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import datastore.DatastoreController;
import kinds.User;
import kinds.fields.HasUrl;
import kinds.fields.IsAdmin;
import org.json.JSONObject;
import org.junit.Test;
import system.fields.HasCredential;
import system.fields.HasId;
import system.fields.HasName;
import utils.Commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Tools {
    final static String API_PREFIX = "api";
    final static String API_VERSION = "0";
    final static String LOCALHOST = "http://localhost:8080";

    public static void main(String... args) throws IOException, NoSuchAlgorithmException {
        login("root", "pppppp");

    }
    public static void createEntity(String host, String kind, JSONObject data) throws IOException {
        String path = String.format("%s/%s/%s/%s", host, API_PREFIX, API_VERSION, kind);
        send("PUT", path, null, data.toString());
    }

    public static void updateEntity(String host, String kind, Long id, JSONObject updateData) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s", host, API_PREFIX, API_VERSION, kind, id);
        send("POST", path, null, updateData.toString());
    }

    public static void deleteEntity(String host, String kind, Long id) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s", host, API_PREFIX, API_VERSION, kind, id);
        send("DELETE", path, null, null);
    }

    public static String send(String method, String url, Map<String, String> headers, String rawPayload) throws IOException {
        URL path = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) path.openConnection();
        httpURLConnection.setRequestMethod(method);
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpURLConnection.setRequestProperty(key, headers.get(key));
            }
        }

        if (method.equals("GET") || method.equals("DELETE")) {

        } else {
            if (rawPayload != null) {
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(rawPayload.getBytes());
                os.flush();
                os.close();
            }
        }

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }

    public static void createSystemUser() throws NoSuchAlgorithmException {
        RemoteApiInstaller remoteApiInstaller = new RemoteApiInstaller();
        String username = "root";
        String password = "pppppp";
        String hash = Commons.byteToHex(Commons.hash(password));
        User user = new User();
        user.set(IsAdmin.is_system, true);
        user.set(HasCredential.username, user);
        user.set(HasCredential.password, hash);
        new DatastoreController().create("User", user.getJsonObject());
    }

    public static void register(String username, String password) throws IOException {
        String path = String.format("%s/%s/%s", LOCALHOST, API_PREFIX, "register");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        send("POST", path, null, jsonObject.toString());
    }

    public static JSONObject login(String username, String password) throws IOException {
        String path = String.format("%s/%s/%s", LOCALHOST, API_PREFIX, "login");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return new JSONObject(send("POST", path, null, jsonObject.toString()));
    }


}
