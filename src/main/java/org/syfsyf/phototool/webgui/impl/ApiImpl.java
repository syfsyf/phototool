package org.syfsyf.phototool.webgui.impl;

import java.io.FileNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.JsonService;
import org.syfsyf.phototool.PhotoolFacade;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.webgui.Api;
import org.syfsyf.phototool.webgui.JobDto;

import spark.Request;
import spark.Response;
import spark.Session;

public class ApiImpl implements Api{

	private static final Logger LOGGER = Logger.getLogger(ApiImpl.class);
	
	private static final String DATA_MODEL="dataModel";
	
	@Inject
	ConfigService configService;
	
	@Inject
	PhotoolFacade photoolFacade;
	
	@Inject 
	JsonService jsonService;
	
	@Override
	public Object load(Request request, Response response) throws Exception {
		return configService.loadProfile();
	}

	@Override
	public Object save(Request request, Response response) throws Exception {
		
		// request.body()
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object loadJob(Request request, Response response) throws Exception {
		
		DataModel dataModel = getDataModel(request);
			
		JobDto dto=new JobDto();
		
		dto.setDirectory(dataModel.getCwd().getAbsolutePath());
		dto.setNumberOfFiles(dataModel.getFiles().size());
					 		
		BeanUtils.copyProperties(dto, dataModel.getProfile());
		
		return dto;
	}
	
	protected DataModel getDataModel(Request request) throws FileNotFoundException{
		
		Session session = request.session();
		DataModel dataModel=session.attribute(DATA_MODEL);
		if(dataModel!=null){
			return dataModel;
		}
		
		dataModel = photoolFacade.createDataModel();
		session.attribute(DATA_MODEL, dataModel);
		return dataModel;		
	}

	@Override
	public Object runJob(Request request, Response response) throws Exception {
		
		JobDto jobDto = jsonService.fromJson(request.body(), JobDto.class);
		DataModel dataModel = getDataModel(request);
		
		Profile profile=new Profile();
		BeanUtils.copyProperties(profile, jobDto);
		
		dataModel.setProfile(profile);
		
		if("x".equals(dataModel.getProfile().getOutDirName())){
			throw new Exception("bum");
		}
		
		LOGGER.info("runJob");
		
		return null; 
	}
	

}
