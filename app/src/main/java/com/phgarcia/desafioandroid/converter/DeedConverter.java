package com.phgarcia.desafioandroid.converter;

import com.phgarcia.desafioandroid.model.Deed;
import com.phgarcia.desafioandroid.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garci on 12/19/2017.
 */

public class DeedConverter {

    public String convertToJSON(List<Deed> deeds) throws JSONException {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("list").array().object().key("deed").array();

            for (Deed deed : deeds) {
                js.object();
                js.key("id").value(deed.getId());
                js.key("name").value(deed.getName());
                js.key("imageURL").value(deed.getImageURL());
                js.key("description").value(deed.getDescription());
                js.endObject();
            }

            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }

    public List<Deed> getFromJSON(String json) {
        List<Deed> deedsList = new ArrayList<Deed>();

        try {
            JSONObject jo = new JSONObject(json);
            JSONArray deedArray = jo.getJSONArray(Constants.FETCH_MAIN_ARRAY_NAME);

            for (int i = 0; i < deedArray.length(); i++) {
                JSONObject deedJSONObject = deedArray.getJSONObject(i);

                Deed deed = new Deed();
                deed.setId(deedJSONObject.getLong("id"));
                deed.setName(deedJSONObject.getString("name"));
                deed.setImageURL(deedJSONObject.getString("image"));
                deed.setDescription(deedJSONObject.getString("description"));
                deed.setSite(deedJSONObject.getString("site"));

                deedsList.add(deed);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return deedsList;
    }

}
