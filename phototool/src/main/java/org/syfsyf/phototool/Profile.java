package org.syfsyf.phototool;

import java.awt.Color;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("profile")
public class Profile {

	private boolean autolevel = false;
	private boolean border = false;
	private Color borderColor = Color.black;
	private int borderSize = 20;

	private String outDirName = "__resize_";
	private boolean resize = true;
	private int resizeWidth = 1300;

	private boolean addSignature=true;
	private String sigFile;
	private String sigGravity = "SouthWest";
	private String sigGeometry = "+20+20";
	private String sigResize = "x10";
	private String customParams="-quality  100%";

	public Color getBorderColor() {
		return borderColor;
	}

	public int getBorderSize() {
		return borderSize;
	}

	public String getOutDirName() {
		return outDirName;
	}

	public int getResizeWidth() {
		return resizeWidth;
	}

	public boolean isAutolevel() {
		return autolevel;
	}

	public boolean isBorder() {
		return border;
	}

	public boolean isResize() {
		return resize;
	}

	public void setAutolevel(boolean autolevel) {
		this.autolevel = autolevel;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public void setOutDirName(String outDirName) {
		this.outDirName = outDirName;
	}

	public void setResize(boolean resize) {
		this.resize = resize;
	}

	public void setResizeWidth(int resizeWidth) {
		this.resizeWidth = resizeWidth;
	}

	public boolean isAddSignature() {
		return addSignature;
	}

	public void setAddSignature(boolean addSignature) {
		this.addSignature = addSignature;
	}

	public String getSigFile() {
		return sigFile;
	}

	public void setSigFile(String signatureFile) {
		this.sigFile = signatureFile;
	}

	public String getSigGravity() {
		return sigGravity;
	}

	public void setSigGravity(String gravity) {
		this.sigGravity = gravity;
	}

	public String getSigGeometry() {
		return sigGeometry;
	}

	public void setSigGeometry(String sigGeometry) {
		this.sigGeometry = sigGeometry;
	}

	public String getSigResize() {
		return sigResize;
	}

	public void setSigResize(String sigResize) {
		this.sigResize = sigResize;
	}

	public String getCustomParams() {
		return customParams;
	}

	public void setCustomParams(String customParams) {
		this.customParams = customParams;
	}
	
	
	
}
