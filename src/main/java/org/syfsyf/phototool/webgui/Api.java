package org.syfsyf.phototool.webgui;

import spark.Request;
import spark.Response;

public interface Api {

	Object load(Request request, Response response) throws Exception;

	Object save(Request request, Response response) throws Exception;

	Object loadJob(Request request, Response response) throws Exception;

	Object runJob(Request request, Response response) throws Exception;

	Object getProcessStatus(Request request, Response response) throws Exception;
}
