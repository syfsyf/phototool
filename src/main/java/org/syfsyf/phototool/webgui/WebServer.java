package org.syfsyf.phototool.webgui;

import static spark.Spark.*;

import java.io.FileNotFoundException;

import org.picocontainer.annotations.Inject;
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
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		
		path("/api",()->{
						
			//path("/profile",()->{				
				//get("/load",apiProfile::load,jsonService::toJson);
				get("/loadJob",apiProfile::loadJob,jsonService::toJson);
				post("/runJob",apiProfile::runJob,jsonService::toJson);
				//post("/save",apiProfile::save,jsonService::toJson);
			//});
			
			after("/*",(req,res)->{
				res.type("application/json");
			});
		});
	}
	
	
	
	
	

}
