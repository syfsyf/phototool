package org.syfsyf.phototool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
