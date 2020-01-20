package org.syfsyf.phototool.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.syfsyf.phototool.utils.JsonService;

public class JsonServiceImpl implements JsonService {

    private Gson gson;

    public JsonServiceImpl() {
        gson = new GsonBuilder()
                // .serializeNulls()
                .setPrettyPrinting().create();
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}
