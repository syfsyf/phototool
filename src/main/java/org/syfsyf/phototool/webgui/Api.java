package org.syfsyf.phototool.webgui;

import spark.Request;
import spark.Response;

public interface Api {

	
	public Object load(Request request, Response response) throws Exception;
	public Object save(Request request, Response response) throws Exception;
	public Object loadJob(Request request, Response response) throws Exception;
	public Object runJob(Request request, Response response) throws Exception;
	
	
}
