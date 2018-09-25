package org.syfsyf.phototool.webgui;

import spark.Request;
import spark.Response;

import java.util.Map;

public interface WebApi {

    Map<String, String> getAppInfo(Request request, Response response) throws Exception;

}
