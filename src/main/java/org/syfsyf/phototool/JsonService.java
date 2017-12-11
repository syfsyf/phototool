package org.syfsyf.phototool;

public interface JsonService {

    String toJson(Object object);

    <T> T fromJson(String json, Class<T> clazz);

}