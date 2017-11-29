package org.syfsyf.phototool.webgui.impl;

import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.webgui.Api;

import spark.Request;
import spark.Response;

public class ApiImpl implements Api{

	@Inject
	ConfigService configService;
	
	@Override
	public Object load(Request request, Response response) throws Exception {
		return configService.loadProfile();
	}

	@Override
	public Object save(Request request, Response response) throws Exception {
		
		// request.body()
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object loadJob(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object runJob(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
