package system;

import system.property.Property;
import org.json.JSONObject;
import utils.SearchData;

import java.util.List;

public interface DatabaseController {
    JSONObject create(String kind, JSONObject data) throws Exception;

    JSONObject update(String kind, Long id, JSONObject updateData) throws Exception;

    boolean delete(String kind, Long id) throws Exception;

    JSONObject get(String kind, Long id, List<Property> properties) throws Exception;

    JSONObject get(String key, List<Property> props) throws Exception;

    /**
     * @param searchApiData search by labels field , use all equal filters
     *
     * @return
     */
    List<Data> search(final SearchData searchApiData);
}
