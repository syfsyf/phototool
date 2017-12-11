package org.syfsyf.phototool.webgui.impl;

import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.webgui.ApiProfile;
import spark.Request;
import spark.Response;

public class ApiProfileImpl implements ApiProfile {

    @Inject
    ConfigService configService;

    @Override
    public Object load(Request request, Response response) throws Exception {
        return configService.loadProfile();
    }

    @Override
    public Object save(Request request, Response response) throws Exception {
        return null;
    }

}
