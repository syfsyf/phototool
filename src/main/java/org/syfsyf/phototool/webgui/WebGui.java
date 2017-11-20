package org.syfsyf.phototool.webgui;

import static spark.Spark.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebGui {

	public static void main(String[] args) throws Exception {
		get("/hello", (req, res) -> {
			// res.header(header, value);
			return readFile("select.html", "UTF-8");
		});

	}

	static String readFile(String path, String string)  {
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded, string);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
