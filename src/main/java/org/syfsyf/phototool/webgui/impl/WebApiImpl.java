package org.syfsyf.phototool.webgui.impl;

import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.webgui.WebApi;
import spark.Request;
import spark.Response;

import java.util.Map;

/**
 * Created by mb on 2018-09-25.
 */
public class WebApiImpl implements WebApi{
    @Inject
    ConfigService configService;
    @Override
    public Map<String, String> getAppInfo(Request request, Response response) throws Exception {
        return configService.getGitInfo();
    }
}
