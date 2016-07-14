package org.syfsyf.phototool;

import com.thoughtworks.xstream.annotations.XStreamAlias;

// TODO: Auto-generated Javadoc
/**
 * The Class Config.
 */
@XStreamAlias("config")
public class Config {

	
	/** The img magic composite. */
	private String imgMagicComposite = "d:\\progs\\ImageMagick-6.9.0-Q16\\composite.exe";
	
	/** The img magic convert. */
	private String imgMagicConvert = "d:\\progs\\ImageMagick-6.9.0-Q16\\convert.exe";

	/** The img magic identify. */
	private String imgMagicIdentify="d:\\progs\\ImageMagick-6.9.0-Q16\\identify.exe";
	
	/**
	 * Gets the img magic composite.
	 *
	 * @return the img magic composite
	 */
	public String getImgMagicComposite() {
		return imgMagicComposite;
	}
	
	/**
	 * Sets the img magic composite.
	 *
	 * @param imgMagicComposite the new img magic composite
	 */
	public void setImgMagicComposite(String imgMagicComposite) {
		this.imgMagicComposite = imgMagicComposite;
	}
	
	/**
	 * Gets the img magic convert.
	 *
	 * @return the img magic convert
	 */
	public String getImgMagicConvert() {
		return imgMagicConvert;
	}
	
	/**
	 * Sets the img magic convert.
	 *
	 * @param imgMagicConvert the new img magic convert
	 */
	public void setImgMagicConvert(String imgMagicConvert) {
		this.imgMagicConvert = imgMagicConvert;
	}
	
	/**
	 * Gets the img magic identify.
	 *
	 * @return the img magic identify
	 */
	public String getImgMagicIdentify() {
		return imgMagicIdentify;
	}
	
	/**
	 * Sets the img magic identify.
	 *
	 * @param imgMagicIdentify the new img magic identify
	 */
	public void setImgMagicIdentify(String imgMagicIdentify) {
		this.imgMagicIdentify = imgMagicIdentify;
	}
	
	
	
	

	
	
	

}
