import org.json.JSONObject;
import org.junit.Test;
import system.fields.HasId;
import system.fields.HasName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Tools {
    final static String API_PREFIX = "api";
    final static String API_VERSION = "0";
    final static String LOCALHOST = "http://localhost:8080";

    public static void main(String... args) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(HasName.first_name.key(), "Test B");
        jsonObject.put(HasName.last_name.key(), "Rich");
        //createEntity(LOCALHOST, "user", jsonObject);
        updateEntity(LOCALHOST, "user", 3L, jsonObject);
        //deleteEntity(LOCALHOST, "user", 3L);
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

    public static void send(String method, String url, Map<String, String> headers, String rawPayload) throws IOException {
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
    }
}
