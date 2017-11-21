package org.syfsyf.phototool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.Profile;

// TODO: Auto-generated Javadoc
/**
 * The Class DataModel.
 */
public class DataModel {

	/** The cwd. */
	private File cwd;	
	
	/** The files. */
	private List<File> files = new ArrayList<File>();
	
	/** The jobs. */
	private List<Job> jobs = new ArrayList<Job>();
	
	/** The profile. */
	private Profile profile;
	
	/** The config. */
	private Config config;

	/**
	 * Gets the cwd.
	 *
	 * @return the cwd
	 */
	public File getCwd() {
		return cwd;
	}

	/**
	 * Sets the cwd.
	 *
	 * @param cwd the new cwd
	 */
	public void setCwd(File cwd) {
		this.cwd = cwd;
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}

	/**
	 * Gets the jobs.
	 *
	 * @return the jobs
	 */
	public List<Job> getJobs() {
		return jobs;
	}

	/**
	 * Sets the jobs.
	 *
	 * @param jobs the new jobs
	 */
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	/**
	 * Gets the profile.
	 *
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Sets the profile.
	 *
	 * @param profile the new profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Sets the config.
	 *
	 * @param config the new config
	 */
	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * toString method for DataModel.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		sb.append("Cwd :  " + this.cwd == null ? "" : this.cwd + ",");		
		sb.append("Files :  " + this.files == null ? "" : this.files.size() + ",");
				
		return "DataModel [" + sb.toString() + "]";
	}
	
}
