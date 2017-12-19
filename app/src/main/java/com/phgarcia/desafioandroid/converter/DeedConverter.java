package com.phgarcia.desafioandroid.converter;

import com.phgarcia.desafioandroid.model.Deed;

import org.json.JSONException;
import org.json.JSONStringer;

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

}
