package org.syfsyf.phototool.webgui.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.utils.JsonService;
import org.syfsyf.phototool.impl.PhotoolFacadeImpl;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.GeoPoints;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.JobsStatusDto;
import org.syfsyf.phototool.webgui.Api;
import org.syfsyf.phototool.domain.JobSetupDto;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.File;
import java.io.FileNotFoundException;

public class ApiImpl implements Api {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiImpl.class);

    private static final String DATA_MODEL = "dataModel";
    private static final String VIEW_MODEL = "viewModel";

    @Inject
    ConfigService configService;

    @Inject
    PhotoolFacadeImpl photoolFacade;

    @Inject
    JsonService jsonService;

    @Override
    public Object load(Request request, Response response) throws Exception {
        return configService.loadProfile();
    }

    @Override
    public Object save(Request request, Response response) throws Exception {
        return null;
    }

    @Override
    public Object loadJob(Request request, Response response) throws Exception {

        DataModel dataModel = getDataModel(request);

        JobSetupDto dto = new JobSetupDto();

        dto.setDirectory(dataModel.getCwd().getAbsolutePath());
        dto.setNumberOfFiles(dataModel.getFiles().size());
        GeoPoints geoPoints = configService.loadGeoPoints();
        dto.setGeoPoints(geoPoints.getGeoPoints());

        BeanUtils.copyProperties(dto, dataModel.getProfile());

        return dto;
    }

    protected DataModel getDataModel(Request request) throws FileNotFoundException {

        Session session = request.session();
        DataModel dataModel = session.attribute(DATA_MODEL);
        if (dataModel != null) {
            return dataModel;
        }

        dataModel = photoolFacade.createDataModel();
        session.attribute(DATA_MODEL, dataModel);
        return dataModel;
    }

    protected JobsStatusDto getViewModel(Request request) throws FileNotFoundException {

        Session session = request.session();
        JobsStatusDto viewModel = session.attribute(VIEW_MODEL);
        if (viewModel != null) {
            return viewModel;
        }
        viewModel = new JobsStatusDto();
        viewModel.setProgressLabel("utworzone w sessji");
        session.attribute(VIEW_MODEL, viewModel);
        return viewModel;
    }

    @Override
    public Object runJob(Request request, Response response) throws Exception {

        JobSetupDto jobDto = jsonService.fromJson(request.body(), JobSetupDto.class);

        DataModel dataModel = photoolFacade.createDataModel(new File(jobDto.getDirectory()));
        request.attribute(DATA_MODEL, dataModel);

        Profile profile = new Profile();
        BeanUtils.copyProperties(profile, jobDto);

        configService.saveProfile(profile);

        dataModel.setProfile(profile);

        JobsStatusDto viewModel = getViewModel(request);

        LOGGER.info("runJob");

        photoolFacade.process(dataModel, viewModel);

        return null;
    }

    @Override
    public Object getProcessStatus(Request request, Response response) throws Exception {
        return getViewModel(request);

    }

}
