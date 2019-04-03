package system;

import org.json.JSONObject;

public interface DatabaseController {
    JSONObject create(JSONObject data);

    JSONObject update(Long id, JSONObject updateData);

    boolean delete(Long id);

    JSONObject get(Long id);

    JSONObject get(String key);
}
