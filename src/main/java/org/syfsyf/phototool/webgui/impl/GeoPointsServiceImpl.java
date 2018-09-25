package org.syfsyf.phototool.webgui.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.Execution;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.GeoPoint;
import org.syfsyf.phototool.cfg.GeoPoints;
import org.syfsyf.phototool.webgui.GeoPointsService;
import spark.ResponseTransformer;

import java.io.FileNotFoundException;
import java.io.Writer;
import java.net.InetAddress;

import static spark.Spark.*;

public class GeoPointsServiceImpl implements GeoPointsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoPointsServiceImpl.class);

    private ConfigService configService;

    private Config config;

    private GeoPoints geoPoints;

    private static class JsonTransformer implements ResponseTransformer {

        private XStream xstream;

        public JsonTransformer() {
            this.xstream = new XStream(new JsonHierarchicalStreamDriver() {
                public HierarchicalStreamWriter createWriter(Writer writer) {
                    return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
                }
            });
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.processAnnotations(new Class[]{GeoPoints.class, GeoPoint.class});
        }

        @Override
        public String render(Object model) throws Exception {

            return xstream.toXML(model);
        }

    }

    private static final JsonTransformer JSON_TRANSFORMER = new JsonTransformer();

    public GeoPointsServiceImpl(ConfigService configService) throws FileNotFoundException {

        this.configService = configService;

        config = configService.loadConfig();

        staticFiles.location("/public");

        // init();

        before((req, res) -> {
            InetAddress address = InetAddress.getByName(req.ip());
            if (!address.isLoopbackAddress()) {
                halt(401, "You are not welcome here");
            }
        });

		/*
         * get("/geoPointsService", (req, res) -> { // res.header(header,
		 * value); InetAddress address=InetAddress.getByName(req.ip());
		 * LOGGER.info("ip: " + req.ip());
		 * 
		 * if(address.isLoopbackAddress()){ LOGGER.info("local"); }
		 * 
		 * 
		 * InputStream select =
		 * getClass().getClassLoader().getResourceAsStream("select.html");
		 * StringWriter writer = new StringWriter(); IOUtils.copy(select,
		 * writer, "UTF-8"); return writer.toString(); });
		 */

        get("/geoPointsService/list", (req, res) -> {
            LOGGER.info("/geoPointsService/list");
            res.type("application/json");
            return configService.loadGeoPoints();

        }, JSON_TRANSFORMER);

        post("/geoPointsService/save", (req, res) -> {
            // req.body();
            return "";
        });
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
