package org.syfsyf.phototool.cfg.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.Profile;

import com.thoughtworks.xstream.XStream;

public class ConfigServiceImpl implements ConfigService{
	
			
	private XStream xstream;
	
	
	public ConfigServiceImpl() {
		xstream=createXStream();
	}

	protected XStream createXStream(){
						
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(new Class[] { Profile.class,Config.class });
		return xstream;
	}
	public XStream getXstream() {
		return xstream;
	}

	@Override
	public Config loadConfig() throws FileNotFoundException {
		Config config = null;
		File file = getConfigFile();
		if (!file.exists()) {
			config = new Config();
			saveConfig(config);
		} else {
			config = (Config) getXstream().fromXML(file);
		}
		return config;
	}

	@Override
	public void saveConfig(Config config) throws FileNotFoundException {
		File file = getConfigFile();
		file.getParentFile().mkdirs();
		getXstream().toXML(config, new FileOutputStream(getConfigFile()));
		
	}

	@Override
	public Profile loadProfile() throws FileNotFoundException {
		Profile profile = null;
		File file = getProfileFile();
		if (!file.exists()) {
			profile = new Profile();
			saveProfile(profile);
		} else {
			profile = (Profile) getXstream().fromXML(file);
		}
		return profile;
	}

	@Override
	public void saveProfile(Profile profile) throws FileNotFoundException {
		File file = getProfileFile();
		file.getParentFile().mkdirs();
		getXstream().toXML(profile, new FileOutputStream(getProfileFile()));
		
	}
	
	/**
	 * Gets the profile file.
	 *
	 * @return the profile file
	 */
	private File getProfileFile() {
		return new File(System.getProperty("user.home") + "/.phototool/profile.xml");
	}
	
	/**
	 * Gets the config file.
	 *
	 * @return the config file
	 */
	private File getConfigFile() {
		return new File(System.getProperty("user.home") + "/.phototool/config.xml");
	}

}
