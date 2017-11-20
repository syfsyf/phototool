package org.syfsyf.phototool;

import java.awt.Color;

import com.thoughtworks.xstream.annotations.XStreamAlias;

// TODO: Auto-generated Javadoc
/**
 * The Class Profile.
 */
@XStreamAlias("profile")
public class Profile {

	/** The autolevel. */
	private boolean autolevel = false;
	
	/** The border. */
	private boolean border = false;
	
	/** The border color. */
	private Color borderColor = Color.black;
	
	/** The border size. */
	private int borderSize = 20;

	/** The out dir name. */
	private String outDirName = "__resize_";
	
	/** The resize. */
	private boolean resize = true;
	
	/** The resize width. */
	private int resizeWidth = 1300;

	/** The add signature. */
	private boolean addSignature=true;
	
	/** The sig file. */
	private String sigFile;
	
	/** The sig gravity. */
	private String sigGravity = "SouthWest";
	
	/** The sig geometry. */
	private String sigGeometry = "+20+20";
	
	/** The sig resize. */
	private String sigResize = "x10";
	
	/** The custom params. */
	private String customParams="-quality  100%";

	/**
	 * Gets the border color.
	 *
	 * @return the border color
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Gets the border size.
	 *
	 * @return the border size
	 */
	public int getBorderSize() {
		return borderSize;
	}

	/**
	 * Gets the out dir name.
	 *
	 * @return the out dir name
	 */
	public String getOutDirName() {
		return outDirName;
	}

	/**
	 * Gets the resize width.
	 *
	 * @return the resize width
	 */
	public int getResizeWidth() {
		return resizeWidth;
	}

	/**
	 * Checks if is autolevel.
	 *
	 * @return true, if is autolevel
	 */
	public boolean isAutolevel() {
		return autolevel;
	}

	/**
	 * Checks if is border.
	 *
	 * @return true, if is border
	 */
	public boolean isBorder() {
		return border;
	}

	/**
	 * Checks if is resize.
	 *
	 * @return true, if is resize
	 */
	public boolean isResize() {
		return resize;
	}

	/**
	 * Sets the autolevel.
	 *
	 * @param autolevel the new autolevel
	 */
	public void setAutolevel(boolean autolevel) {
		this.autolevel = autolevel;
	}

	/**
	 * Sets the border.
	 *
	 * @param border the new border
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/**
	 * Sets the border color.
	 *
	 * @param borderColor the new border color
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * Sets the border size.
	 *
	 * @param borderSize the new border size
	 */
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	/**
	 * Sets the out dir name.
	 *
	 * @param outDirName the new out dir name
	 */
	public void setOutDirName(String outDirName) {
		this.outDirName = outDirName;
	}

	/**
	 * Sets the resize.
	 *
	 * @param resize the new resize
	 */
	public void setResize(boolean resize) {
		this.resize = resize;
	}

	/**
	 * Sets the resize width.
	 *
	 * @param resizeWidth the new resize width
	 */
	public void setResizeWidth(int resizeWidth) {
		this.resizeWidth = resizeWidth;
	}

	/**
	 * Checks if is adds the signature.
	 *
	 * @return true, if is adds the signature
	 */
	public boolean isAddSignature() {
		return addSignature;
	}

	/**
	 * Sets the adds the signature.
	 *
	 * @param addSignature the new adds the signature
	 */
	public void setAddSignature(boolean addSignature) {
		this.addSignature = addSignature;
	}

	/**
	 * Gets the sig file.
	 *
	 * @return the sig file
	 */
	public String getSigFile() {
		return sigFile;
	}

	/**
	 * Sets the sig file.
	 *
	 * @param signatureFile the new sig file
	 */
	public void setSigFile(String signatureFile) {
		this.sigFile = signatureFile;
	}

	/**
	 * Gets the sig gravity.
	 *
	 * @return the sig gravity
	 */
	public String getSigGravity() {
		return sigGravity;
	}

	/**
	 * Sets the sig gravity.
	 *
	 * @param gravity the new sig gravity
	 */
	public void setSigGravity(String gravity) {
		this.sigGravity = gravity;
	}

	/**
	 * Gets the sig geometry.
	 *
	 * @return the sig geometry
	 */
	public String getSigGeometry() {
		return sigGeometry;
	}

	/**
	 * Sets the sig geometry.
	 *
	 * @param sigGeometry the new sig geometry
	 */
	public void setSigGeometry(String sigGeometry) {
		this.sigGeometry = sigGeometry;
	}

	/**
	 * Gets the sig resize.
	 *
	 * @return the sig resize
	 */
	public String getSigResize() {
		return sigResize;
	}

	/**
	 * Sets the sig resize.
	 *
	 * @param sigResize the new sig resize
	 */
	public void setSigResize(String sigResize) {
		this.sigResize = sigResize;
	}

	/**
	 * Gets the custom params.
	 *
	 * @return the custom params
	 */
	public String getCustomParams() {
		return customParams;
	}

	/**
	 * Sets the custom params.
	 *
	 * @param customParams the new custom params
	 */
	public void setCustomParams(String customParams) {
		this.customParams = customParams;
	}
	
	
	
}