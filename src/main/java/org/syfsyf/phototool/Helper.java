package org.syfsyf.phototool;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Helper {

	private Helper() {

	}

	public static String stacktrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

}
