package org.syfsyf.phototool;

import java.io.File;

public class ResizeJob extends AbstractJob {

	private File inFile;
	private File outFile;
	private int size;
	private String converterExe;

	public ResizeJob(String converterExe,File inFile, File outFile, int size) {
		super();
		this.inFile = inFile;
		this.outFile = outFile;
		this.size = size;
		this.converterExe=converterExe;
	}

	@Override
	public void run() {

		outFile.getParentFile().mkdirs();
		try {
			String cmd = converterExe + " \"" + inFile.getAbsolutePath() + "\" -resize " + size + "x" + size + " \"" + outFile.getAbsolutePath() + "\"";
			Execution execution = runCmd(cmd);			
		} catch (Exception ex) {
			setError(ex.getMessage());
		}		
		setDone(true);
	}

}
