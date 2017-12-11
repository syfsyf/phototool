package org.syfsyf.phototool.webgui;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.MimeTypes;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.Helper;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.cfg.ConfigService;

import java.net.InetAddress;

import static spark.Spark.*;

public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class);

    @Inject
    ConfigService configService;

    @Inject
    JsonService jsonService;
    @Inject
    Api apiProfile;

    public void start() {
        staticFiles.location("/public");

        // webSocket("/echo", EchoWebSocket.class);

        before("/*", (req, res) -> {

            InetAddress address = InetAddress.getByName(req.ip());
            if (!address.isLoopbackAddress()) {
                halt(401, "You are not welcome here");
            }
            LOGGER.info("Session id:" + req.session().id());
        });


        path("/api", () -> {

            get("/loadJob", apiProfile::loadJob, jsonService::toJson);
            post("/runJob", apiProfile::runJob, jsonService::toJson);
            get("/processStatus", apiProfile::getProcessStatus, jsonService::toJson);

            after("/*", (req, res) -> {
                res.type(MimeTypes.Type.APPLICATION_JSON.asString());
            });

            exception(Exception.class, (exc, req, res) -> {

                LOGGER.error(exc);
                ErrorDto errorDto = new ErrorDto();
                res.status(500);
                errorDto.setMessage(exc.getMessage());
                errorDto.setDetails(Helper.stacktrace(exc));
                res.type(MimeTypes.Type.APPLICATION_JSON.asString());
                res.body(jsonService.toJson(errorDto));

            });
        });
    }

    public String getServerMainUrl() {

        return "http://localhost:" + port();
    }

}
