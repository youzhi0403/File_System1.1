package cn.powerrun.model;

import java.util.Date;
/**
 * 
 * @author Admin
 * 存储每一个工单的定位的信息
 */
public class Locate {
	private String id;             //制图对象id
	private Date locateDate;       //制图完成时间

	private String jdkJAR; // java工程所使用的定位jar包存储位置
	private String sdkJAR; // android工程所使用的定位jar包存储位置
	private String northPhoto; // 正北图存储位置
	private String locateJSON; // 有定位数据的json文件的存储位置

	public Locate() {
		super();
	}

	public Locate(String id) {
		super();
		this.id = id;
	}

	public Locate(String id, Date locateDate, String jdkJAR, String sdkJAR, String northPhoto, String locateJSON) {
		super();
		this.id = id;
		this.locateDate = locateDate;
		this.jdkJAR = jdkJAR;
		this.sdkJAR = sdkJAR;
		this.northPhoto = northPhoto;
		this.locateJSON = locateJSON;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLocateDate() {
		return locateDate;
	}

	public void setLocateDate(Date locateDate) {
		this.locateDate = locateDate;
	}

	public String getJdkJAR() {
		return jdkJAR;
	}

	public void setJdkJAR(String jdkJAR) {
		this.jdkJAR = jdkJAR;
	}

	public String getSdkJAR() {
		return sdkJAR;
	}

	public void setSdkJAR(String sdkJAR) {
		this.sdkJAR = sdkJAR;
	}

	public String getNorthPhoto() {
		return northPhoto;
	}

	public void setNorthPhoto(String northPhoto) {
		this.northPhoto = northPhoto;
	}

	public String getLocateJSON() {
		return locateJSON;
	}

	public void setLocateJSON(String locateJSON) {
		this.locateJSON = locateJSON;
	}

	@Override
	public String toString() {
		return "Locate [id=" + id + ", locateDate=" + locateDate + ", jdkJAR=" + jdkJAR + ", sdkJAR=" + sdkJAR
				+ ", northPhoto=" + northPhoto + ", locateJSON=" + locateJSON + "]";
	}

}
