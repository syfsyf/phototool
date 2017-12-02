package org.syfsyf.phototool.webgui;

import static spark.Spark.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;

import org.eclipse.jetty.http.MimeTypes;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.Helper;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.cfg.ConfigService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.Request;
import spark.Response;
import spark.Route;

public class WebServer {
	
	@Inject
	ConfigService configService;
	
	@Inject JsonService jsonService;
	@Inject Api apiProfile;
	

	public void start() {
		staticFiles.location("/public");
			
		before((req,res)->{
			InetAddress address=InetAddress.getByName(req.ip());
			if(!address.isLoopbackAddress()){
				halt(401,"You are not welcome here");
			}
		});
		
		path("/api",()->{
						
			//path("/profile",()->{				
				//get("/load",apiProfile::load,jsonService::toJson);
				get("/loadJob",apiProfile::loadJob,jsonService::toJson);
				post("/runJob",apiProfile::runJob,jsonService::toJson);
				//post("/save",apiProfile::save,jsonService::toJson);
			//});
				
			
			
			after("/*",(req,res)->{
				res.type(MimeTypes.Type.APPLICATION_JSON.asString());
			});
			
			exception(Exception.class,(exc,req,res)->{				
				ErrorDto errorDto=new ErrorDto();				
				res.status(500);
				errorDto.setMessage(exc.getMessage());												
				errorDto.setDetails(Helper.stacktrace(exc));
				res.type(MimeTypes.Type.APPLICATION_JSON.asString());
				res.body(jsonService.toJson(errorDto));
								
			});
			
			
			
			
		});
	}
	
	
	
	
	

}
