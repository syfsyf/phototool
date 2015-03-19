package org.syfsyf.phototool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataModel {

	private File cwd;
	private File outDir;
	private List<File> files = new ArrayList<File>();
	private List<Job> jobs = new ArrayList<Job>();
	private Profile profile;

	public File getCwd() {
		return cwd;
	}

	public void setCwd(File cwd) {
		this.cwd = cwd;
	}

	public File getOutDir() {
		return outDir;
	}

	public void setOutDir(File outDir) {
		this.outDir = outDir;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * toString method for DataModel
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		sb.append("Cwd :  " + this.cwd == null ? "" : this.cwd + ",");
		sb.append("Out Dir :  " + this.outDir == null ? "" : this.outDir + ",");
		sb.append("Files :  " + this.files == null ? "" : this.files.size() + ",");
				
		return "DataModel [" + sb.toString() + "]";
	}

}
