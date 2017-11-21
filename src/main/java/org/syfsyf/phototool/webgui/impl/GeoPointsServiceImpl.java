package org.syfsyf.phototool.webgui.impl;

import static spark.Spark.get;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.Execution;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.GeoPoint;
import org.syfsyf.phototool.cfg.GeoPoints;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.webgui.GeoPointsService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import spark.ResponseTransformer;

public class GeoPointsServiceImpl implements GeoPointsService {

	
	private ConfigService configService;

	private Config config;

	private GeoPoints geoPoints;

	private static class JsonTransformer implements ResponseTransformer {

		private XStream xstream;

		public JsonTransformer() {
			this.xstream = new XStream(new JsonHierarchicalStreamDriver(){
			    public HierarchicalStreamWriter createWriter(Writer writer) {
			        return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
			    }
			});
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.processAnnotations(new Class[] { GeoPoints.class,GeoPoint.class });
		}

		@Override
		public String render(Object model) throws Exception {

			return xstream.toXML(model);
		}

	}

	private static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();

	public GeoPointsServiceImpl(ConfigService configService) throws FileNotFoundException {
		
		this.configService=configService;
		
		config=configService.loadConfig();
		
		get("/geoPointsService", (req, res) -> {
			// res.header(header, value);
			InputStream select = getClass().getClassLoader().getResourceAsStream("select.html");
			StringWriter writer=new StringWriter();
			IOUtils.copy(select, writer,"UTF-8");						
			return writer.toString();
		});
		
		get("/geoPointsService/list",(req,res)->{
			res.type("application/json");			
			return geoPoints.getGeoPoints(); 
					
		},JSON_TRANSFORMER);
	}

	@Override
	public void manage(GeoPoints geoPoints) {

		this.geoPoints = geoPoints;
		try {
			String cmd = config.getChromeExe() + " --app=http://localhost:4567/geoPointsService";
			Execution exe = new Execution(cmd);
			exe.run();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
