package org.syfsyf.phototool;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("config")
public class Config {

	
	private String imgMagicComposite = "d:\\progs\\ImageMagick-6.9.0-Q16\\composite.exe";
	private String imgMagicConvert = "d:\\progs\\ImageMagick-6.9.0-Q16\\convert.exe";

	private String imgMagicIdentify="d:\\progs\\ImageMagick-6.9.0-Q16\\identify.exe";
	
	public String getImgMagicComposite() {
		return imgMagicComposite;
	}
	public void setImgMagicComposite(String imgMagicComposite) {
		this.imgMagicComposite = imgMagicComposite;
	}
	public String getImgMagicConvert() {
		return imgMagicConvert;
	}
	public void setImgMagicConvert(String imgMagicConvert) {
		this.imgMagicConvert = imgMagicConvert;
	}
	public String getImgMagicIdentify() {
		return imgMagicIdentify;
	}
	public void setImgMagicIdentify(String imgMagicIdentify) {
		this.imgMagicIdentify = imgMagicIdentify;
	}
	
	
	
	

	
	
	

}
