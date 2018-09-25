package org.syfsyf.phototool.webgui;

import org.slf4j.Logger;
import org.eclipse.jetty.http.MimeTypes;
import org.picocontainer.annotations.Inject;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.Helper;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.cfg.ConfigService;
import spark.Request;
import spark.Response;

import java.net.InetAddress;

import static spark.Spark.*;

public class WebServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServer.class);

    @Inject
    ConfigService configService;

    @Inject
    JsonService jsonService;
    @Inject
    Api apiProfile;

    @Inject
    WebApi webApi;

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
            get("/appInfo",webApi::getAppInfo,jsonService::toJson);

            after("/*", (req, res) -> {
                res.type(MimeTypes.Type.APPLICATION_JSON.asString());
            });

            exception(Exception.class, (Exception exc, Request req, Response res) -> {

                LOGGER.error("error", exc);
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
