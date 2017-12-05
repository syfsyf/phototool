package org.syfsyf.phototool.webgui;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.webSocket;

import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.MimeTypes;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.Helper;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.cfg.ConfigService;

public class WebServer {
	
	private static final Logger LOGGER = Logger.getLogger(WebServer.class);
	
	@Inject
	ConfigService configService;
	
	@Inject JsonService jsonService;
	@Inject Api apiProfile;
	

	public void start() {
		staticFiles.location("/public");
		
		webSocket("/echo", EchoWebSocket.class);
			
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
				get("/processStatus",apiProfile::getProcessStatus,jsonService::toJson);
				//post("/save",apiProfile::save,jsonService::toJson);
			//});
				
			
			
			after("/*",(req,res)->{
				res.type(MimeTypes.Type.APPLICATION_JSON.asString());
			});
			
			exception(Exception.class,(exc,req,res)->{		
				
				LOGGER.error(exc);
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
