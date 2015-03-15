package org.syfsyf.phototool;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("config")
public class Profile {

	private boolean resize=true;
	private int resizeWidth=1300;
	private boolean border=false;
	private int borderSize;
	private boolean autolevel=false;
	private String outDirName="__resize_";	
	private String imgMagicConvert="d:\\progs\\ImageMagick-6.9.0-Q16\\convert.exe";
	public boolean isResize() {
		return resize;
	}
	public void setResize(boolean resize) {
		this.resize = resize;
	}
	public int getResizeWidth() {
		return resizeWidth;
	}
	public void setResizeWidth(int resizeWidth) {
		this.resizeWidth = resizeWidth;
	}
	
	public String getOutDirName() {
		return outDirName;
	}
	
	public void setOutDirName(String outDirName) {
		this.outDirName = outDirName;
	}
	
	public String getImgMagicConvert() {
		return imgMagicConvert;
	}
	public void setImgMagicConvert(String imgMagicConvert) {
		this.imgMagicConvert = imgMagicConvert;
	}
	public boolean isBorder() {
		return border;
	}
	public void setBorder(boolean border) {
		this.border = border;
	}
	public int getBorderSize() {
		return borderSize;
	}
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}
	public boolean isAutolevel() {
		return autolevel;
	}
	public void setAutolevel(boolean autolevel) {
		this.autolevel = autolevel;
	}
	
	
}
